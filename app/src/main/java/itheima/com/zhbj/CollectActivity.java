package itheima.com.zhbj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itheima.com.bean.NewsItemBean;
import itheima.com.utils.SpUtils;

/**
 * Created by bushangkoukou on 2017/6/7.
 */
public class CollectActivity extends Activity {
    @BindView(R.id.ib_menu)
    ImageButton ibMenu;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_collect_list)
    ListView lvCollectList;
    private List<NewsItemBean.DataBean.NewsBean> newsData;
    private static final int ITEM_TYPE_ONE = 0;// 一个图片的条目类型
    private static final int ITEM_TYPE_THREE = 1;// 3个图片的条目类型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        ibMenu.setVisibility(View.GONE);
        tvTitle.setText("收藏");
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        DbUtils utils = DbUtils.create(this, "zhbj");
        try {
            newsData = utils.findAll(NewsItemBean.DataBean.NewsBean.class);
            if (newsData == null) {
                return;
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        lvCollectList.setAdapter(new NewsAdapter());

        lvCollectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItemBean.DataBean.NewsBean newsBean = newsData.get(position);
                Intent intent = new Intent(CollectActivity.this, NewsDetailActivity.class);

                //传递当前点击的新闻对象,传递对象需要实现Serializable或Parcelable
                intent.putExtra("news", newsBean);
                startActivity(intent);
            }
        });
    }

    private class NewsAdapter extends BaseAdapter {
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


        //判断当前条目属于的类型  返回的值必须小于getViewTypeCount-1
        @Override
        public int getItemViewType(int position) {
            // 根据服务器返回的数据中的 type，如果是0 条目中只有1个图片，如果是 1 条目有3个图片
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
            // 取存储的新闻id
            String cacheId = SpUtils.getString(CollectActivity.this, SpUtils.NEWSID_CACHE_JSON, "");
            View view = null;
            // 根据当前条目的类型，返回不同的布局
            int itemViewType = getItemViewType(position);
            if (itemViewType == ITEM_TYPE_ONE) {// 返回一个图片的布局
                view = View.inflate(CollectActivity.this, R.layout.item_newsitem_one, null);
                ViewHolder viewHolder = new ViewHolder(view);

                Glide.with(CollectActivity.this).load(newsBean.listimage).into(viewHolder.ivNewsitemImg);
                viewHolder.tvNewsitemTitle.setText(newsBean.title);
                viewHolder.tvNewsitemTime.setText(newsBean.pubdate);


                if (cacheId.contains(String.valueOf(newsBean.id))) {
                    viewHolder.tvNewsitemTitle.setTextColor(Color.RED);
                } else {
                    viewHolder.tvNewsitemTitle.setTextColor(Color.BLACK);
                }
            } else {// 返回3个图片的布局
                view = View.inflate(CollectActivity.this, R.layout.item_newsitem_three, null);
                ViewHolder2 viewHolder2 = new ViewHolder2(view);

                Glide.with(CollectActivity.this).load(newsBean.listimage).into(viewHolder2.ivNewsitemImg);
                Glide.with(CollectActivity.this).load(newsBean.listimage1).into(viewHolder2.ivNewsitemImg1);
                Glide.with(CollectActivity.this).load(newsBean.listimage2).into(viewHolder2.ivNewsitemImg2);
                viewHolder2.tvNewsitemTime.setText(newsBean.pubdate);


                // 取存储的新闻id
                if (cacheId.contains(String.valueOf(newsBean.id))) {
                    viewHolder2.tvNewsitemTime.setTextColor(Color.RED);
                } else {
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
}


