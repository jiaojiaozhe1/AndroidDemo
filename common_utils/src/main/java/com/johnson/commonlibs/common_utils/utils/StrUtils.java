package com.johnson.commonlibs.common_utils.utils;

/**
 * Created by zhangwt on 16/3/4.<br/>
 *
 * @author zhangwt
 * @version 1.0
 * @date 16/3/4
 */
public class StrUtils {

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
}
