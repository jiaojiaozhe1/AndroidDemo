package com.johnson.commonlibs.common_utils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangwt on 16/2/24.<br/>
 *
 * @author zhangwt
 * @version 1.0
 * @date 16/2/24
 */
public final class DateUtils {

    /**
     * long time 2 String
     * @param time
     * @return yyyy-mm-dd
     */
    public static String time2string(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        String t = simpleDateFormat.format(date);
        return t;
    }


    /**
     * long time 2 String
     * @param time
     * @return yyyy-mm-dd
     */
    public static String Time2StringHHMMSS(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        String t = simpleDateFormat.format(date);
        return t;
    }



    /**
     * 获取当前的时间
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

}
