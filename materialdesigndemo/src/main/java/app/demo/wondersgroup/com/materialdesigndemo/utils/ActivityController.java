package app.demo.wondersgroup.com.materialdesigndemo.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwentao on 2017/1/4.
 * 管理activity
 */

public class ActivityController {
    public static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAllActivities(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());//杀掉当前进程
    }
}
