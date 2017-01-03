package app.demo.wondersgroup.com.androiddemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import app.demo.wondersgroup.com.androiddemo.interfaces.ApiService;
import app.demo.wondersgroup.com.androiddemo.model.User;
import app.demo.wondersgroup.com.androiddemo.model.UserInfo;
import app.demo.wondersgroup.com.androiddemo.model.UserInfoResponse;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button infoBtn;
    private Button jsonInfoBtn;
    private Button notifyBtn;
    private TextView infoView;
    private TextView jsonInfoView;
    private ApiService apiService;
    //        private static final String BASE_URL = "http://pro.reindeerjob.com/";
    private static final String BASE_URL = "https://www.shsmile.com.cn/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRetrofit();
        initView();
        initListener();

    }

    private void requestService() {
        User user = new User();
        user.setAccountId("8a81c0605303d1d10153125299cf03c2");

        final Gson gson = new Gson();
        String param = gson.toJson(user);

        String inputStr = null;
        try {
            inputStr = URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        apiService.getUserHeadInfo(inputStr).enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if (response.isSuccessful()) {
                    LogUtil.e("request", "body======" + response.body().getData());
                    if (response.body().getCode().equals("200")) {
//                        UserInfo userInfo = gson.fromJson(response.body().getData().toString(), UserInfo.class);

//                        Log.e("request", "body======" + userInfo.getNickname());

                        UserInfo userInfo = response.body().getData();

                        infoView.setText("nickName=" + userInfo.getNickname());
                    }
                }

            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                Log.e("request", "onFailure======" + t);
            }
        });
    }

    private void initRetrofit() {
        //创建retrofit 实力 并完成相应的配置
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

    }


    private void initListener() {
        infoBtn.setOnClickListener(this);
        jsonInfoBtn.setOnClickListener(this);
        notifyBtn.setOnClickListener(this);
    }

    private void initView() {
        infoBtn = (Button) findViewById(R.id.user_info_btn);
        jsonInfoBtn = (Button) findViewById(R.id.user_info_json_btn);
        notifyBtn = (Button) findViewById(R.id.send_notify_btn);
        infoView = (TextView) findViewById(R.id.user_info_view);
        jsonInfoView = (TextView) findViewById(R.id.user_info_json_view);

    }

    @Override
    public void onClick(View view) {
        if (infoBtn == view) {

            requestService();
        } else if (jsonInfoBtn == view) {
//            getJsonData();
            User user = new User();
            user.setAccountId("hahha");
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else if (notifyBtn == view) {
            Intent intent = new Intent(this,SecondActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("有消息")
//                    .setContentText("这是一个普通的通知哦")
                    .setWhen(System.currentTimeMillis())//发送通知的时间
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                    .setDefaults(NotificationCompat.DEFAULT_ALL)//默认使用系统属性 如何振动,怎么播放通知铃声
                    .setContentIntent(pendingIntent)// 点击通知可以进入到通知详情页
                    .setPriority(NotificationCompat.PRIORITY_MAX)// 通知优先级最高
                    .setVibrate(new long[]{0,1000,1000,1000})//手机振动,第一个参数是静止时间,第二个是振动时间,第三个是静止时间,第四个是振动时间
                    .setLights(Color.GRAY,1000,1000)// 第一个参数是来通知手机提示灯颜色,第二个参数是 灯亮时间,第三个参数是灯暗时间 这样会有一闪一闪的效果
                    .setAutoCancel(true)//点击后通知栏自动消失
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("当前这个style 是用来指定可以显示很多内容的,然后如果是要显示图片的话可以通过bigPicture 来展示大图片,那么这里先把之前的contentText 内容隐藏"))
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.bg)))
                    .build();

            notificationManager.notify(1, notification);// 第一个参数是通知id
//            notificationManager.cancel(1);//  参数是要关闭的通知id

        }
    }


    public void getJsonData() {

        User user = new User();
        user.setAccountId("8a81c0605303d1d10153125299cf03c2");

        final Gson gson = new Gson();
        String param = gson.toJson(user);

        String inputStr = null;
        try {
            inputStr = URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //调用请求方

        //请求的参数是string 类型所以需要把json 对象转为string
        apiService.getUserInfo(inputStr).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {

                    LogUtil.e("body", "body===" + response.body().toString());

                    JsonObject body = response.body();
                    String code = response.body().get("code").getAsString();
                    if (code.equals("200")) {
                        JsonObject data = body.getAsJsonObject("data");

//                        //gson.fromJson(XX,XX) 第二个参数传入解析的type
//                        gson.fromJson(data.toString(),User.class);// 第二个参数是解析后的数据类型是对象
//
//                        gson.fromJson(data.toString(),new TypeToken<List<User>>(){}.getType());// 第二个参数是解析后的数据类型是一个user 的集合
//
                        jsonInfoView.setText("nickName=" + data.get("nickname").getAsString());

                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


}
