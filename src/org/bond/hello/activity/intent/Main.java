package org.bond.hello.activity.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.bond.hello.R;
import org.bond.hello.activity.http.DownloadImage;
import org.bond.hello.util.ViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2015-6-22.
 */
public class Main extends Activity implements View.OnClickListener {
    private static final int SHOW_SUBACTIVITY = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.intent_main);

        List<View> views = ViewUtil.getAllChildViews(this);
        for (View item : views) {
            if (item instanceof Button) {
                item.setOnClickListener(this);
            }
        }

        //获取网络情况
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(this, "网络情况良好", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_open_normal:
                intent = new Intent();
                intent.setClass(this, DownloadImage.class);
                this.startActivity(intent);
                break;
            case R.id.btn_open_system:
                //直接指定包名类名,耦合性太高
                //intent = new Intent();
                //intent.setClassName("com.android.gallery", "com.android.camera.GalleryPicker");
                //隐式意图,描述动作行为
                //多个处理程序会弹出选择框
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                //intent.setData(Uri.parse("content://contacts/people"));
                this.startActivity(intent);
                break;
            case R.id.btn_implicit_intent:
                intent = new Intent();
                intent.setAction("org.bond.hello.action.XXX");//自定义动作
                intent.addCategory("android.intent.category.DEFAULT");//默认类型
                //intent.setData(Uri.parse("bond:1322哈哈"));
                intent.setDataAndType(Uri.parse("bond:1322哈哈"), "vnd.android.cursor.item/hello");
                this.startActivity(intent);
                break;
            case R.id.btn_send_sms:
                intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.addCategory("android.intent.category.DEFAULT");//默认类型
                intent.setData(Uri.parse("sms:13532137564"));
                this.startActivity(intent);
                break;
            case R.id.btn_activity_result:
                intent = new Intent();
                intent.setClass(this, org.bond.hello.activity.intent.IntentResult.class);
                intent.putExtra("message", "测试消息" + System.currentTimeMillis());
                //requestCode>=0的时候onActivityResult才会调用
                this.startActivityForResult(intent, SHOW_SUBACTIVITY);
                break;

            default:
                Toast toast = Toast.makeText(v.getContext(), "无对应的Actity", Toast.LENGTH_LONG);
                toast.show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (SHOW_SUBACTIVITY): {
                if (resultCode == Activity.RESULT_OK) {
                    String message = data.getStringExtra("message");
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                }
                break;
            }
            default:
                break;

        }
    }
}