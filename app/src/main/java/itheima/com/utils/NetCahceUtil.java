package itheima.com.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bushangkoukou on 2017/6/8.
 */

public class NetCahceUtil {

    private final Activity activity;
    private final MemoryCacheUtil memoryCacheUtil;
    private final LocalCacheUtil localCacheUtil;

    public NetCahceUtil(Context context, MemoryCacheUtil memoryCacheUtil, LocalCacheUtil localCacheUtil){
        activity = (Activity) context;
        this.memoryCacheUtil = memoryCacheUtil;
        this.localCacheUtil = localCacheUtil;
    }

    public  void  display(final String url, final ImageView imageView){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bytes = response.body().bytes();
                //把文件转换成Bitmap
                final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //显示图片
                        imageView.setImageBitmap(bitmap);
                        //把图片保存到内存和磁盘
                        memoryCacheUtil.save(url,bitmap);
                        localCacheUtil.save(url,bitmap);
                    }
                });


            }
        });
    }
}
