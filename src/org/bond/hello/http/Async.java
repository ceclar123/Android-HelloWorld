package org.bond.hello.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;
import org.bond.hello.R;

import java.net.URLEncoder;

/**
 * Created by Administrator on 2015-6-21.
 */
public class Async extends Activity {
    private EditText txtUserName;
    private EditText txtPwd;

    private Handler txtHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg != null && msg.what == 1) {
                try {
                    byte[] data = (byte[]) msg.obj;
                    String txt = new String(data, "utf-8");
                    Toast.makeText(Async.this, "请求成功:" + txt, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(Async.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else if (msg != null && msg.what == 2) {
                try {
                    byte[] data = (byte[]) msg.obj;
                    String txt = new String(data, "utf-8");
                    Toast.makeText(Async.this, "请求错误:" + txt, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(Async.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    /**
     * https://github.com/loopj/android-async-http 异步Http
     *
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.http_async);

        txtUserName = (EditText) this.findViewById(R.id.txtUserName);
        txtPwd = (EditText) this.findViewById(R.id.txtPwd);
        Button btnGet = (Button) this.findViewById(R.id.btnGet);
        Button btnPost = (Button) this.findViewById(R.id.btnPost);
        Button btnPut = (Button) this.findViewById(R.id.btnPut);
        Button btnDelete = (Button) this.findViewById(R.id.btnDelete);

        //Get
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AsyncHttpClient client = new AsyncHttpClient();
                    String url = "http://192.168.0.101:8080/restful/UserServlet?userName=" + URLEncoder.encode(txtUserName.getText().toString().trim(), "utf-8") + "&pwd=" + URLEncoder.encode(txtPwd.getText().toString().trim(), "utf-8");

                    client.get(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            //Toast.makeText(Async.this, statusCode, Toast.LENGTH_SHORT).show();
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = responseBody;
                            txtHanlder.sendMessage(msg);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            //Toast.makeText(Async.this, statusCode, Toast.LENGTH_SHORT).show();
                            Message msg = new Message();
                            msg.what = 2;
                            msg.obj = responseBody;
                            txtHanlder.sendMessage(msg);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(Async.class.toString(), e.getMessage());
                }
            }
        });

        //Post
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AsyncHttpClient client = new AsyncHttpClient();
                    String url = "http://192.168.0.101:8080/restful/UserServlet";
                    RequestParams params = new RequestParams();
                    params.add("userName", txtUserName.getText().toString().trim());
                    params.add("pwd", txtPwd.getText().toString().trim());
                    client.post(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            //Toast.makeText(Async.this, statusCode, Toast.LENGTH_SHORT).show();
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = responseBody;
                            txtHanlder.sendMessage(msg);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            //Toast.makeText(Async.this, statusCode, Toast.LENGTH_SHORT).show();
                            Message msg = new Message();
                            msg.what = 2;
                            msg.obj = responseBody;
                            txtHanlder.sendMessage(msg);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(Async.class.toString(), e.getMessage());
                }
            }
        });
        //Put
        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AsyncHttpClient client = new AsyncHttpClient();
                    String url = "http://192.168.0.101:8080/restful/UserServlet?uid=1";
                    RequestParams params = new RequestParams();
                    params.add("userName", txtUserName.getText().toString().trim());
                    params.add("pwd", txtPwd.getText().toString().trim());
                    client.put(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            //Toast.makeText(Async.this, statusCode, Toast.LENGTH_SHORT).show();
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = responseBody;
                            txtHanlder.sendMessage(msg);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            //Toast.makeText(Async.this, statusCode, Toast.LENGTH_SHORT).show();
                            Message msg = new Message();
                            msg.what = 2;
                            msg.obj = responseBody;
                            txtHanlder.sendMessage(msg);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(Async.class.toString(), e.getMessage());
                }
            }
        });
        //Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AsyncHttpClient client = new AsyncHttpClient();
                    String url = "http://192.168.0.101:8080/restful/UserServlet?uid=1";

                    client.delete(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            //Toast.makeText(Async.this, statusCode, Toast.LENGTH_SHORT).show();
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = responseBody;
                            txtHanlder.sendMessage(msg);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            //Toast.makeText(Async.this, statusCode, Toast.LENGTH_SHORT).show();
                            Message msg = new Message();
                            msg.what = 2;
                            msg.obj = responseBody;
                            txtHanlder.sendMessage(msg);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(Async.class.toString(), e.getMessage());
                }

            }
        });
    }
}