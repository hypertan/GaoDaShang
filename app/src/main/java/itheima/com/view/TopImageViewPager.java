package itheima.com.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by bushangkoukou on 2017/6/3.
 */

public class TopImageViewPager extends ViewPager {

    private int downX;
    private int downY;

    public TopImageViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();

                //水平移动的距离
                int diffX = moveX - downX;
                //竖直移动的距离
                int diffY = moveY - downY;

                if (Math.abs(diffX)<Math.abs(diffY)){
                    //1上下滑动,不处理
                    getParent().requestDisallowInterceptTouchEvent(false);
                }else {
                    //2左右滑动
                    //2.1滑动到最左边,且手指从左往右,不处理
                    if (getCurrentItem()==0&&diffX>0){
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    //2.2滑动到最右边,手指从右向左,不处理
                    else if (getCurrentItem()==getAdapter().getCount()-1&&diffX<0){
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                    break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
