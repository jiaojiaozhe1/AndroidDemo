package com.johnson.commonlibs.common_utils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangwt on 16/3/4.<br/>
 * 正则表达式
 *
 * @author zhangwt
 * @version 1.0
 * @date 16/3/4
 */
public class RegxUtils {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private static final String PHONE_PATTERN = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    private static final String PWD_PATTERN  = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    /**
     * 是否为手机号码
     *
     * @param tel
     * @return
     */
    public static boolean validatePhone(String tel) {
        Pattern p = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = p.matcher(tel);
        return matcher.matches();
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * @prama: str 要判断是否包含特殊字符的目标字符串
     */

    public static boolean compileExChar(String str) {
        Pattern pattern = Pattern.compile(PWD_PATTERN);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();

    }
}
