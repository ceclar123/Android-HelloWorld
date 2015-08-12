package org.bond.hello.widget.image;

import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;
import android.widget.ImageView;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-7-6.
 */
public class MergeImage extends Activity {
    private ImageView imgMerge;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.image_merge);

        imgMerge = (ImageView) this.findViewById(R.id.img_merge);
        Bitmap bitmapDown = BitmapFactory.decodeResource(getResources(), R.drawable.before0);
        Bitmap bitmapUp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        Bitmap alterBitmap = Bitmap.createBitmap(bitmapDown.getWidth(), bitmapDown.getHeight(), bitmapDown.getConfig());

        Canvas canvas = new Canvas(alterBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
        Matrix matrix = new Matrix();
        canvas.drawBitmap(bitmapDown, matrix, paint);
        canvas.drawBitmap(bitmapUp, matrix, paint);
        imgMerge.setImageBitmap(alterBitmap);
    }
}