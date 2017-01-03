package app.demo.wondersgroup.com.netstatechange;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NetWorkReceiver netWorkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button netStateBtn = (Button) findViewById(R.id.net_state_btn);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        netWorkReceiver = new NetWorkReceiver();
        registerReceiver(netWorkReceiver,intentFilter);
    }

    class NetWorkReceiver extends BroadcastReceiver {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceive(Context context, Intent intent) {

            //监听wifi 开关 的开与关，与wifi 链接无关
            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                int intExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                switch (intExtra) {
                    case WifiManager.WIFI_STATE_DISABLED:
                        Log.e("检测wifi ", "网络没有链接");
                        break;
                    case WifiManager.WIFI_STATE_DISABLING:
                        break;
                    case WifiManager.WIFI_STATE_ENABLED:
                        Log.e("检测wifi", "网络链接");
                        break;
                    case WifiManager.WIFI_STATE_ENABLING:
                        break;
                }
            }
            //监听网络状态是否链接
            if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (null != parcelableExtra) {
                    NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                    NetworkInfo.State state = networkInfo.getState();
                    if (state == NetworkInfo.State.CONNECTED) {
                        Log.e("是否有网络", "w网络链接");
                    } else {
                        Log.e("是否有网络", "网络没有链接");
                    }
                }
            }

            //监听网络状态，包括链接网络，wifi,还是手机 这个可以单独使用不用上面两个
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null) {
                    if (networkInfo.isConnected()) {
                        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            Log.e("ConnectivityManager", "wifi 已经链接");
                        } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            Log.e("ConnectivityManager", "手机网络链接 已经链接");
                        }
                    } else {
                        Log.e("ConnectivityManager", "网络没有链接");

                    }
                } else {
                    Log.e("ConnectivityManager", "网络没有链接");

                }

            }


        }
    }


}
