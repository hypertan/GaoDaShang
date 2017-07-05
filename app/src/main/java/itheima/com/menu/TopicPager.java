package itheima.com.menu;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import itheima.com.fragment.base.BasePager;


/**
 * Created by bushangkoukou on 2017/6/1.
 */

public class TopicPager extends BasePager {

    public TopicPager(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("专题详情");
        return textView;
    }

    @Override
    public void initData() {

    }
}
