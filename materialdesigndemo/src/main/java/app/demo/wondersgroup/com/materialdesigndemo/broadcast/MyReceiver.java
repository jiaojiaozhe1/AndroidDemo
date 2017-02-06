package app.demo.wondersgroup.com.materialdesigndemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.johnson.commonlibs.common_utils.utils.LogUtil;

import app.demo.wondersgroup.com.materialdesigndemo.SendBroadActivity;

/**
 * 接受一个网络变化广播,然后启动activity 测试发送普通广播功能
 */

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
//        Toast.makeText(context, "收到广播", Toast.LENGTH_LONG).show();
//        LogUtil.e("onReceive", "net changed.....");
//        Intent broadIntent = new Intent(context, SendBroadActivity.class);
//        broadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(broadIntent);

    }
}
