package app.demo.wondersgroup.com.servicedemo.activity.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwentao on 2017/1/6.
 */

public class ActivityController {
    private static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAllActivityes(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
