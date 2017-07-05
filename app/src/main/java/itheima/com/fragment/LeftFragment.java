package itheima.com.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itheima.com.bean.MessageEvent;
import itheima.com.fragment.base.BaseFragment;
import itheima.com.zhbj.R;

/**
 * Created by bushangkoukou on 2017/5/31.
 */

public class LeftFragment extends BaseFragment {


    String[] titles = new String[]{"新闻", "专题", "组图", "互动"};
    @BindView(R.id.lv_leftmenu)
    ListView lvLeftmenu;
    Unbinder unbinder;
    private int currentClickIndex;
    private MyAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        // 更新左侧菜单ListView
        adapter = new MyAdapter();
        lvLeftmenu.setAdapter(adapter);

        // 设置条目点击事件
        lvLeftmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 记录点击的条目位置
                currentClickIndex = position;
                // 刷新ListView
                adapter.notifyDataSetChanged();
                //收回侧滑菜单
                SlidingMenu slidingMenu = mActivity.getSlidingMenu();
                slidingMenu.toggle();
                // 使用EventBus发布消息，通知新闻中心Fragment
                EventBus.getDefault().post(new MessageEvent(position));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity, R.layout.item_leftmenu, null);
            ViewHolder holder = new ViewHolder(view);
            holder.tvMenutext.setText(titles[position]);

            // 判断当前点击的条目位置 和 当前position
            if (currentClickIndex == position) {
                holder.tvMenutext.setTextColor(Color.RED);
                holder.ivMenuarr.setBackgroundResource(R.drawable.menu_arr_select);
            } else {
                holder.tvMenutext.setTextColor(Color.WHITE);
                holder.ivMenuarr.setBackgroundResource(R.drawable.menu_arr_normal);
            }

            return view;
        }

        class ViewHolder {
            @BindView(R.id.iv_menuarr)
            ImageView ivMenuarr;
            @BindView(R.id.tv_menutext)
            TextView tvMenutext;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
