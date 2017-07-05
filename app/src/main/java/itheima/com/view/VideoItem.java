package itheima.com.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.io.IOException;

import itheima.com.zhbj.R;

/**
 * Created by bushangkoukou on 2017/6/10.
 */

public class VideoItem extends FrameLayout implements View.OnClickListener {

    private final String url;
    private ImageButton ib_videoitem_play;
    private View play;
    private View preview;
    private OnVideoItemClickListener listener;
    private TextureView ttv_video_player;
    private ImageButton ib_video_pause;
    private ProgressBar pb_video_progress;
    private MediaPlayer mediaPlayer;

    public VideoItem(Context context, String url) {
        super(context);
        this.url = url;

        initView();
    }

    private void initView() {
        //视屏预览布局
        preview = View.inflate(getContext(), R.layout.item_video, null);
        ib_videoitem_play = (ImageButton) preview.findViewById(R.id.ib_videoitem_play);

        ib_videoitem_play.setOnClickListener(this);

        //播放视屏布局
        play = View.inflate(getContext(), R.layout.item_video_play, null);
        ttv_video_player = (TextureView) play.findViewById(R.id.ttv_video_player);
        ib_video_pause = (ImageButton) play.findViewById(R.id.ib_video_pause);
        pb_video_progress =  (ProgressBar) play.findViewById(R.id.pb_video_progress);

        ib_video_pause.setOnClickListener(this);

        this.addView(preview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_videoitem_play:
                // 展示播放界面
                preview.setVisibility(INVISIBLE);
                this.addView(play);

                if(listener!=null){
                    listener.onVideoItemClick();
                }

                // 播放视频
                startPlay();
                break;
            case R.id.ib_video_pause:
                //判断当前视屏是否正在播放
                if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    ib_video_pause.setBackgroundResource(R.drawable.play_icon);
                }else {
                    mediaPlayer.start();
                    ib_video_pause.setBackgroundResource(R.drawable.pause_video);
                }
            default:
                break;
        }
    }

    private void startPlay() {
        //必须TextureView 创建成功才能使用
        ttv_video_player.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                //TextureView创建成功,可用
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setSurface(new Surface(surface));
                play();
            }



            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                //控件尺寸变化时调用
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                //控件销毁是调用 this.removeView(play);会导致TextureView被移除
                if (mediaPlayer!=null){
                    mediaPlayer.stop();
                }
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                //控件中的数据更新是调用
            }
        });
    }

    private void play() {
        try {
            //设置视屏数据源
            mediaPlayer.setDataSource(url);
            //重复播放
            mediaPlayer.setLooping(true);
            //准备
            mediaPlayer.prepareAsync();
            //监听播放器准备情况
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //去掉进度条
                    pb_video_progress.setVisibility(GONE);
                    ib_video_pause.setBackgroundResource(R.drawable.pause_video);
                    //真正开始播放视频
                    mediaPlayer.start();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOnVideoItemClickListener(OnVideoItemClickListener listener){
        this.listener = listener;
    }

    //显示预览界面
    public void preview() {
        preview.setVisibility(VISIBLE);
        this.removeView(play);
    }

    public interface OnVideoItemClickListener{
        void   onVideoItemClick();
    }
}
