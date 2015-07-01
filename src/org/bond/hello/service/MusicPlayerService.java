package org.bond.hello.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import org.bond.hello.R;

import java.io.IOException;

/**
 * Created by Administrator on 2015-6-27.
 */
public class MusicPlayerService extends Service {
    private static final String TAG = " MusicPlayerService";
    private MediaPlayer mediaPlayer;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "服务创建了onCreate");
        Toast.makeText(this, "show media player", Toast.LENGTH_SHORT).show();

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.hongri);
            mediaPlayer.setLooping(false);
        }
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "服务销毁了onDestroy");
        Toast.makeText(this, "stop media player", Toast.LENGTH_SHORT);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    /**
     * START_NOT_STICKY
     * 如果系统在onStartCommand()方法返回之后杀死这个服务，那么直到接受到新的Intent对象，这个服务才会被重新创建。这是最安全的选项，用来避免在不需要的时候运行你的服务。
     * <p/>
     * START_STICKY
     * 如果系统在onStartCommand()返回后杀死了这个服务，系统就会重新创建这个服务并且调用onStartCommand()方法，但是它不会重新传递最后的Intent对象，系统会用一个null的Intent对象来调用onStartCommand()方法，在这个情况下，除非有一些被发送的Intent对象在等待启动服务。这适用于不执行命令的媒体播放器（或类似的服务），它只是无限期的运行着并等待工作的到来。
     * <p/>
     * START_REDELIVER_INTENT
     * 如果系统在onStartCommand()方法返回后，系统就会重新创建了这个服务，并且用发送给这个服务的最后的Intent对象调用了onStartCommand()方法。任意等待中的Intent对象会依次被发送。这适用于那些应该立即恢复正在执行的工作的服务，如下载文件。
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "服务开启了onStartCommand");
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                int op = bundle.getInt("op");
                switch (op) {
                    case 1:
                        play();
                        break;
                    case 2:
                        stop();
                        break;
                    case 3:
                        pause();
                        break;
                }
            }
        }
        return START_NOT_STICKY;
    }

    public void play() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            Log.v(TAG, "play");
        }
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Log.v(TAG, "pause");
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
                mediaPlayer.prepare();
                Log.v(TAG, "stop");
            } catch (IOException ex) {
                ex.printStackTrace();
                Log.e(TAG, ex.getMessage());
            }
        }
    }

}
