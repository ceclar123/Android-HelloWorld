package org.bond.hello.activity.http;

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
 * Created by Administrator on 2015-6-20.
 */
public class Main extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_main);

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
            case R.id.btn_load_image:
                intent = new Intent();
                intent.setClass(this, DownloadImage.class);
                this.startActivity(intent);
                break;
            case R.id.btn_html_view:
                intent = new Intent();
                intent.setClass(this, HtmlView.class);
                this.startActivity(intent);
                break;
            case R.id.btn_http_restful:
                intent = new Intent();
                intent.setClass(this, Restful.class);
                this.startActivity(intent);
                break;
            case R.id.btn_async_http:
                intent = new Intent();
                intent.setClass(this, Async.class);
                this.startActivity(intent);
                break;
            case R.id.btn_upload_file:
                intent = new Intent();
                intent.setClass(this, UploadFile.class);
                this.startActivity(intent);
                break;
            case R.id.btn_smart_image:
                intent = new Intent();
                intent.setClass(this, SmartImage.class);
                this.startActivity(intent);
                break;
            case R.id.btn_mthread_download:
                intent = new Intent();
                intent.setClass(this, MThreadDownload.class);
                this.startActivity(intent);
                break;

            default:
                Toast toast = Toast.makeText(v.getContext(), "无对应的Actity", Toast.LENGTH_LONG);
                toast.show();
                break;
        }
    }
}
