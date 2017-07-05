package itheima.com.zhbj;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import itheima.com.fragment.LeftFragment;
import itheima.com.fragment.MainFragment;

/**
 * Created by bushangkoukou on 2017/5/31.
 */
public class HomeActivity extends SlidingFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

     /*   if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }*/
        setContentView(R.layout.activity_home_main);
        setBehindContentView(R.layout.activity_home_left);
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        slidingMenu.setBehindOffset(360);
        initFragment();
        StatusBarUtil.setColor(this, Color.RED);
    }

    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_main, new MainFragment());
        transaction.replace(R.id.ll_left, new LeftFragment());
        transaction.commit();
    }
}


