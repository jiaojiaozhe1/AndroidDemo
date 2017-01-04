package app.demo.wondersgroup.com.materialdesigndemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import app.demo.wondersgroup.com.materialdesigndemo.utils.ActivityController;
import app.demo.wondersgroup.com.materialdesigndemo.utils.LogUtil;

/**
 * Created by zhangwentao on 2017/1/4.
 */

public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("BaseActivity","class==name===" + getClass().getSimpleName() + "onCreate");//获取当前类名
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
        LogUtil.e("BaseActivity","class==name===" + getClass().getSimpleName() + "onDestroy");//获取当前类名
    }
}
