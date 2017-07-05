package itheima.com.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itheima.com.bean.NewsItemBean;
import itheima.com.fragment.base.BasePager;
import itheima.com.utils.SpUtils;
import itheima.com.utils.UrlUtils;
import itheima.com.zhbj.HomeActivity;
import itheima.com.zhbj.NewsDetailActivity;
import itheima.com.zhbj.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bushangkoukou on 2017/6/3.
 */

public class NewsItemPager extends BasePager {


    @BindView(R.id.vp_newsitem_topimage)
    ViewPager vpNewsitemTopimage;
    @BindView(R.id.lv_newsitem_list)
    ListView lvNewsitemList;
    @BindView(R.id.tv_topimage_title)
    TextView tvTopimageTitle;
    @BindView(R.id.cpi_indicator)
    CirclePageIndicator cpiIndicator;
    private String url;
    private List<NewsItemBean.DataBean.TopnewsBean> topnewsData;
    private List<NewsItemBean.DataBean.NewsBean> newsData;
    private static final int ITEM_TYPE_ONE = 0;//一个图片的条目类型
    private static final int ITEM_TYPE_THREE = 1;//三个图片的条目类型
    private PullToRefreshListView pullToRefreshListView;
    private NewsAdapter adapter;
    private String moreUrl;

    public NewsItemPager(Context context, String url) {
        super(context);
        this.url = url;
    }


    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.pager_newsitem, null);
//        ButterKnife.bind(this, view);
        //PullToRefreshListView内部封装一个ListView
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_newsitem_list);
        //修改加载时的文字
        ILoadingLayout loadingLayoutProxy = pullToRefreshListView.getLoadingLayoutProxy(false, true);
        loadingLayoutProxy.setPullLabel("上拉加载....");
        loadingLayoutProxy.setReleaseLabel("松开以加载....");

        //监听下拉刷新和上拉加载
        pullToRefreshListView.setOnRefreshListener(new MyOnRefreshListener());

        lvNewsitemList = pullToRefreshListView.getRefreshableView();


        //创建轮播图控件
        View header = View.inflate(mContext, R.layout.header_topimage, null);
