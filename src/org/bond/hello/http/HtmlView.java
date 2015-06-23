package org.bond.hello.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.bond.hello.R;
import org.bond.hello.util.HttpURLConnectionUtil;

/**
 * Created by Administrator on 2015-6-21.
 */
public class HtmlView extends Activity {
    private EditText txtContent;
    private Handler txtHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            if (msg != null && msg.what == 1) {
                txtContent.setText((String) msg.obj);
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.http_html_view);

        final EditText txtUrl = (EditText) this.findViewById(R.id.txtUrl);
        Button btnLoad = (Button) this.findViewById(R.id.btnLoad);
        txtContent = (EditText) this.findViewById(R.id.txtContent);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        String url = txtUrl.getText().toString().trim();
                        if (TextUtils.isEmpty(url)) {
                            Toast.makeText(HtmlView.this, "网站不正确", Toast.LENGTH_SHORT).show();
                        } else {
                            String result = "";
                            try {
                                result = HttpURLConnectionUtil.doGet(url, null);
                            } catch (Exception e) {
                                result = e.getMessage();
                                e.printStackTrace();
                                Log.e(HtmlView.class.toString(), e.getMessage());
                            }
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = result;
                            txtHandler.sendMessage(msg);
                        }
                    }
                };
                thread.start();
            }
        });
    }
}