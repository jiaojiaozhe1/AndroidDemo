package com.johnson.commonlibs.common_utils.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zhangwt on 16/3/4.<br/>
 *
 * @author zhangwt
 * @version 1.0
 * @date 16/3/4
 */
public class StrUtils {


    public static String join(final ArrayList<String> array, String separator) {
        StringBuffer result = new StringBuffer();
        if (array != null && array.size() > 0) {
            for (String str : array) {
                result.append(str);
                result.append(separator);
            }
            result.delete(result.length() - 1, result.length());
        }
        return result.toString();
    }


    /**
     * 是否满足password 6-14位
     *
     * @param password
     * @return
     */
    public static boolean isMatchPass(String password) {
        int len = password.trim().length();
        if (len > 14 || len < 6) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
