package itheima.com.menu;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itheima.com.bean.PhotoBean;
import itheima.com.fragment.base.BasePager;
import itheima.com.utils.ImageCacheUtil;
import itheima.com.utils.UrlUtils;
import itheima.com.zhbj.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by bushangkoukou on 2017/6/1.
 */

public class PhotoPager extends BasePager {

    @BindView(R.id.lv_photo_list)
    ListView lvPhotoList;
    @BindView(R.id.gv_photo_grid)
    GridView gvPhotoGrid;
    private List<PhotoBean.DataBean.NewsBean> newsData;
    private final ImageCacheUtil imageCacheUtil;

    public PhotoPager(Context context) {
        super(context);
        imageCacheUtil = new ImageCacheUtil(mContext);
    }

    @Override
    protected View initView() {
        /*TextView textView = new TextView(mContext);
        textView.setText("组图详情");
        return textView;*/
        View view = View.inflate(mContext, R.layout.pager_photo, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {

        getDataFromServer();
    }

    public void getDataFromServer() {

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(UrlUtils.PHOTO_URL).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();

                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parseJson(result);
                    }
                });

            }
        });
    }

    private void parseJson(String result) {
//        System.out.println(result );
        Gson gson = new Gson();
        PhotoBean photoBean = gson.fromJson(result, PhotoBean.class);
        System.out.println(photoBean.data.news.get(0).title);

        //把数据展示到listview中
        newsData = photoBean.data.news;
        lvPhotoList.setAdapter(new MyAdapter());
    }

    boolean isListType = true;//当前是ListView


    public void switchPhotoType(ImageButton ibPhoto) {
        //当前是ListView
        if (isListType) {
            ibPhoto.setBackgroundResource(R.drawable.icon_pic_grid_type);
            lvPhotoList.setVisibility(View.GONE);
            gvPhotoGrid.setVisibility(View.VISIBLE);
            gvPhotoGrid.setAdapter(new MyAdapter());
        }

        //当前是GridView
        else {
            ibPhoto.setBackgroundResource(R.drawable.icon_pic_list_type);
            lvPhotoList.setVisibility(View.VISIBLE);
            gvPhotoGrid.setVisibility(View.GONE);
            lvPhotoList.setAdapter(new MyAdapter());
        }

        isListType = !isListType;
    }

    class MyAdapter extends BaseAdapter {

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

            ArrayList<SoftReference<MyAdapter>> list = new ArrayList<SoftReference<MyAdapter>>();

            MyAdapter myAdapter = new MyAdapter();
            SoftReference<MyAdapter> myAdapterSoftReference = new SoftReference<MyAdapter>(myAdapter);

            list.add(myAdapterSoftReference);


            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = View.inflate(mContext, R.layout.item_photo, null);


            ViewHolder viewHolder = new ViewHolder(view);
            PhotoBean.DataBean.NewsBean newsBean = newsData.get(position);
            viewHolder.tvPhotoItemTitle.setText(newsBean.title);
            //内部有图片的三级缓存
//            Glide.with(mContext).load(newsBean.listimage).into(viewHolder.ivPhotoItemImage);
            imageCacheUtil.display(newsBean.listimage,viewHolder.ivPhotoItemImage);
            return view;
        }


    }

    static class ViewHolder {
        @BindView(R.id.iv_photo_item_image)
        ImageView ivPhotoItemImage;
        @BindView(R.id.tv_photo_item_title)
        TextView tvPhotoItemTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
