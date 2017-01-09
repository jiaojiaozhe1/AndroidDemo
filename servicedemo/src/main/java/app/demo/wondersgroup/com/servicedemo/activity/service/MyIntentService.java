package app.demo.wondersgroup.com.servicedemo.activity.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by zhangwentao on 2017/1/9.
 * service 和启动他的 活动在一个线程里面
 *
 * E/MainActivity: 线程id===1
 01-09 13:17:44.191 22536-22536/app.demo.wondersgroup.com.servicedemo E/MyIntentService: 线程id===1
 01-09 13:17:44.192 319-2394/? E/ACDB-LOADER: Error: ACDB AudProc vol returned = -19
 01-09 13:17:44.194 22536-22732/app.demo.wondersgroup.com.servicedemo E/onHandleIntent: 线程id===30842
 */

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("myIntentService");
        Log.e("MyIntentService","线程id===" + Thread.currentThread().getId());
    }

    /**
     * 执行耗时操作,并且执行完毕可以自动关闭服务
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.e("onHandleIntent","线程id===" + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","onDestroy===");
    }
}
