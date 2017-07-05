package itheima.com.bean;

        import java.io.Serializable;
        import java.util.List;

/**
 * Created by bushangkoukou on 2017/6/3.
 */

public class NewsItemBean {
    /**
     * data : {"countcommenturl":"http://zhbj.qianlong.com/client/content/countComment/","more":"/10007/list_2.json","news":[{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"http://10.0.2.2:8080/zhbj/10007/2078369924F9UO.jpg","pubdate":"2014-10-1113:18","title":"网上大讲堂第368期预告：义务环保人人有责","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html","listimage1":"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg","listimage2":"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"http://10.0.2.2:8080/zhbj/10007/1509585620ASS3.jpg","pubdate":"2014-10-1111:20","title":"马路改建为停车场车位年费高达3000元","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35313,"listimage":"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg","listimage1":"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg","listimage2":"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg","pubdate":"2014-10-1110:34","title":"北京两年内将迁出1200家工业污染企业","type":1,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35314,"listimage":"http://10.0.2.2:8080/zhbj/10007/1505891536Z82T.jpg","pubdate":"2014-10-1110:08","title":"大雾再锁京城机场航班全部延误","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35315,"listimage":"http://10.0.2.2:8080/zhbj/10007/1483727032VMXT.jpg","listimage1":"http://10.0.2.2:8080/zhbj/10007/1483727032VMXT.jpg","listimage2":"http://10.0.2.2:8080/zhbj/10007/1483727032VMXT.jpg","pubdate":"2014-10-1110:03","title":"APEC会议期间调休企业员工盼同步放假","type":1,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35316,"listimage":"http://10.0.2.2:8080/zhbj/10007/1481879990BEMG.jpg","pubdate":"2014-10-1109:59","title":"机械\u201c龙马\u201d巡演17日亮相奥园","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"http://10.0.2.2:8080/zhbj/10007/14800329488K7F.jpg","listimage1":"http://10.0.2.2:8080/zhbj/10007/14800329488K7F.jpg","listimage2":"http://10.0.2.2:8080/zhbj/10007/14800329488K7F.jpg","pubdate":"2014-10-1109:54","title":"门头沟获批100套限价房","type":1,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"http://10.0.2.2:8080/zhbj/10007/14791094274LT9.jpg","pubdate":"2014-10-1109:52","title":"APEC期间净空区放带灯风筝可拘留","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35314","id":35314,"listimage":"http://10.0.2.2:8080/zhbj/10007/1478185906G9WQ.jpg","pubdate":"2014-10-1109:48","title":"今起两自住房摇号","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35117","id":35117,"listimage":"http://10.0.2.2:8080/zhbj/10007/1477262385PASS.jpg","listimage1":"http://10.0.2.2:8080/zhbj/10007/1477262385PASS.jpg","listimage2":"http://10.0.2.2:8080/zhbj/10007/1477262385PASS.jpg","pubdate":"2014-10-1109:45","title":"故宫神武门广场拟夜间开放停车","type":1,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"}],"title":"北京","topic":[{"description":"11111111","id":10101,"listimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","sort":1,"title":"北京","url":"http://10.0.2.2:8080/zhbj/10007/list_1.json"},{"description":"22222222","id":10100,"listimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","sort":2,"title":"222222222222","url":"http://10.0.2.2:8080/zhbj/10007/list_1.json"}],"topnews":[{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35301","id":35301,"pubdate":"2014-04-0814:24","title":"蜗居生活","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35125,"pubdate":"2014-04-0809:09","title":"中秋赏月","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU92.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35126,"pubdate":"2014-04-0809:09","title":"天空翱翔","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU93.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35127,"pubdate":"2014-04-0809:09","title":"感官设计","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU94.png","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"}]}
     * retcode : 200
     */

    public DataBean data;
    public int retcode;

