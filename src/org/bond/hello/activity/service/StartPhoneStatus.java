package org.bond.hello.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import org.bond.hello.R;
import org.bond.hello.service.PhoneStatusService;

/**
 * Created by Administrator on 2015-6-25.
 */
public class StartPhoneStatus extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.service_start_phone_status);
    }

    public void btnStart_Click(View view) {
        //启动服务
        Intent intent = new Intent(this, PhoneStatusService.class);
        startService(intent);

        Toast.makeText(this, "启动成功", Toast.LENGTH_SHORT).show();
    }
}