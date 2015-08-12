package org.bond.hello.widget.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import org.bond.hello.R;
import org.bond.hello.service.ReceiverService;

/**
 * Created by Administrator on 2015-6-28.
 */
public class RegisterReceiver extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.service_register_receiver);
    }

    public void btnStart_Click(View view) {
        Intent intent = new Intent(this, ReceiverService.class);
        this.startService(intent);
        Toast.makeText(this, "启动服务", Toast.LENGTH_SHORT).show();
    }

    public void btnStop_Click(View view) {
        Intent intent = new Intent(this, ReceiverService.class);
        this.stopService(intent);
        Toast.makeText(this, "停止服务", Toast.LENGTH_SHORT).show();
    }

    public void btnSend_Click(View view) {
        Intent intent = new Intent("org.bond.hello.receiveService");
        intent.putExtra("msg", "李四");
        this.sendBroadcast(intent);
    }
}