//        ButterKnife.bind(this,header);
        vpNewsitemTopimage = (ViewPager) header.findViewById(R.id.vp_newsitem_topimage);
        tvTopimageTitle = (TextView) header.findViewById(R.id.tv_topimage_title);
        cpiIndicator = (CirclePageIndicator) header.findViewById(R.id.cpi_indicator);

        lvNewsitemList.addHeaderView(header);
        lvNewsitemList.setOnItemClickListener(new MyOnItemClickListener());
        return view;
    }
    class MyOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("onItemClick:"+position);
            int pos = position -2;// 因为下拉刷新添加一个头，轮播图也是一个头，position从2开始

            NewsItemBean.DataBean.NewsBean newsBean = newsData.get(pos);

            // 把点击的新闻id存储起来
            // 存储时， 先把已经存储的id获取 ，再累加当前点击的id 35311 、 35312 35333
            String cacheId = SpUtils.getString(mContext, SpUtils.NEWSID_CACHE_JSON, "");
            if(!cacheId.contains(String.valueOf(newsBean.id))){
                cacheId += ","+newsBean.id;
                SpUtils.putString(mContext,SpUtils.NEWSID_CACHE_JSON,cacheId);

                adapter.notifyDataSetChanged();
            }
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            intent.putExtra("url",newsBean);
            mContext.startActivity(intent);

        }
    }

    class MyOnRefreshListener implements PullToRefreshBase.OnRefreshListener2{

        //下拉刷新回调
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            //访问网络,刷新数据
            getDataFromServer(true);

        }

        //上拉加载回调
        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            // 访问网络，获取更多的数据，把数据累加到之前的ListView之后
            // 判断是否有更多数据
            if(TextUtils.isEmpty(moreUrl)){
                Toast.makeText(mContext, "没有更多数据了", Toast.LENGTH_SHORT).show();
                // 需要把下拉刷新控件复位
                pullToRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(pullToRefreshListView.isRefreshing()){
                            pullToRefreshListView.onRefreshComplete();// 设置刷新完成
                        }
                    }
                },100);

            }else{
                getDataFromServer(true);
            }
        }
    }

    @Override
    public void initData() {
        System.out.println("NewsItemPager" + url);

        //访问网络之前,读取缓存的json
        String cacheJson = SpUtils.getString(mContext, url, "");
        if (!TextUtils.isEmpty(cacheJson)){
            parseJson(cacheJson,false);
        }

        getDataFromServer(false);
    }

    private void getDataFromServer(final boolean isLoamore ) {
        OkHttpClient okHttpClient = new OkHttpClient();
        String netUrl = "";
        if (!isLoamore)// 不是加载更多
        {
            netUrl = UrlUtils.BASE_URL + this.url;
        }else{
            netUrl = UrlUtils.BASE_URL + moreUrl;// http://10.0.2.2:8080/zhbj
        }
        final Request request = new Request.Builder().url(UrlUtils.BASE_URL + url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //访问网络完成后,需要把下拉刷新控件复位
                ((HomeActivity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "网络出问题了", Toast.LENGTH_SHORT).show();
                        if (pullToRefreshListView.isRefreshing()){
                            pullToRefreshListView.onRefreshComplete();//设置刷新完成
                        }
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //访问网络完成后,需要把下拉刷新控件复位
                final String result = response.body().string();
                //缓存数据
                SpUtils.putString(mContext,url,result);
                ((HomeActivity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            parseJson(result, false);
                            //设置刷新完成
                            if (pullToRefreshListView.isRefreshing()){
                                pullToRefreshListView.onRefreshComplete();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    private void parseJson(String result, boolean isLoadMore) {
        System.out.println("parsejson:"+result);
        Gson gson = new Gson();
        NewsItemBean newsItemBean = gson.fromJson(result, NewsItemBean.class);

        if (!isLoadMore){
            System.out.println(newsItemBean.data.topnews.get(0).title);

            //获取顶部轮播图的图片集合
            topnewsData = newsItemBean.data.topnews;
            //给轮播图设置一个适配器
            vpNewsitemTopimage.setAdapter(new TopNewsAdapter());
            //监听ViewPager的滑动
            vpNewsitemTopimage.addOnPageChangeListener(new MyListener());

            //初始化轮播图首页图片标题
            tvTopimageTitle.setText(topnewsData.get(0).title);

            //绑定圆点指示器
            cpiIndicator.setViewPager(vpNewsitemTopimage);
            //设置圆点指示器红点不滑动
            cpiIndicator.setSnap(true);

            //获取新闻列表的数据哦
            newsData = newsItemBean.data.news;

            //给新闻列表设置适配器
            adapter =  new  NewsAdapter();
            lvNewsitemList.setAdapter(adapter);
        }else {
            // 如果是加载更多，把解析的数据中的新闻集合，添加到之前的ListView中
            newsData.addAll(newsItemBean.data.news);
            adapter.notifyDataSetChanged();
        }

        handler.removeCallbacksAndMessages(null);//把未处理的消息删除
        //Handler发送延迟消息,让轮播图自动替换
        handler.sendEmptyMessageDelayed(0,3000);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //让ViewPager,往右边滑动一页
            //当滑动到最后一页时,自动回到第一页
            int item = (vpNewsitemTopimage.getCurrentItem() + 1)%topnewsData.size();
            //当从最后一页滑动到第一页时,不需要滑动动画
            if (item==0){
                vpNewsitemTopimage.setCurrentItem(item,false);//参数2代表是否需要滑动效果
            }else {
                vpNewsitemTopimage.setCurrentItem(item);
            }


            //再次发送消息,实现无限循环
            handler.sendEmptyMessageDelayed(0,3000);
        }
    };




    class NewsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return newsData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        //返回条目类型个数
        @Override
        public int getViewTypeCount() {
            return 2;
        }

        //判断当前条目属于的类型 返回的值必须小于getViewTypeCount-1
        @Override
        public int getItemViewType(int position) {
            //根据服务器返回的数据中的type 如果是0 条目中只有1个图片,如果是1条目有3个图片
            NewsItemBean.DataBean.NewsBean newsBean = newsData.get(position);
            if (newsBean.type.equals("0")) {
                return ITEM_TYPE_ONE;
            } else {
                return ITEM_TYPE_THREE;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NewsItemBean.DataBean.NewsBean newsBean = newsData.get(position);
            //取存储的新闻id
            String cacheId = SpUtils.getString(mContext, SpUtils.NEWSCENTER_CACHE_JSON, "");
            View view = null;
            //根据当前条目的类型,返回不同的布局
            int itemViewType = getItemViewType(position);
            if (itemViewType == ITEM_TYPE_ONE) {//返回一个图片的布局
                view = View.inflate(mContext, R.layout.item_newsitem_one, null);
                ViewHolder viewHolder = new ViewHolder(view);
                Glide.with(mContext).load(newsBean.listimage).into(viewHolder.ivNewsitemImg);
                viewHolder.tvNewsitemTitle.setText(newsBean.title);
                viewHolder.tvNewsitemTime.setText(newsBean.pubdate);
                if (cacheId.contains(String.valueOf(newsBean.id))){
                    viewHolder.tvNewsitemTitle.setTextColor(Color.RED);
                }else {
                    viewHolder.tvNewsitemTitle.setTextColor(Color.BLACK);
                }
            } else {//返回三个图片的布局
                view = View.inflate(mContext, R.layout.item_newsitem_three, null);
                ViewHolder2 viewHolder2 = new ViewHolder2(view);
                Glide.with(mContext).load(newsBean.listimage).into(viewHolder2.ivNewsitemImg);
                Glide.with(mContext).load(newsBean.listimage1).into(viewHolder2.ivNewsitemImg1);
                Glide.with(mContext).load(newsBean.listimage2).into(viewHolder2.ivNewsitemImg2);
                viewHolder2.tvNewsitemTime.setText(newsBean.pubdate);
                if (cacheId.contains(String.valueOf(newsBean.id))){
                    viewHolder2.tvNewsitemTime.setTextColor(Color.RED);
                }else {
                    viewHolder2.tvNewsitemTime.setTextColor(Color.BLACK);
                }


            }

            return view;
        }


    }
    static class ViewHolder2 {
        @BindView(R.id.iv_newsitem_img)
        ImageView ivNewsitemImg;
        @BindView(R.id.iv_newsitem_img1)
        ImageView ivNewsitemImg1;
        @BindView(R.id.iv_newsitem_img2)
        ImageView ivNewsitemImg2;
        @BindView(R.id.tv_newsitem_time)
        TextView tvNewsitemTime;

        ViewHolder2(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder {
        @BindView(R.id.iv_newsitem_img)
        ImageView ivNewsitemImg;
        @BindView(R.id.tv_newsitem_title)
        TextView tvNewsitemTitle;
        @BindView(R.id.tv_newsitem_time)
        TextView tvNewsitemTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    class MyListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //当ViewPager切换页面时,改变图片标题
            tvTopimageTitle.setText(topnewsData.get(position).title);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    class TopNewsAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return topnewsData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //创建图片控件
            ImageView imageView = new ImageView(mContext);
            //设置ImageView缩放类型
            //把图片缩放到把ImageView宽高完全填充,图片会拉伸
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //显示网络图片
            Glide.with(mContext).load(topnewsData.get(position).topimage).into(imageView);
            container.addView(imageView);

            //处理图片触摸事件
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            handler.removeCallbacksAndMessages(null);
                            break;
                        case MotionEvent.ACTION_UP:
                            handler.sendEmptyMessageDelayed(0,3000);
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            handler.sendEmptyMessageDelayed(0,3000);
                             break;
                        default:
                            break;
                    }
                    return true;
                }
            });
            return imageView;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }

}
