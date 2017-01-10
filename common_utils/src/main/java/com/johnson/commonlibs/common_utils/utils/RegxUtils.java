package com.johnson.commonlibs.common_utils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangwt on 16/3/4.<br/>
 * 正则表达式
 * @author zhangwt
 * @version 1.0
 * @date 16/3/4
 */
public class RegxUtils {

    /**
     * 是否为手机号码
     * @param tel
     * @return
     */
    public static boolean isMatch(String tel) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = p.matcher(tel);
        return matcher.matches();
    }
}
