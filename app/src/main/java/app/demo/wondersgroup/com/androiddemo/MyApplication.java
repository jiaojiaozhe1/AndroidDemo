package app.demo.wondersgroup.com.androiddemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangwentao on 2016/12/8.
 */

public class MyApplication extends Application {

    private static MyApplication instance;//获取全局的application对象
    private static Context mContext;//获取全局的 context

    public static Context getContext() {
        return mContext;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        mContext = getApplicationContext();
    }
}
