package org.bond.hello.activity.control;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-4-6.
 */
public class ControlImageView extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ctl_imageview);

        ImageView img_ctl_1 = (ImageView) this.findViewById(R.id.img_ctl_1);
        img_ctl_1.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher));
        //img_ctl_1.setImageResource(R.drawable.ic_launcher);
        //img_ctl_1.setImageDrawable(Drawable.createFromPath("/mnt/sdcard/img1.png"));
        //img_ctl_1.setImageURI(Uri.parse("file://mnt/sdcard/img1.png"));
    }
}