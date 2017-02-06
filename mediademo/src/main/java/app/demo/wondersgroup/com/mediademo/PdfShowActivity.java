package app.demo.wondersgroup.com.mediademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PdfShowActivity extends AppCompatActivity {
    private WebView mWebView;
    private String pdfUrl = "http://www8.cao.go.jp/okinawa/8/2012/0409-1-1.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_show);

        mWebView = (WebView) findViewById(R.id.pdf_web_view);

        showPdf();
    }

    private void showPdf() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.getSettings().setSupportZoom(true);

        mWebView.getSettings().setDomStorageEnabled(true);

        mWebView.getSettings().setAllowFileAccess(true);

//        mWebView.getSettings().setPluginState();

        mWebView.getSettings().setUseWideViewPort(true);

        mWebView.getSettings().setBuiltInZoomControls(true);

        mWebView.requestFocus();

        mWebView.getSettings().setLoadWithOverviewMode(true);

        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        mWebView.loadUrl("http://docs.google.com/gview?embedded=true&url=" +pdfUrl);
    }
}
