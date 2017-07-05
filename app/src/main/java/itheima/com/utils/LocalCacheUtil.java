package itheima.com.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by bushangkoukou on 2017/6/8.
 */

public class LocalCacheUtil {

    private final MemoryCacheUtil memoryCacheUtil;
    private  String cathe_dir;

    public  LocalCacheUtil(MemoryCacheUtil memoryCacheUtil){
        cathe_dir = Environment.getExternalStorageDirectory().getAbsolutePath()+"/zhbj";
        this.memoryCacheUtil = memoryCacheUtil;
    }
    //存图片
    public void save(String url, Bitmap bitmap){
        try {
            //把url作为文件名
            String fileName = MD5Encoder.encode(url);
            File file = new File(cathe_dir,fileName);
            //判断父目录是否存在
            File parent = file.getParentFile();
            if (!parent.exists()){
                parent.mkdir();
            }
            //把Bitmap保存到文件中
            OutputStream outputstream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputstream);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //取图片
    public  Bitmap get(String url){

        try {
            String fileName = MD5Encoder.encode(url);
            File file = new File(cathe_dir,fileName);
            if (file.exists()){
                //把文件转换成Bitmap
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                memoryCacheUtil.save(url,bitmap);
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return  null;
    }
}
