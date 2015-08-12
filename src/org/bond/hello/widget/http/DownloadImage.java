package org.bond.hello.widget.http;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.bond.hello.R;
import org.bond.hello.util.CommonHttpClient;

/**
 * Created by Administrator on 2015-6-20.
 */
public class DownloadImage extends Activity {
    private ImageView img;
    private EditText txtUrl;

    private Handler imgHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            if (msg.what == 1) {
                Bitmap bitmap = (Bitmap) msg.obj;
                img.setImageBitmap(bitmap);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.http_download_image);

        img = (ImageView) this.findViewById(R.id.img_1);
        txtUrl = (EditText) this.findViewById(R.id.txtUrl);
        Button btnLoad = (Button) this.findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = txtUrl.getText().toString().trim();
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(DownloadImage.this, "图片路径不正确", Toast.LENGTH_SHORT).show();
                } else {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                HttpGet getMethod = new HttpGet(url);
                                HttpResponse response = CommonHttpClient.getHttpClient().execute(getMethod);
                                if (response.getStatusLine().getStatusCode() == 200.) {
                                    byte[] data = EntityUtils.toByteArray(response.getEntity());
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                                    Message message = new Message();
                                    message.what = 1;
                                    message.obj = bitmap;
                                    imgHandler.sendMessage(message);
                                } else {
                                    System.out.println(response.getStatusLine().getStatusCode());
                                    Log.e(DownloadImage.class.toString(), String.valueOf(response.getStatusLine().getStatusCode()));
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(DownloadImage.class.toString(), e.getMessage());
                            }
                        }
                    };
                    thread.start();
                }
            }
        });
    }
}