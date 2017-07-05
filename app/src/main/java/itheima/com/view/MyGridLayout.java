package itheima.com.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import itheima.com.zhbj.R;


/**
 * Created by huang on 2017/6/8.
 */

public class MyGridLayout extends GridLayout implements View.OnDragListener {
    private OnItemClickListener listener;
    private List<Rect> rects;
    private boolean dragable;

    public MyGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 设置添加控件的动画
        setLayoutTransition(new LayoutTransition());

        // 监听控件的拖拽事件
        setOnDragListener(this);
    }

    // 根据传递进来的数据，动态添加子控件
    public void setData(List<String> data) {
        for (int i = 0; i <data.size() ; i++) {
            addItem(data.get(i));
        }
    }

    public void addItem(String s) {
        TextView textView = new TextView(getContext());
        textView.setBackgroundResource(R.drawable.item_select_bg);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(15,5,15,5);
        textView.setText(s);
        // 把TextView添加到容器中
        addView(textView);
        // 给TextView设置外边距
        LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(5,5,5,5);

        // 监听TextView点击事件
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClick(v);
                }
            }
        });

        // 监听长按事件
        textView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!dragable){
                    return true;
                }

                // 把当前控件变红色
                v.setBackgroundResource(R.drawable.item_select_red_bg);

                dragItem = v;
                // 开始拖拽控件
                v.startDrag(null,new DragShadowBuilder(v),null,0);

                // 获取所有子空间的矩形区域
                getAllRects();
                return true;
            }
        });
    }
    private View dragItem = null;

    private void getAllRects() {
        rects = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            rects.add(new Rect(view.getLeft(),view.getTop(),view.getRight(),view.getBottom()));
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if(!dragable){
            return true;
        }
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                System.out.println("ACTION_DRAG_STARTED");
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                System.out.println("ACTION_DRAG_ENTERED");
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                System.out.println("ACTION_DRAG_EXITED");
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                System.out.println("ACTION_DRAG_LOCATION");
                // 控件拖拽时，位置发生变化
                // 根据当前拖拽的控件，判断落在哪一个控件中,获取拖拽控件将要插入的位置
                int dragItemIndex = findDragItem(event);
                if(dragItemIndex!=-1&&dragItem!=null&&getChildAt(dragItemIndex)!=dragItem){
                    // 先删除原来位置的控件
                    removeView(dragItem);
                    // 再把拖拽的控件添加到新位置
                    addView(dragItem,dragItemIndex);
                }
                break;
            case DragEvent.ACTION_DROP:
                System.out.println("ACTION_DROP");
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                System.out.println("ACTION_DRAG_ENDED");
                if(dragItem!=null){
                    dragItem.setBackgroundResource(R.drawable.item_select_bg);
                }
                break;
            default:
                break;
        }

        return true;
    }

    private int findDragItem(DragEvent event) {
        if(rects==null){
            return -1;
        }
        for (int i = 0; i < rects.size(); i++) {
            // 拖拽的控件落在一个控件内
            if(rects.get(i).contains((int)event.getX(),(int)event.getY())){
                return i;
            }
        }
        return -1;
    }

    public interface OnItemClickListener{
        void onItemClick(View v);
    }

    // 设置控件是否可以拖拽
    public void setDragable(boolean dragable){
        this.dragable = dragable;
    }
}
