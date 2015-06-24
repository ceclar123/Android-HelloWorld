package org.bond.hello.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2015-6-24.
 */
public class OutCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //获取广播数据
        String number = this.getResultData();
        Log.i(OutCallReceiver.class.toString(), "外拨电话接收器" + number);
        //处理数据
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String ipNumber = sp.getString("ipNumber", "");
        String newNumber = ipNumber + number;

        //设置广播数据
        this.setResultData(newNumber);
        Toast.makeText(context, newNumber, Toast.LENGTH_LONG).show();
        //终止广播,短信拦截
        //this.abortBroadcast();
    }
}
