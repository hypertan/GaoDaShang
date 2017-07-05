package itheima.com.fragment.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import itheima.com.zhbj.R;

/**
 * Created by bushangkoukou on 2017/6/1.
 */

public class BasePagerFragment extends BaseFragment {
    @BindView(R.id.ib_menu)
    protected
    ImageButton ibMenu;
    @BindView(R.id.tv_title)
    public
    TextView tvTitle;
    @BindView(R.id.fl_basepager)
    public
    FrameLayout flBasepager;
    @BindView(R.id.ib_photo)
    public ImageButton ibPhoto;
    Unbinder unbinder;


    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_basepager, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.ib_menu)
    public void onViewClicked() {
        // 获取侧滑菜单，自动打开或关闭菜单
        mActivity.getSlidingMenu().toggle();
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
