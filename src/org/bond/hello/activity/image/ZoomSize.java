package org.bond.hello.activity.image;

import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import org.bond.hello.R;

import java.util.Random;

/**
 * Created by Administrator on 2015-7-2.
 */
public class ZoomSize extends Activity {
    private ImageView imgUp;
    private ImageView imgDown;
    private Bitmap bitMap;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.image_zoom_size);

        imgUp = (ImageView) this.findViewById(R.id.img_up);
        imgDown = (ImageView) this.findViewById(R.id.img_down);

        bitMap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        imgUp.setImageBitmap(bitMap);
    }

    public void btnDecrease_Click(View view) {
        Bitmap alterBitmap = Bitmap.createBitmap(2 * bitMap.getWidth(), bitMap.getHeight(), bitMap.getConfig());
        Canvas canvas = new Canvas(alterBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        Matrix matrix = new Matrix();
        matrix.setValues(new float[]{
                2.0f, 0, 0,
                0, 1, 0,
                0, 0, 1
        });
        canvas.drawBitmap(bitMap, matrix, paint);
        imgDown.setImageBitmap(alterBitmap);
    }

    public void btnEnlarge_Click(View view) {
        Bitmap alterBitmap = Bitmap.createBitmap(bitMap.getWidth() / 2, bitMap.getHeight(), bitMap.getConfig());
        Canvas canvas = new Canvas(alterBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        Matrix matrix = new Matrix();
        matrix.setValues(new float[]{
                0.5f, 0, 0,
                0, 1, 0,
                0, 0, 1
        });
        canvas.drawBitmap(bitMap, matrix, paint);
        imgDown.setImageBitmap(alterBitmap);
    }

    public void btnStretching_Click(View view) {
        Bitmap alterBitmap = Bitmap.createBitmap(4 * bitMap.getWidth(), 4 * bitMap.getHeight(), bitMap.getConfig());
        Canvas canvas = new Canvas(alterBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        Matrix matrix = new Matrix();
        matrix.setScale(4.0f, 4.0f);
        canvas.drawBitmap(bitMap, matrix, paint);
        imgDown.setImageBitmap(alterBitmap);
    }

    public void btnRotate_Click(View view) {
        Bitmap alterBitmap = Bitmap.createBitmap(bitMap.getWidth(), bitMap.getHeight(), bitMap.getConfig());
        Canvas canvas = new Canvas(alterBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);//消除锯齿,图片效果更好一点
        Matrix matrix = new Matrix();
        //matrix.setRotate(17f);//0-360(以左上角为圆心)
        Random rd = new Random();
        matrix.setRotate(rd.nextInt(360), bitMap.getWidth() / 2, bitMap.getHeight() / 2);//指定圆心
        canvas.drawBitmap(bitMap, matrix, paint);
        imgDown.setImageBitmap(alterBitmap);
    }
}