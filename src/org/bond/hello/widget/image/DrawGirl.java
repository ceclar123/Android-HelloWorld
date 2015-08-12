package org.bond.hello.widget.image;

import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-7-1.
 */
public class DrawGirl extends Activity {
    private ImageView imgBefore;
    private ImageView imgAfter;
    private Bitmap modifyBitmap;
    private Canvas canvas;
    private Paint paint;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.image_draw_girl);

        imgAfter = (ImageView) this.findViewById(R.id.img_after);
        imgBefore = (ImageView) this.findViewById(R.id.img_before);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;

        //只读图片
        Bitmap bitmapBefore = BitmapFactory.decodeResource(getResources(), R.drawable.before0, options);
        Bitmap bitmapAfter = BitmapFactory.decodeResource(getResources(), R.drawable.after0, options);

        //可修改图片(大图片需要做坐标转换)
        modifyBitmap = Bitmap.createBitmap(bitmapBefore.getWidth(), bitmapBefore.getHeight(), bitmapBefore.getConfig());

        imgAfter.setImageBitmap(bitmapAfter);
        imgBefore.setImageBitmap(modifyBitmap);

        canvas = new Canvas(modifyBitmap);
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmapBefore, new Matrix(), paint);

        imgBefore.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://第一次接触屏幕
                        //event.getRawX()获取屏幕坐标;getX获取控件相对坐标
                        break;
                    case MotionEvent.ACTION_MOVE://在屏幕上华东
                        int endX = (int) event.getX();
                        int endY = (int) event.getY();

                        for (int i = -8; i <= 8; i++) {
                            int newEndX = endX;
                            int newEndY = endY;

                            for (int j = -8; j <= 8; j++) {
                                if (i + endX < 0) {
                                    newEndX = 0;
                                } else if (i + endX > modifyBitmap.getWidth()) {
                                    newEndX = modifyBitmap.getWidth();
                                } else {
                                    newEndX = i + endX;
                                }

                                if (j + endY < 0) {
                                    newEndY = 0;
                                } else if (j + endY > modifyBitmap.getHeight()) {
                                    newEndY = modifyBitmap.getHeight();
                                } else {
                                    newEndY = j + endY;
                                }
                                modifyBitmap.setPixel(newEndX, newEndY, Color.TRANSPARENT);
                            }
                        }
                        imgBefore.setImageBitmap(modifyBitmap);
                        break;
                    case MotionEvent.ACTION_UP://离开屏幕
                        break;

                }

                return true;
            }
        });
    }
}