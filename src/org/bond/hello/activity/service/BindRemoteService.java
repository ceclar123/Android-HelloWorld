package org.bond.hello.activity.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import org.bond.hello.R;
import org.ghost.hello.service.IService;

/**
 * Created by Administrator on 2015-6-28.
 */
public class BindRemoteService extends Activity {
    public static final String TAG = "BindRemoteService";
    private boolean mIsBound;
    private IService mBoundService;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.service_bind_remote_service);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIsBound) {
            Log.i(TAG, "解绑服务");
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    public void btnBind_Click(View view) {
        Log.i(TAG, "绑定服务");
        Toast.makeText(BindRemoteService.this, "绑定服务", Toast.LENGTH_SHORT).show();
        bindService(new Intent("org.ghost.hello.service.sayHello"), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }


    public void btnUnbind_Click(View view) {
        Log.i(TAG, "解绑服务");
        Toast.makeText(BindRemoteService.this, "解绑服务", Toast.LENGTH_SHORT).show();
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    public void btnTest_Click(View view) {
        if (mBoundService == null) {
            Toast.makeText(BindRemoteService.this, "服务未绑定", Toast.LENGTH_SHORT).show();
        } else {
            try {
                String message = mBoundService.sayHello("张三");
                Toast.makeText(BindRemoteService.this, message, Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                Log.e(TAG, "调用远程方法报错:" + e.getMessage());
            }
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mBoundService = IService.Stub.asInterface(service);
            Log.i(TAG, "服务开启连接");
        }

        public void onServiceDisconnected(ComponentName className) {
            mBoundService = null;
            Log.i(TAG, "服务断开连接");
        }
    };
}