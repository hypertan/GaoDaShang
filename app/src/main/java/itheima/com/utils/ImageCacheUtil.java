package itheima.com.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by bushangkoukou on 2017/6/8.
 */

public class ImageCacheUtil {

    private final MemoryCacheUtil memoryCacheUtil;
    private final LocalCacheUtil localCacheUtil;
    private final NetCahceUtil netCahceUtil;

    public ImageCacheUtil(Context context){
        memoryCacheUtil = new MemoryCacheUtil();
        localCacheUtil = new LocalCacheUtil(memoryCacheUtil);
        netCahceUtil = new NetCahceUtil(context,memoryCacheUtil,localCacheUtil);
    }

    public void display(String url, ImageView imageView) {

        Bitmap bitmap = null;
        //从内存缓存取图片
        bitmap = memoryCacheUtil.get(url);
        if (bitmap != null) {
            System.out.println("从内存缓存取图片");
            imageView.setImageBitmap(bitmap);
            return;
        }
        //从磁盘缓存取图片
        bitmap = localCacheUtil.get(url);
        if (bitmap != null) {
            System.out.println("从磁盘缓存取图片");
            imageView.setImageBitmap(bitmap);
            return;
        }
        //访问网络取图片
        System.out.println("访问网络取图片");
        netCahceUtil.display(url,imageView);

    }
}
