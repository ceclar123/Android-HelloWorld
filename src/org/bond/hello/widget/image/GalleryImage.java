package org.bond.hello.widget.image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import org.bond.hello.R;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2015-6-28.
 */
public class GalleryImage extends Activity {
    private ImageView imageView;
    private int windowHeight;
    private int windowWidth;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.image_gallery_image);
        imageView = (ImageView) this.findViewById(R.id.img_1);

        //屏幕尺寸
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowHeight = windowManager.getDefaultDisplay().getHeight();
        windowWidth = windowManager.getDefaultDisplay().getWidth();

        //新的方式
        //Point outSize = new Point();
        //windowManager.getDefaultDisplay().getSize(outSize);
        //windowWidth = outSize.x;
        //windowHeight = outSize.y;

        //创建可修改Bitmap
        bitmap = Bitmap.createBitmap(windowWidth, windowHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);

        //画笔
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            int startX;
            int startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://第一次接触屏幕
                        //event.getRawX()获取屏幕坐标;getX获取控件相对坐标
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE://在屏幕上华东
                        int endX = (int) event.getX();
                        int endY = (int) event.getY();

                        canvas.drawLine(startX, startY, endX, endY, paint);
                        imageView.setImageBitmap(bitmap);

                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_UP://离开屏幕
                        break;

                }

                return true;
            }
        });
    }


    public void btnSelect_Click(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        this.startActivityForResult(intent, 0);
    }

    public void btnSave_Click(View view) {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Image", System.currentTimeMillis() + ".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            Toast.makeText(this, "保存图片成功", Toast.LENGTH_SHORT).show();

            //模拟系统消息通知系统sdcard重新挂载
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
            intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
            this.sendBroadcast(intent);
        } catch (Exception e) {
            Toast.makeText(this, "保存图片失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            //获取缩略图
            //Bitmap bitmap = data.getParcelableExtra("data");
            Uri uri = data.getData();//图片路径

            //图片解析配置
            BitmapFactory.Options options = new BitmapFactory.Options();
            //不去真的解析图片,只是获取图片头信息
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(uri.getPath(), options);
            int imageHeight = options.outHeight;
            int imageWidth = options.outWidth;
            Log.i(LoadBigImage.class.toString(), "高度:" + imageHeight + ";宽度:" + imageWidth);

            //根据屏幕缩放图片
            int scaleX = imageWidth / windowWidth;
            int scaleY = imageHeight / windowHeight;
            int scale = 1;
            if (scaleX > scaleY & scaleY > 1) {
                scale = scaleX;
            } else if (scaleY > scaleX & scaleX > 1) {
                scale = scaleY;
            }
            //真的解析图片
            options.inJustDecodeBounds = false;
            //采样率
            options.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath(), options);
            imageView.setImageBitmap(bitmap);
        }
    }
}