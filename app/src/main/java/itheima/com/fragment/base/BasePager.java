package itheima.com.fragment.base;

import android.content.Context;
import android.view.View;

/**
 * Created by bushangkoukou on 2017/6/1.
 */

public abstract class BasePager {
    // 记录传递进来的上下文对象，方便子类使用
    protected Context mContext;
    // 创建对象时，就创建控件，把控件保存，外界直接使用
    public View view;

    public BasePager(Context context) {
        this.mContext = context;
        view = initView();
    }
    //创建控件
    protected abstract View initView();

    // 更新控件
    public abstract void initData();
}

