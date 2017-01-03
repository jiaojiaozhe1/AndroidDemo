package app.demo.wondersgroup.com.androiddemo.model;

/**
 * Created by zhangwentao on 2016/12/5.
 */
//
//"code":"200",
//        "message":"sucess",
//        "data": {
//        "id":"8a81c0605303d1d10153125299ce03c1",//用户信息接口
//        "nickname":"leslie",//昵称
//        "totalCoinNum":0,//微校币总数
//        "avatar":"avatar/20160229180647654/1456740407654.png"   //头像地址
//        }

public class UserInfo {
    private String id;
    private String nickname;
    private int totalCoinNum;
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname == null ? "内容为空" : nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getTotalCoinNum() {
        return totalCoinNum;
    }

    public void setTotalCoinNum(int totalCoinNum) {
        this.totalCoinNum = totalCoinNum;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
