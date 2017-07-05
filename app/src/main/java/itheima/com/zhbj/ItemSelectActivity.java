package itheima.com.zhbj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itheima.com.view.MyGridLayout;

/**
 * Created by bushangkoukou on 2017/6/8.
 */

public class ItemSelectActivity extends Activity {


    @BindView(R.id.mygridlayout1)
    MyGridLayout mygridlayout1;
    @BindView(R.id.mygridlayout2)
    MyGridLayout mygridlayout2;
    private List<String> select = Arrays.asList("北京", "中国", "国际", "体育", "生活", "旅游", "科技", "军事", "时尚", "财经", "育儿", "汽车");
    private List<String> unselect = Arrays.asList("娱乐", "服饰", "音乐", "视频", "段子", "搞笑", "科学", "房产", "名站");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_itemselect);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {

        mygridlayout1.setData(select);
        mygridlayout2.setData(unselect);

        mygridlayout1.setDragable(true);

        mygridlayout1.setOnItemClickListener(new MyGridLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                mygridlayout1.removeView(v);
                String string = ((TextView) v).getText().toString();
                mygridlayout2.addItem(string);
            }
        });
        mygridlayout2.setOnItemClickListener(new MyGridLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                mygridlayout2.removeView(v);
                String string = ((TextView) v).getText().toString();
                mygridlayout1.addItem(string);
            }
        });
    }
}

