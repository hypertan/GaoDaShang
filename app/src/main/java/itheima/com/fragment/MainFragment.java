package itheima.com.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itheima.com.fragment.base.BaseFragment;
import itheima.com.zhbj.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.rg_buttom)
    RadioGroup rgButtom;
    Unbinder unbinder;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        //初始化显示首页Fragment
        switchFragment(new HomeFragment());
        //  处理底部单向按钮点击事件
        rgButtom.setOnCheckedChangeListener(this);
        //设置默认选中首页单选按钮
        rgButtom.check(R.id.rb_home);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                switchFragment(new HomeFragment());
                enableSlidingMenu(false);
                break;
            case R.id.rb_newscenter:
                switchFragment(new NewscenterFragment());
                enableSlidingMenu(true);
                break;
            case R.id.rb_video:
                switchFragment(new VideoFragment());
                enableSlidingMenu(true);
                break;
            case R.id.rb_govaffairs:
                switchFragment(new GovaffairsFragment());
                enableSlidingMenu(true);
                break;
            case R.id.rb_setting:
                switchFragment(new SettingFragment());
                enableSlidingMenu(true);
                break;
            default:
                break;
        }
    }

    private void enableSlidingMenu(boolean enable) {
        //判断左侧菜单的依据
        SlidingMenu slidingMenu = mActivity.getSlidingMenu();
        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }

    }

    private void switchFragment(Fragment fragment) {
        //动态替换Fragment
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, fragment);
        transaction.commit();

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
}

