package org.bond.hello.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import org.bond.hello.R;
import org.bond.hello.activity.http.DownloadImage;

/**
 * Created by Administrator on 2015-6-27.
 */
public class LocalService extends Service {
    public class LocalBinder extends Binder {
        /**
         * 直接返回服务对象
         *
         * @return
         */
        public LocalService getService() {
            return LocalService.this;
        }

        /**
         * 通过方法间接调用
         *
         * @param text
         */
        public void showDemo(String text) {
            showDemo(text);
        }
    }

    public final static String TAG = "BindLocalService";
    private final IBinder mBinder = new LocalBinder();
    private NotificationManager mNM;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "服务创建了onCreate");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //创建的时候显示通知
        showNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "服务开启了onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "服务销毁了");
        mNM.cancel(R.string.local_service_started);
        Toast.makeText(this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
    }

    private void showNotification() {
        CharSequence text = getText(R.string.local_service_started);

        //老的方式
        //Notification notification = new Notification(R.drawable.ic_launcher, text, System.currentTimeMillis());
        //PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, DownloadImage.class), 0);
        //notification.setLatestEventInfo(this, getText(R.string.local_service_label), text, contentIntent);

        //新的方式
        Notification.Builder builder = new Notification.Builder(LocalService.this);
        PendingIntent contentIndent = PendingIntent.getActivity(LocalService.this, 0, new Intent(LocalService.this, DownloadImage.class), PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIndent).setSmallIcon(R.drawable.ic_launcher)//设置状态栏里面的图标（小图标） 　　　　　　　　　　　　　　　　　　　　
                // .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.i5))//下拉下拉列表里面的图标（大图标） 　　　　　　　
                .setTicker("有新的提示") //设置状态栏的显示的信息
                .setWhen(System.currentTimeMillis())//设置时间发生时间
                .setAutoCancel(true)//设置可以清除
                .setContentTitle(getText(R.string.local_service_label))//设置下拉列表里的标题
                .setContentText(text)//设置上下文内容
                .setSubText("This is SubText")//子文本
                .setAutoCancel(true);//自动关闭
        Notification notification = builder.build();

        mNM.notify(R.string.local_service_started, notification);
    }

    public void showDemo(String text) {
        Toast.makeText(LocalService.this, text, Toast.LENGTH_SHORT).show();
    }
}
