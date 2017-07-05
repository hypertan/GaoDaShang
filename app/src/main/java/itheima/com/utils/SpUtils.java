package itheima.com.utils;

/**
 * Created by bushangkoukou on 2017/5/31.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

    private static String NAME = "zhbj101";
    private static SharedPreferences sp;
    public static final String IS_APP_FIRSTOPEN = "isAppFirstOpen";
    public static final String NEWSCENTER_CACHE_JSON = "newscenter_cache_json";
    public static final String NEWSID_CACHE_JSON = "newsid_cache_json";

    public static boolean getBoolean(Context context, String key,
                                      boolean defValue) {
        if(sp==null){
            sp = context.getSharedPreferences(NAME,
                    Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        if(sp==null){
            sp = context.getSharedPreferences(NAME,
                    Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    public static void putString(Context context, String key, String value) {
        if(sp==null){
            sp = context.getSharedPreferences(NAME,
                    Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key,
                                     String defValue) {
        if(sp==null){
            sp = context.getSharedPreferences(NAME,
                    Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }

}
