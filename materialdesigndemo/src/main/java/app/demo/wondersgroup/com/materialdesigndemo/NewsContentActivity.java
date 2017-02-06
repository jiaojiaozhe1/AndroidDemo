package app.demo.wondersgroup.com.materialdesigndemo;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.johnson.commonlibs.common_utils.BaseActivity;

import app.demo.wondersgroup.com.materialdesigndemo.fragment.NewsContentFragment;

public class NewsContentActivity extends BaseActivity {


    public static void startAction(Context context, String content) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("content", content);
        context.startActivity(intent);
        Log.e("startAction","content = " + content);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_news_tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("新闻内容");
        }


        String content = getIntent().getStringExtra("content");

        Log.e("NewsContentActivity","content = " + content);

        NewsContentFragment contentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_phone);

        contentFragment.refreshContent(content);
    }
}
