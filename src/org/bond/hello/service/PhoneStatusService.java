package org.bond.hello.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Administrator on 2015-6-25.
 */
public class PhoneStatusService extends Service {
    //Service一旦被创建,长期在后台运行,除非手动关闭
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(PhoneStatusService.class.toString(), "onCreate");

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(new MyPhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(PhoneStatusService.class.toString(), "onDestroy");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.i(PhoneStatusService.class.toString(), "onLowMemory");
    }

    private class MyPhoneListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE://空闲状态
                    break;

                case TelephonyManager.CALL_STATE_RINGING://响铃状态
                    Log.i("onCallStateChanged", "来电号码:" + incomingNumber);
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK://通话状态
                    break;
            }
        }
    }
}
