package app.demo.wondersgroup.com.servicedemo.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.demo.wondersgroup.com.servicedemo.R;
import app.demo.wondersgroup.com.servicedemo.activity.service.MyIntentService;
import app.demo.wondersgroup.com.servicedemo.activity.service.MyService;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView contentView;
    private Button updateBtn;
    private Button startBtn;
    private Button stopBtn;
    private Button bindBtn;
    private Button unBindBtn;
    private Button startNotify;
    private EditText contentEdit;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    contentEdit.getText().clear();
                    String content = (String) msg.obj;
                    contentView.setText(content);
                }
            }
        };
    }

    private void initListener() {
        updateBtn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        bindBtn.setOnClickListener(this);
        unBindBtn.setOnClickListener(this);
        startNotify.setOnClickListener(this);
    }

    private void initView() {
        contentView = (TextView) findViewById(R.id.contentView);
        updateBtn = (Button) findViewById(R.id.update_btn);
        startBtn = (Button) findViewById(R.id.start_service);
        stopBtn = (Button) findViewById(R.id.stop_service);
        bindBtn = (Button) findViewById(R.id.bind_service);
        unBindBtn = (Button) findViewById(R.id.unbind_service);
        startNotify = (Button) findViewById(R.id.start_notify);
        contentEdit = (EditText) findViewById(R.id.content_edit);
    }


    /**
     * handler 在子线程中发送消息给主线程 中的handler 接受,并处理
     *
     * @param view
     */

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MyService.class);//普通service
//        Intent intent = new Intent(this, MyIntentService.class);//intentService
        if (view == updateBtn) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 0;
                    message.obj = contentEdit.getText().toString();
                    handler.sendMessage(message);
                }
            }).start();
        } else if (view == startBtn) {

            startService(intent);
            Log.e("MainActivity", "线程id===" + Thread.currentThread().getId());
        } else if (view == stopBtn) {
            stopService(intent);
        } else if (view == bindBtn) {
            //生命周期执行顺序 onCreate  onBind onServiceConnected setMessage

            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        } else if (view == unBindBtn) {
            unbindService(serviceConnection);
        } else if (view == startNotify) {
            startDownloadNofify();
        }
    }

    private void startDownloadNofify() {
        new DownLoadNotify().execute();
    }

    class DownLoadNotify extends AsyncTask<String, Integer, String> {
        boolean isDone = false;
        int progress = 0;
        int totoalProgress = 100;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                        while (progress < 100) {

                            progress++;
                            publishProgress(progress);
                            if (progress == 100){
                                return;
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int value = values[0].intValue();
            if (value < 100) {
                startNofify(value, "正在下载...");
            } else if (value == 100) {
                startNofify(-1, "下载完成");
            }
//
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    /**
     * 一个有下载进度的通知
     * @param progress
     * @param progressContent
     */
    private void startNofify(int progress, String progressContent) {
        Intent intent = new Intent(this, ForegroundServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        // 用来开启一个通知
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //显示的通知
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        notification.setContentTitle(progressContent);
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setContentIntent(pendingIntent);

        if (progress > 0) {
            notification.setContentText(progress + "%");// 下载进度值
            notification.setProgress(100, progress, false);// 进度条
        }

        notificationManager.notify(1, notification.build());
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("serviceConnection", "serviceConnection");
            MyService.MyBinder myBind = (MyService.MyBinder) iBinder;
            myBind.setMessage();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("onServiceDisconnected", "onServiceDisconnected");
        }
    };
}

