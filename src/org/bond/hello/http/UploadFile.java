package org.bond.hello.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;
import org.bond.hello.R;

import java.io.File;

/**
 * Created by Administrator on 2015-6-21.
 */
public class UploadFile extends Activity {
    private EditText txtFilePath;
    private Handler txtHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg != null && msg.what == 1) {
                try {
                    byte[] data = (byte[]) msg.obj;
                    String txt = new String(data, "utf-8");
                    Toast.makeText(UploadFile.this, "上传成功:" + txt, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(UploadFile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else if (msg != null && msg.what == 2) {
                try {
                    byte[] data = (byte[]) msg.obj;
                    String txt = new String(data, "utf-8");
                    Toast.makeText(UploadFile.this, "上传错误:" + txt, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(UploadFile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.http_upload);

        txtFilePath = (EditText) this.findViewById(R.id.txtFilePath);
        Button btnUpload = (Button) this.findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filePath = txtFilePath.getText().toString().trim();
                if (TextUtils.isEmpty(filePath)) {
                    Toast.makeText(UploadFile.this, "文件路径不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    String url = "http://192.168.0.101:8080/restful/UploadFile";
                    File file = new File(filePath);
                    if (false == file.exists()) {
                        Toast.makeText(UploadFile.this, "指定文件不存在", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            RequestParams paras = new RequestParams();
                            paras.put("file", file);
                            AsyncHttpClient client = new AsyncHttpClient();
                            client.post(url, paras, new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    Message msg = new Message();
                                    msg.what = 1;
                                    msg.obj = responseBody;
                                    txtHanlder.sendMessage(msg);
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                    Message msg = new Message();
                                    msg.what = 2;
                                    msg.obj = responseBody;
                                    txtHanlder.sendMessage(msg);
                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(UploadFile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}