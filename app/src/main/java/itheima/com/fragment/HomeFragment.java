package itheima.com.fragment;

import android.view.View;
import android.widget.TextView;

import itheima.com.fragment.base.BasePagerFragment;

/**
 * Created by bushangkoukou on 2017/6/1.
 */
public class HomeFragment extends BasePagerFragment {
    @Override
    protected void initData() {
        ibMenu.setVisibility(View.GONE);
        tvTitle.setText("首页");

        TextView textView = new TextView(mActivity);
        textView.setText("首页");
        flBasepager.addView(textView);
    }
}
