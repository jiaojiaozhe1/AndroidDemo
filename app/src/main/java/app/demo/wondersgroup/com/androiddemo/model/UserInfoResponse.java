package app.demo.wondersgroup.com.androiddemo.model;

/**
 * Created by zhangwentao on 2016/12/5.
 */

public class UserInfoResponse {
    private String code;
    private String message;
    private UserInfo data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }
}
