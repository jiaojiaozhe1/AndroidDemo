package app.demo.wondersgroup.com.androiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import app.demo.wondersgroup.com.androiddemo.interfaces.ApiService;
import app.demo.wondersgroup.com.androiddemo.model.User;
import app.demo.wondersgroup.com.androiddemo.model.UserInfo;
import app.demo.wondersgroup.com.androiddemo.model.UserInfoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button infoBtn;
    private Button jsonInfoBtn;
    private TextView infoView;
    private TextView jsonInfoView;
    private ApiService apiService;
    private static final String BASE_URL = "http://pro.reindeerjob.com/";


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
                    Log.e("request", "body======" + response.body());
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
    }

    private void initView() {
        infoBtn = (Button) findViewById(R.id.user_info_btn);
        jsonInfoBtn = (Button) findViewById(R.id.user_info_json_btn);
        infoView = (TextView) findViewById(R.id.user_info_view);
        jsonInfoView = (TextView) findViewById(R.id.user_info_json_view);

    }

    @Override
    public void onClick(View view) {
        if (infoBtn == view) {
            requestService();
        } else if (jsonInfoBtn == view){
            getJsonData();
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
                if (response.isSuccessful()){
                    JsonObject body = response.body();
                    Log.e("body","body===" + response.body().toString());
                    String code = response.body().get("code").getAsString();
                    if (code.equals("200")){
                        JsonObject data = body.getAsJsonObject("data");
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
