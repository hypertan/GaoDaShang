package itheima.com.zhbj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import itheima.com.utils.DensityUtil;
import itheima.com.utils.SpUtils;
import itheima.com.view.RotatePageTransformer;


/**
 * Created by bushangkoukou on 2017/5/31.
 */
public class GuideActivity extends Activity implements View.OnClickListener {
    private ViewPager vp_bg;
    private ArrayList<ImageView> imgs;
    private LinearLayout ll_points;
    private ImageView iv_redpoint;
    private Button bt_start;
    private int pointPx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        vp_bg = (ViewPager) findViewById(R.id.vp_bg);
        ll_points = (LinearLayout) findViewById(R.id.ll_points);
        iv_redpoint = (ImageView) findViewById(R.id.iv_redpoint);
        bt_start = (Button) findViewById(R.id.bt_start);
        bt_start.setOnClickListener(this);

        initViewPager();

        StatusBarUtil.setColor(this, Color.YELLOW);
    }

    private void initViewPager() {
        initData();
        vp_bg.setPageTransformer(true,new RotatePageTransformer());
        vp_bg.setAdapter(new MyAdapter());
        vp_bg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("position:"+position+":positionOffset:"+positionOffset+":positionOffsetPixels:"+positionOffsetPixels);
                int redPointX = (int) ((positionOffset+position) * pointPx*2);
                iv_redpoint.setTranslationX(redPointX);
            }

            @Override
            public void onPageSelected(int position) {
                if(position==imgs.size()-1){
                    bt_start.setVisibility(View.VISIBLE);
                }else{
                    bt_start.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initData() {
        imgs = new ArrayList<ImageView>();
        int[] imgIds = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
        for (int i = 0; i < imgIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imgIds[i]);
            imgs.add(imageView);

            //创建灰点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.guide_point_normal);
            //设置灰点的尺寸
            pointPx = DensityUtil.dip2px(getApplicationContext(), 10);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pointPx, pointPx);
            if (i!=0){
                params.leftMargin = pointPx;
            }
            point.setLayoutParams(params);
            //把灰点添加到界面
            ll_points.addView(point);
        }

    }

    @Override
    public void onClick(View v) {
        // 把是否打开过应用，保存boolean值 为false
        SpUtils.putBoolean(this,SpUtils.IS_APP_FIRSTOPEN,false);
        // 打开主界面
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private class MyAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imgs.get(position);
            vp_bg.addView(imageView);
            return imageView;
        }

    }

}
