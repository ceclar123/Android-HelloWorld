package org.bond.hello.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-6-27.
 */
public class MusicPlayer extends Activity implements View.OnClickListener {
    private Button btnPlay;
    private Button btnStop;
    private Button btnPause;
    private Button btnExit;
    private Button btnClose;
    private Intent intent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.service_music_player);

        //采用Start开始服务,如果Activity销毁了,对应Service不会销毁,除非手动关闭
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnStop = (Button) findViewById(R.id.btn_stop);
        btnPause = (Button) findViewById(R.id.btn_pause);
        btnExit = (Button) findViewById(R.id.btn_exit);
        btnClose = (Button) findViewById(R.id.btn_close);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int op = -1;
        //隐式意图
        //intent = new Intent("org.bond.hello.service.MusicPlayerService");
        //显示意图
        intent = new Intent(this, org.bond.hello.service.MusicPlayerService.class);

        switch (v.getId()) {
            case R.id.btn_play:
                op = 1;
                break;
            case R.id.btn_stop:
                op = 2;
                break;
            case R.id.btn_pause:
                op = 3;
                break;
            case R.id.btn_close:
                this.finish();
                break;
            case R.id.btn_exit:
                op = 4;
                stopService(intent);
                this.finish();
                break;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("op", op);
        intent.putExtras(bundle);

        startService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁Activity终止服务
        if (intent != null) {
            stopService(intent);
        }
    }

}