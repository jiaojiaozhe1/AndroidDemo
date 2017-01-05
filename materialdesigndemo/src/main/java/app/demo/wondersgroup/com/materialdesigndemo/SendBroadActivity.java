package app.demo.wondersgroup.com.materialdesigndemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 发送一个普通广播,然后注册一个动态广播接收器 接受普通广播
 */
public class SendBroadActivity extends AppCompatActivity {
    NormalBroadReceiver normalBroadReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broad);
        initView();
        initData();
    }

    private void initData() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wondersgroup.send.normal.broadcast");
        normalBroadReceiver = new NormalBroadReceiver();
        registerReceiver(normalBroadReceiver, intentFilter);

    }

    private void initView() {
        Button sendBroad = (Button) findViewById(R.id.send_broad_btn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.broadcast_toolbar);
        toolbar.setTitle("广播类");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        sendBroad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.wondersgroup.send.normal.broadcast");
                sendBroadcast(intent);
            }
        });
    }

    class NormalBroadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "收到普通广播", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(normalBroadReceiver);
    }
}
