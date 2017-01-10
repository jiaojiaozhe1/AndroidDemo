package com.wondersgroup.smile.school.app;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by zhangwentao on 2017/1/10.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
    }

    {

        PlatformConfig.setWeixin(Constant.weChatAppId, Constant.weChatKey);
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone(Constant.qqAppId, Constant.qqKey);
    }


}
