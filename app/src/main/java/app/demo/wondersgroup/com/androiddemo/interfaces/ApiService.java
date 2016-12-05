package app.demo.wondersgroup.com.androiddemo.interfaces;


import com.google.gson.JsonObject;

import app.demo.wondersgroup.com.androiddemo.model.UserInfoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhangwentao on 2016/12/2.
 * retrofit 接口定义 将需要进行服务器请求的方法写到接口中,
 */

public interface ApiService {

//    http://pro.reindeerjob.com/smile_mobile/showCoins.do?inputStr=?
//    {"accountId":"8a81c0605303d1d10153125299cf03c2"}//用户id
//    响应：
//    {
//        "code":"200",
//            "message":"sucess",
//            "data": {
//        "id":"8a81c0605303d1d10153125299ce03c1",//用户信息接口
//                "nickname":"leslie",//昵称
//                "totalCoinNum":0,//微校币总数
//                "avatar":"avatar/20160229180647654/1456740407654.png"   //头像地址
//    }
//    }

    /**
     * 使用get 请求 里面的参数是URL 中是除去baseurl 中的 具体那个请求模块的地址
     * @param param 请求参数 会已key = value 的方式拼接在URL后面 ps:inputStr=param
     * @return 返回的是 UserInfoResponse 对象  可以直接获取对象中的数据
     */

    @GET("smile_mobile/showCoins.do")
    Call<UserInfoResponse> getUserHeadInfo(@Query("inputStr") String param);


//    http://pro.reindeerjob.com/smile_mobile/showAccountInfo.do?inputStr=?
//    {"accountId":"8a81c0605303d1d10153125299cf03c2"}//用户id
//    响应：
//    {
//        "code":"200",
//            "message":"sucess",
//            "data": {
//        "id":"8a81c0605303d1d10153125299ce03c1",//用户信息id
//                "avatar":"avatar/20160229180647654/1456740407654.png",//头像地址
//                "nickname":"leslie",//昵称
//                "sex":"1",//性别 1：男，0：女
//                "areaId":null,//地址id：如：上海徐汇： “310104”
//                "areaName":null,//格式："上海 上海市 徐汇 "
//                "birthday":333648000000,//生日
//                "baseStudyStage": {//学习阶段
//            "id":"8a81c005515c6a8101517a354e5d0006",//阶段id
//                    "stageName":"工作阶段"//阶段名称
//        },...
    /**
     * 参数同上一个方法
     * @param param
     * @return 返回的是一个json 对象那么我们就可以直接通过解析json 对象来获取 数据 ps: 好处是不需要知道json对象具体的返回参数
     */
    @GET("smile_mobile/showAccountInfo.do")
    Call<JsonObject> getUserInfo(@Query("inputStr") String param);

}
