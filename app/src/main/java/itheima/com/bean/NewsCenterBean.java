package itheima.com.bean;

import java.util.List;

/**
 * Created by bushangkoukou on 2017/6/1.
 */

public class NewsCenterBean {
    public int retcode;
    public List<Integer> extend;
    public List<MenuItem> data;

    public class MenuItem{
        public int id;
        public String title;
        public int type;
        public List<NewsTab> children;
        public String url;
        public String url1;
        public String dayurl;
        public String excurl;
        public String weekurl;
    }
    public class NewsTab{
        public int id;
        public String title;
        public int type;
        public String url;
    }
}