    public static class DataBean {
        /**
         * countcommenturl : http://zhbj.qianlong.com/client/content/countComment/
         * more : /10007/list_2.json
         * news : [{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"http://10.0.2.2:8080/zhbj/10007/2078369924F9UO.jpg","pubdate":"2014-10-1113:18","title":"网上大讲堂第368期预告：义务环保人人有责","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"http://10.0.2.2:8080/zhbj/10007/1509585620ASS3.jpg","pubdate":"2014-10-1111:20","title":"马路改建为停车场车位年费高达3000元","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35313,"listimage":"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg","listimage1":"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg","listimage2":"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg","pubdate":"2014-10-1110:34","title":"北京两年内将迁出1200家工业污染企业","type":1,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35314,"listimage":"http://10.0.2.2:8080/zhbj/10007/1505891536Z82T.jpg","pubdate":"2014-10-1110:08","title":"大雾再锁京城机场航班全部延误","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35315,"listimage":"http://10.0.2.2:8080/zhbj/10007/1483727032VMXT.jpg","listimage1":"http://10.0.2.2:8080/zhbj/10007/1483727032VMXT.jpg","listimage2":"http://10.0.2.2:8080/zhbj/10007/1483727032VMXT.jpg","pubdate":"2014-10-1110:03","title":"APEC会议期间调休企业员工盼同步放假","type":1,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35316,"listimage":"http://10.0.2.2:8080/zhbj/10007/1481879990BEMG.jpg","pubdate":"2014-10-1109:59","title":"机械\u201c龙马\u201d巡演17日亮相奥园","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"http://10.0.2.2:8080/zhbj/10007/14800329488K7F.jpg","listimage1":"http://10.0.2.2:8080/zhbj/10007/14800329488K7F.jpg","listimage2":"http://10.0.2.2:8080/zhbj/10007/14800329488K7F.jpg","pubdate":"2014-10-1109:54","title":"门头沟获批100套限价房","type":1,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"http://10.0.2.2:8080/zhbj/10007/14791094274LT9.jpg","pubdate":"2014-10-1109:52","title":"APEC期间净空区放带灯风筝可拘留","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35314","id":35314,"listimage":"http://10.0.2.2:8080/zhbj/10007/1478185906G9WQ.jpg","pubdate":"2014-10-1109:48","title":"今起两自住房摇号","type":0,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35117","id":35117,"listimage":"http://10.0.2.2:8080/zhbj/10007/1477262385PASS.jpg","listimage1":"http://10.0.2.2:8080/zhbj/10007/1477262385PASS.jpg","listimage2":"http://10.0.2.2:8080/zhbj/10007/1477262385PASS.jpg","pubdate":"2014-10-1109:45","title":"故宫神武门广场拟夜间开放停车","type":1,"url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"}]
         * title : 北京
         * topic : [{"description":"11111111","id":10101,"listimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","sort":1,"title":"北京","url":"http://10.0.2.2:8080/zhbj/10007/list_1.json"},{"description":"22222222","id":10100,"listimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","sort":2,"title":"222222222222","url":"http://10.0.2.2:8080/zhbj/10007/list_1.json"}]
         * topnews : [{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35301","id":35301,"pubdate":"2014-04-0814:24","title":"蜗居生活","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35125,"pubdate":"2014-04-0809:09","title":"中秋赏月","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU92.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35126,"pubdate":"2014-04-0809:09","title":"天空翱翔","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU93.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35127,"pubdate":"2014-04-0809:09","title":"感官设计","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU94.png","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"}]
         */

        public String countcommenturl;
        public String more;
        public String title;
        public List<NewsBean> news;
        public List<TopicBean> topic;
        public List<TopnewsBean> topnews;

        public static class NewsBean implements Serializable {
            /**
             * comment : true
             * commentlist : http://10.0.2.2:8080/zhbj/10007/comment_1.json
             * commenturl : http://zhbj.qianlong.com/client/user/newComment/35319
             * id : 35311
             * listimage : http://10.0.2.2:8080/zhbj/10007/2078369924F9UO.jpg
             * pubdate : 2014-10-1113:18
             * title : 网上大讲堂第368期预告：义务环保人人有责
             * type : 0
             * url : http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html
             * listimage1 : http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg
             * listimage2 : http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg
             */

            public boolean comment;
            public String commentlist;
            public String commenturl;
            public int id;
            public String listimage;
            public String pubdate;
            public String title;
            public String type;
            public String url;
            public String listimage1;
            public String listimage2;
        }

        public static class TopicBean {
            /**
             * description : 11111111
             * id : 10101
             * listimage : http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg
             * sort : 1
             * title : 北京
             * url : http://10.0.2.2:8080/zhbj/10007/list_1.json
             */

            public String description;
            public int id;
            public String listimage;
            public int sort;
            public String title;
            public String url;
        }

        public static class TopnewsBean {
            /**
             * comment : true
             * commentlist : http://10.0.2.2:8080/zhbj/10007/comment_1.json
             * commenturl : http://zhbj.qianlong.com/client/user/newComment/35301
             * id : 35301
             * pubdate : 2014-04-0814:24
             * title : 蜗居生活
             * topimage : http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg
             * type : news
             * url : http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html
             */

            public boolean comment;
            public String commentlist;
            public String commenturl;
            public int id;
            public String pubdate;
            public String title;
            public String topimage;
            public String type;
            public String url;
        }
    }
}
