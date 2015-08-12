package org.bond.hello.widget.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-6-28.
 */
public class LoadBigImage extends Activity {
    private ImageView imageView;
    private int windowHeight;
    private int windowWidth;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.image_load_big_image);
        imageView = (ImageView) this.findViewById(R.id.img_1);

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowHeight = windowManager.getDefaultDisplay().getHeight();
        windowWidth = windowManager.getDefaultDisplay().getWidth();

        //新的方式
        //Point outSize = new Point();
        //windowManager.getDefaultDisplay().getSize(outSize);
        //windowWidth = outSize.x;
        //windowHeight = outSize.y;
    }

    //ExifInterface类可以读取设置图片的附属信息
    public void btnLoad_Click(View view) {
        //老的方式,很容易内存不足,卡死程序
        //Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.raw.shanghai);
        //imageView.setImageBitmap(bitmap);

        //图片解析配置
        BitmapFactory.Options options = new BitmapFactory.Options();
        //不去真的解析图片,只是获取图片头信息
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(this.getResources(), R.raw.shanghai, options);
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
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.raw.shanghai, options);
        imageView.setImageBitmap(bitmap);
    }
}