package org.bond.hello.activity.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.bond.hello.R;
import org.bond.hello.service.LocalService;

/**
 * Created by Administrator on 2015-6-27.
 */
public class BindLocalService extends Activity {
    private boolean mIsBound;
    private LocalService mBoundService;

    public BindLocalService() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.service_bind_local_service);

        //才能绑定方式,如果Activity销毁了,那么对应的Service也会销毁
        Button btnBind = (Button) findViewById(R.id.btn_bind);
        btnBind.setOnClickListener(mBindListener);

        Button btnUnbind = (Button) findViewById(R.id.btn_unbind);
        btnUnbind.setOnClickListener(mUnbindListener);

        Button btn_text = (Button) this.findViewById(R.id.btn_text);
        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBoundService == null) {
                    Toast.makeText(BindLocalService.this, "服务没有绑定", Toast.LENGTH_SHORT).show();
                } else {
                    mBoundService.showDemo("Hello World张-三");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停掉服务
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        /**
         * 服务成功绑定调用
         * @param className
         * @param service
         */
        public void onServiceConnected(ComponentName className, IBinder service) {
            mBoundService = ((LocalService.LocalBinder) service).getService();
            Toast.makeText(BindLocalService.this, R.string.local_service_connected, Toast.LENGTH_SHORT).show();
        }

        /**
         * 服务失去绑定,程序异常终止
         * @param className
         */
        public void onServiceDisconnected(ComponentName className) {
            mBoundService = null;
            Toast.makeText(BindLocalService.this, R.string.local_service_disconnected, Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mBindListener = new View.OnClickListener() {
        public void onClick(View v) {
            bindService(new Intent(BindLocalService.this, LocalService.class), mConnection, Context.BIND_AUTO_CREATE);
            mIsBound = true;
        }
    };

    private View.OnClickListener mUnbindListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (mIsBound) {
                // Detach our existing connection.
                unbindService(mConnection);
                mIsBound = false;
            }
        }
    };

}