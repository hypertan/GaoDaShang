package itheima.com.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by bushangkoukou on 2017/5/31.
 */

public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
            // [-Infinity,-1)
        if (position < -1) {
            // This page is way off-screen to the left.
            view.setAlpha(0);

            // [-1,0]
        } else if (position <= 0) {
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out. position 1 0.9 0.8 ..0          0 .. 1
            view.setAlpha(1 - position);// 0 .. 1                 1 .. 0

            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}