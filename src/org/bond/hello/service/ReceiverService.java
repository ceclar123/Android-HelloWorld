package org.bond.hello.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2015-6-28.
 */
public class ReceiverService extends Service {
    public static final String TAG = "ReceiverService";
    private MyReceiver receiver;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "创建服务onCreate");
        //注册接收器
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("org.bond.hello.receiveService");
        this.registerReceiver(receiver, filter);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "销毁服务onDestroy");
        //反注册接收器
        if (receiver != null) {
            this.unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }

    private void showMemo(String msg) {
        Toast.makeText(ReceiverService.this, "服务内部方法:" + msg, Toast.LENGTH_SHORT).show();
    }

    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "广播接收器：onReceive");
            String msg = intent.getStringExtra("msg");
            showMemo(msg);
        }
    }
}
