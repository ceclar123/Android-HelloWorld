package org.bond.hello.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.bond.hello.R;
import org.bond.hello.util.ViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2015-6-25.
 */
public class Main extends Activity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.service_main);

        List<View> views = ViewUtil.getAllChildViews(this);
        for (View item : views) {
            if (item instanceof Button) {
                item.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_phone_status:
                intent = new Intent();
                intent.setClass(this, org.bond.hello.activity.service.StartPhoneStatus.class);
                this.startActivity(intent);
                break;
            case R.id.btn_music_service:
                intent = new Intent();
                intent.setClass(this, org.bond.hello.activity.service.MusicPlayer.class);
                this.startActivity(intent);
                break;
            case R.id.btn_bind_service:
                intent = new Intent();
                intent.setClass(this, org.bond.hello.activity.service.BindLocalService.class);
                this.startActivity(intent);
                break;
            case R.id.btn_remote_service:
                intent = new Intent();
                intent.setClass(this, org.bond.hello.activity.service.BindRemoteService.class);
                this.startActivity(intent);
                break;
            case R.id.btn_register_receiver:
                intent = new Intent();
                intent.setClass(this, org.bond.hello.activity.service.RegisterReceiver.class);
                this.startActivity(intent);
                break;


            default:
                Toast toast = Toast.makeText(v.getContext(), "无对应的Actity", Toast.LENGTH_LONG);
                toast.show();
                break;
        }
    }
}