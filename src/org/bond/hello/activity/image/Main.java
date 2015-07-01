package org.bond.hello.activity.image;

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
 * Created by Administrator on 2015-6-28.
 */
public class Main extends Activity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.image_main);

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
            case R.id.btn_load_big_image:
                intent = new Intent();
                intent.setClass(this, org.bond.hello.activity.image.LoadBigImage.class);
                this.startActivity(intent);
                break;
            case R.id.btn_gallery_image:
                intent = new Intent();
                intent.setClass(this, org.bond.hello.activity.image.GalleryImage.class);
                this.startActivity(intent);
                break;
            case R.id.btn_draw_girl:
                intent = new Intent();
                intent.setClass(this, org.bond.hello.activity.image.DrawGirl.class);
                this.startActivity(intent);
                break;


            default:
                Toast toast = Toast.makeText(v.getContext(), "无对应的Actity", Toast.LENGTH_LONG);
                toast.show();
                break;
        }
    }
}