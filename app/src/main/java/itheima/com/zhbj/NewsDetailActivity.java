package itheima.com.zhbj;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import itheima.com.bean.NewsItemBean;

/**
 * Created by bushangkoukou on 2017/6/5.
 */

public class NewsDetailActivity extends Activity {


    @BindView(R.id.ib_menu)
    ImageButton ibMenu;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_collect)
    ImageButton ibCollect;
    @BindView(R.id.ib_share)
    ImageButton ibShare;
    @BindView(R.id.ib_textsize)
    ImageButton ibTextsize;
    @BindView(R.id.wv_web)
    WebView wvWeb;
    @BindView(R.id.pb_progress)
    ProgressBar pbProgress;
    @BindView(R.id.ib_listen)
    ImageButton ibListen;
    private SpeechSynthesizer mTts;
    private int currentItem = 2;
    private WebSettings settings;
    private NewsItemBean.DataBean.NewsBean newsBean;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
        ButterKnife.bind(this);

        //初始化讯飞语音sdk
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=59351554");
        initView();


    }

    private void initView() {

        ibMenu.setVisibility(View.GONE);
        ibBack.setVisibility(View.VISIBLE);
        ibCollect.setVisibility(View.VISIBLE);
        ibListen.setVisibility(View.VISIBLE);
        ibShare.setVisibility(View.VISIBLE);
        ibTextsize.setVisibility(View.VISIBLE);
        tvTitle.setText("详情");

        initWebView();

    }


    private void initWebView() {
        //取Intent中的参数
        newsBean = (NewsItemBean.DataBean.NewsBean) getIntent().getSerializableExtra("url");
        url = newsBean.url;
        wvWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //页面加载完成后回调
                pbProgress.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
        //配置WebView
        settings = wvWeb.getSettings();
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);//支持JavaScript
        //给html网页传递一个对象,必须在loadurl之前
        wvWeb.addJavascriptInterface(new JavaObject(), "Android");
        //默认WebView不能弹出对话框
        wvWeb.setWebChromeClient(new WebChromeClient());
        wvWeb.loadUrl(url);

    }

    @OnClick({R.id.ib_collect, R.id.ib_share, R.id.ib_textsize, R.id.ib_listen, R.id.ib_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_collect:
                collect();
                break;
            case R.id.ib_share:
                break;
            case R.id.ib_textsize:
                showTextSize();
                break;
            case R.id.ib_listen:
                //调用java代码弹出对话框
                wvWeb.loadUrl("javascript:wave()");
                parseWeb();
                break;
            case R.id.ib_back:
                finish();
                break;

        }
    }

    private void collect() {
        DbUtils utils = DbUtils.create(this, "zhbj");

        // 存储一条数据
        try {
            utils.save(newsBean);// 对象中需要String id; 创建表时以id 为主键
            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        } catch (DbException e) {
            // 抛异常，证明之前保存过这条数据
            // 取消收藏
            try {
                utils.delete(newsBean);
                Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
            } catch (DbException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }


    int tempItem = 0;

    private void showTextSize() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = new String[]{"超大号字体", "大号字体", "正常字体", "小号字体", "超小号字体"};
        builder.setSingleChoiceItems(items, currentItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tempItem = which;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //根据点击的条目位置,改变文字字体
                currentItem = tempItem;
                changeTextSize(currentItem);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    private void changeTextSize(int currentItem) {
        switch (currentItem) {
            case 0:
                settings.setTextSize(WebSettings.TextSize.LARGEST);
                break;
            case 1:
                settings.setTextSize(WebSettings.TextSize.LARGER);
                break;
            case 2:
                settings.setTextSize(WebSettings.TextSize.NORMAL);
                break;
            case 3:
                settings.setTextSize(WebSettings.TextSize.SMALLER);
                break;
            case 4:
                settings.setTextSize(WebSettings.TextSize.SMALLEST);
                break;
            default:
                break;
        }
    }

    private void parseWeb() {
        new Thread() {
            @Override
            public void run() {
                StringBuffer sb = new StringBuffer();
                try {
                    Document document = Jsoup.parse(new URL(url), 10000);
                    //获取所有节点
                    Elements elements = document.select("p");
                    //遍历节点
                    Iterator<Element> iterator = elements.iterator();
                    while (iterator.hasNext()) {
                        //获取一个节点
                        Element element = iterator.next();
                        //获取节点的文字内容
                        String text = element.text();
                        sb.append(text);
                    }
                    System.out.println(sb.toString());
                    speak(sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    private void speak(String s) {
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传 InitListener
        mTts = SpeechSynthesizer.createSynthesizer(this, null);
        //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer  类
        //设置发音人（更多在线发音人，用户可参见 附录13.2
        mTts.setParameter(SpeechConstant.VOICE_NAME, "vixm"); //设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "70");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "70");//设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在SD卡需要在AndroidManifest.xml添加写 SD卡权限
        //仅支持保存为pcm和wav格式，如果不需要保存合成音频，注释该行代码
        //mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        //3.开始合成
        mTts.startSpeaking(s, null);

    }


    class JavaObject {

        @JavascriptInterface//api 17 4.2版本以上,方法想要被js调用,必须添加此注解
        public void back() {
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTts != null && mTts.isSpeaking()) {
            mTts.stopSpeaking();
        }
    }
}
