package itheima.com.fragment;

import android.widget.TextView;

import itheima.com.fragment.base.BasePagerFragment;

/**
 * Created by bushangkoukou on 2017/6/1.
 */
public class GovaffairsFragment extends BasePagerFragment {
    @Override
    protected void initData() {
        tvTitle.setText("政务");

        TextView textView = new TextView(mActivity);
        textView.setText("政务");
        flBasepager.addView(textView);
    }
}
