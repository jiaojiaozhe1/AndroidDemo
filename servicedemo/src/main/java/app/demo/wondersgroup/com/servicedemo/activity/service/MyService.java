package app.demo.wondersgroup.com.servicedemo.activity.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import app.demo.wondersgroup.com.servicedemo.R;
import app.demo.wondersgroup.com.servicedemo.activity.ForegroundServiceActivity;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService", "onCreate");
        foregroundService();
    }

    /**
     * 开启前台服务 这样当内存资源不足的时候不会担心 服务被系统回收,这样的前台服务是有一个通知栏的界面,点击可以进入详情页
     * 可以使用startService 或者bindService 方式 所以在onCreate 中执行
     */
    private void foregroundService() {
        Intent intent = new Intent(this, ForegroundServiceActivity.class);
        PendingIntent service = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("前台服务")
                .setContentText("点击可以进入到详情页")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(service)
                .build();
        startForeground(1,notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MyService", "onBind");
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyService", "onStartCommand");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopSelf();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyService", "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MyService", "onUnbind");
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        public void setMessage() {
            Log.e("MyService", "setMessage");
        }
    }
}
