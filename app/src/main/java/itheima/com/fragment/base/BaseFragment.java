package itheima.com.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import itheima.com.zhbj.HomeActivity;

/**
 * Created by bushangkoukou on 2017/6/1.
 */

public abstract class BaseFragment extends Fragment {

    // 获取当前Fragment的宿主，就是HomeActivity
    protected HomeActivity mActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = (HomeActivity) getActivity();
        return initView();
    }

    protected abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();
}
