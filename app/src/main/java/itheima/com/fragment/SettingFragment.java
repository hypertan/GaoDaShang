package itheima.com.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import itheima.com.fragment.base.BasePagerFragment;
import itheima.com.zhbj.CollectActivity;
import itheima.com.zhbj.R;

/**
 * Created by bushangkoukou on 2017/6/1.
 */
public class SettingFragment extends BasePagerFragment {
        Button btSettingCollect;
    @Override
    protected void initData() {
        ibMenu.setVisibility(View.GONE);
        tvTitle.setText("设置");

     /*   TextView textView = new TextView(mActivity);
        textView.setText("设置");
        flBasepager.addView(textView);*/
        View view = View.inflate(mActivity, R.layout.fragment_setting,null);
        btSettingCollect = (Button) view.findViewById(R.id.bt_setting_collect);
        btSettingCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity,CollectActivity.class));
            }
        });
        flBasepager.addView(view);
    }
}
