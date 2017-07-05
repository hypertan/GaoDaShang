package itheima.com.menu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import itheima.com.bean.NewsCenterBean;
import itheima.com.fragment.base.BasePager;
import itheima.com.zhbj.HomeActivity;
import itheima.com.zhbj.ItemSelectActivity;
import itheima.com.zhbj.R;


/**
 * Created by bushangkoukou on 2017/6/1.
 */

public class NewsPager extends BasePager {

    private final List<NewsCenterBean.NewsTab> data;
    @BindView(R.id.indicator)
    TabPageIndicator indicator;
    @BindView(R.id.pager)
    ViewPager pager;
    private List<NewsItemPager> itemPagers;

    public NewsPager(Context context, List<NewsCenterBean.NewsTab> children) {
        super(context);
        this.data = children;
    }

    @Override
    protected View initView() {
       /* TextView textView = new TextView(mContext);
        textView.setText("新闻详情");
        return textView;*/
        View view = View.inflate(mContext, R.layout.pager_news, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        //根据页签数量,创建页签详情界面对象
        itemPagers = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            itemPagers.add(new NewsItemPager(mContext, data.get(i).url));
        }

        //给ViewPager设置数据
        pager.setAdapter(new MyAdapter());

        //关联ViewPager指示器
        indicator.setViewPager(pager);

        //监听ViewPager
        pager.addOnPageChangeListener(new MyListerner());

    }

    @OnClick(R.id.ib_itemselect)
    public void onViewClicked() {

        mContext.startActivity(new Intent(mContext,ItemSelectActivity.class));

    }

    class MyListerner implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //当北京页签,让SlidingMenu可以滑动
            //其他页签,不让滑动
            SlidingMenu slidingMenu = ((HomeActivity) mContext).getSlidingMenu();
            if (position == 0) {
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
            } else {
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    class MyAdapter extends PagerAdapter {

        //返回指示器显示的内容
        @Override
        public CharSequence getPageTitle(int position) {
            return data.get(position).title;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            /*TextView textView = new TextView(mContext);
            textView.setText(data.get(position).url);
            container.addView(textView);
            return textView;*/
            //根据位置获取页签详情界面对象
            NewsItemPager itemPager = itemPagers.get(position);
            container.addView(itemPager.view);
            //更新详情界面数据
            itemPager.initData();
            return itemPager.view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
