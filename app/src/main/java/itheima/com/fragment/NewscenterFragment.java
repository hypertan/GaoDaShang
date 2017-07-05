package itheima.com.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import itheima.com.bean.MessageEvent;
import itheima.com.bean.NewsCenterBean;
import itheima.com.fragment.base.BasePager;
import itheima.com.fragment.base.BasePagerFragment;
import itheima.com.menu.InterractPager;
import itheima.com.menu.NewsPager;
import itheima.com.menu.PhotoPager;
import itheima.com.menu.TopicPager;
import itheima.com.utils.SpUtils;
import itheima.com.utils.UrlUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bushangkoukou on 2017/6/1.
 */
public class NewscenterFragment extends BasePagerFragment {
    String[] titles = new String[]{"新闻", "专题", "组图", "互动"};
    private List<BasePager> pagers;

    @Override
    protected void initData() {
        tvTitle.setText("新闻中心");

        TextView textView = new TextView(mActivity);
        textView.setText("新闻中心");
        flBasepager.addView(textView);

        //访问网络之前,先取缓存数据,取到就展示
        String cacheJson = SpUtils.getString(mActivity, SpUtils.NEWSCENTER_CACHE_JSON, "");
        if (!TextUtils.isEmpty(cacheJson)){
            //解析缓存数据,展示
            parseJson(cacheJson);
        }
        getDataFromServer();
    }


    private void getDataFromServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(UrlUtils.NEWSCENTER_URL).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String result = null;
                        try {
                            result = response.body().string();
                            //缓存json数据
                            SpUtils.putString(mActivity,SpUtils.NEWSCENTER_CACHE_JSON,result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(result);
                        parseJson(result);
                    }
                });

            }
        });
    }

    private void parseJson(String result) {
        Gson gson = new Gson();
        NewsCenterBean newsCenterBean = gson.fromJson(result, NewsCenterBean.class);
        System.out.println(newsCenterBean.data.get(0).children.get(0).url);
        // 创建左侧菜单对应的4个对象
        pagers = new ArrayList<>();
        pagers.add(new NewsPager(mActivity,newsCenterBean.data.get(0).children));
        pagers.add(new TopicPager(mActivity));
        pagers.add(new PhotoPager(mActivity));
        pagers.add(new InterractPager(mActivity));
        switchTitle(0);

    }


    // 使用Eventbus
    // MAIN 此方法执行在主线程
    // ASYNC 方法执行在子线程
    // BACKGROUND 发消息的方法运行在主线程，此方法运行在子线程；如果发消息的方法运行在子线程，此方法运行在子线程
    // POSTING 发消息的方法运行在什么线程，此方法就运行在什么线程
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void switchTitle(MessageEvent event) {
        System.out.println("event:"+event.getPosition()+":"+Thread.currentThread().getName());

        switchTitle(event.getPosition());
    }
    private void switchTitle(int index){
        tvTitle.setText(titles[index]);

        // 根据左侧菜单点击的位置，切换新闻中心内容
        final BasePager pager = pagers.get(index);
        // 每次都需要重新创建控件，耗费资源
        //  View view = pager.initView();
        // 清除之前的控件
        flBasepager.removeAllViews();
        flBasepager.addView(pager.view);
        pager.initData();

        //如果当前展示的是组图界面,设置显示组图切换按钮
        if (index==2){
            ibPhoto.setVisibility(View.VISIBLE);
            ibPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //切换组图按钮
                    //切换ListView和GridView
                    PhotoPager photoPager = (PhotoPager) pagers.get(2);

                    photoPager.switchPhotoType(ibPhoto);
                }
            });
        }else {
            ibPhoto.setVisibility(View.GONE);
        }




    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
