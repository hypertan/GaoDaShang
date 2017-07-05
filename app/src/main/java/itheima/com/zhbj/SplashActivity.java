package itheima.com.zhbj;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import itheima.com.utils.SpUtils;

public class SplashActivity extends Activity {

    private VideoView vv_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        vv_video = (VideoView) findViewById(R.id.vv_video);
        startPlay();
    }

    private void startPlay() {
        //播放视屏源码
        vv_video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kr36));
        //播放视屏
        vv_video.start();
        // 播放完成时，重复播放
        vv_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                vv_video.start();
            }
        });
    }

    public void enter(View v) {
        boolean isFirstOpen = SpUtils.getBoolean(this, SpUtils.IS_APP_FIRSTOPEN, true);
        if (isFirstOpen) {
            startActivity(new Intent(this, GuideActivity.class));
        } else {
            startActivity(new Intent(this, HomeActivity.class));
        }
        finish();
    }


}
