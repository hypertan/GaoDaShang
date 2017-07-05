package itheima.com.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by bushangkoukou on 2017/6/8.
 */

public class MemoryCacheUtil {

    private  LruCache<String, Bitmap> lruCache;

    public MemoryCacheUtil(){

        //初始化LruCache
        int maxSize = (int) (Runtime.getRuntime().maxMemory()/8);//取当前应用能用的最大内存的1/8
        //返回每一个添加的图片大小
        lruCache = new LruCache<String, Bitmap>(maxSize){
            //返回每一个添加的图片大小

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }
        //获取图片
    public Bitmap get(String url){
          return   lruCache.get(url);
    }
        //保存图片
    public void  save(String url,Bitmap bitmap){
          lruCache.put(url,bitmap);
    }


}
