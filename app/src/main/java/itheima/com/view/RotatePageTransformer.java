package itheima.com.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by bushangkoukou on 2017/5/31.
 */

public class RotatePageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if (position < -1) { // [-Infinity,-1)
            page.setRotation(0);
        } else if (position <= 1) { // (0,1]  [-1 1]
            page.setRotation(30 * position);
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getHeight());
        } else { // (1,+Infinity]
            page.setRotation(0);
        }
    }
}

