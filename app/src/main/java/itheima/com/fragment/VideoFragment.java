package itheima.com.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import itheima.com.fragment.base.BasePagerFragment;
import itheima.com.view.VideoItem;

/**
 * Created by bushangkoukou on 2017/6/1.
 */
public class VideoFragment extends BasePagerFragment {
    private  String  url =  "http://flv2.bn.netease.com/videolib3/1604/28/fVobI0704/SD/fVobI0704-mobile.mp4";

    @Override
    protected void initData() {
        tvTitle.setText("视频");

        ListView listView = new ListView(mActivity);
        listView.setDividerHeight(0);
        flBasepager.addView(listView);

        //更新ListView
        listView.setAdapter(new MyAdapter());
    }

    class MyAdapter  extends BaseAdapter{

        @Override
        public int getCount() {
            return 10;
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
            if (convertView==null){
                convertView = new VideoItem(mActivity,url);
            }

            final VideoItem videoItem = (VideoItem) convertView;
            //显示预览界面
            videoItem.preview();
            videoItem.setOnVideoItemClickListener(new VideoItem.OnVideoItemClickListener() {
                @Override
                public void onVideoItemClick() {
                    //判断是否有视屏正在播放
                    if (currentVideoItem!=null&&currentVideoItem!=videoItem){
                        //停止播放
                        currentVideoItem.preview();
                    }
                    currentVideoItem = videoItem;
                }
            });
            return convertView;
        }
    }
    private VideoItem currentVideoItem = null;
}
