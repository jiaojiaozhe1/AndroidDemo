package app.demo.wondersgroup.com.androiddemo;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import app.demo.wondersgroup.com.androiddemo.model.User;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();

//        User user = getIntent().getParcelableExtra("user");
//        LogUtil.e("user id ==", "id==" + user.getAccountId());

    }

    private void initView() {
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);//webview.getSettings() 是用来设置浏览器属性=== 支持js
        webView.getSettings().setUseWideViewPort(true);//自适应屏幕显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //webview在安卓5.0之前默认允许其加载混合网络协议内容
            // 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//支持混合内容
        }
        webView.setWebViewClient(new WebViewClientBase());// 使用webview 来加载网页 而不是系统默认的浏览器
        webView.setWebChromeClient(new WebViewChromeBase());
        webView.loadUrl("http://www.kxuejie.com");

    }

    class WebViewClientBase extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            LogUtil.e("page", "start");
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            LogUtil.e("page", "finished");
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            LogUtil.e("client", "shouldOverrideUrlLoading==" );
            view.loadUrl("http://www.baidu.com");
            return true;
        }
    }

    class WebViewChromeBase extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            LogUtil.e("chrom", "progress==" + newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }
}
