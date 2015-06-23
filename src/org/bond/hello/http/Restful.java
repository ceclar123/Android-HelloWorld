package org.bond.hello.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.bond.hello.R;
import org.bond.hello.util.CommonHttpClient;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-6-21.
 */
public class Restful extends Activity {
    private EditText txtUserName;
    private EditText txtPwd;

    private Handler txtHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg != null && msg.what == 1) {
                Toast.makeText(Restful.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.http_restful);

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
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        String rtn = "";
                        try {
                            String url = "http://192.168.0.101:8080/restful/UserServlet?userName=" + URLEncoder.encode(txtUserName.getText().toString().trim(), "utf-8") + "&pwd=" + URLEncoder.encode(txtPwd.getText().toString().trim(), "utf-8");
                            HttpGet getMethod = new HttpGet(url);
                            HttpResponse response = CommonHttpClient.getHttpClient().execute(getMethod);
                            rtn = EntityUtils.toString(response.getEntity());
                        } catch (Exception e) {
                            rtn = "异常信息:" + e.getMessage();
                        }

                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = rtn;
                        txtHanlder.sendMessage(msg);
                    }
                };
                thread.start();
            }
        });

        //Post
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        String rtn = "";
                        try {
                            String url = "http://192.168.0.101:8080/restful/UserServlet";
                            HttpPost postMethod = new HttpPost(url);
                            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
                            params.add(new BasicNameValuePair("userName", txtUserName.getText().toString().trim()));
                            params.add(new BasicNameValuePair("pwd", txtPwd.getText().toString().trim()));
                            postMethod.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                            HttpResponse response = CommonHttpClient.getHttpClient().execute(postMethod);
                            rtn = EntityUtils.toString(response.getEntity());
                        } catch (Exception e) {
                            rtn = "异常信息:" + e.getMessage();
                        }

                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = rtn;
                        txtHanlder.sendMessage(msg);
                    }
                };
                thread.start();

            }
        });
        //Put
        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        String rtn = "";
                        try {
                            String url = "http://192.168.0.101:8080/restful/UserServlet?uid=1";
                            HttpPut putMethod = new HttpPut(url);
                            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
                            params.add(new BasicNameValuePair("userName", txtUserName.getText().toString().trim()));
                            params.add(new BasicNameValuePair("pwd", txtPwd.getText().toString().trim()));
                            putMethod.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                            HttpResponse response = CommonHttpClient.getHttpClient().execute(putMethod);
                            rtn = EntityUtils.toString(response.getEntity());
                        } catch (Exception e) {
                            rtn = "异常信息:" + e.getMessage();
                        }

                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = rtn;
                        txtHanlder.sendMessage(msg);
                    }
                };
                thread.start();

            }
        });
        //Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        String rtn = "";
                        try {
                            String url = "http://192.168.0.101:8080/restful/UserServlet?uid=1";
                            HttpDelete deleteMethod = new HttpDelete(url);
                            HttpResponse response = CommonHttpClient.getHttpClient().execute(deleteMethod);
                            rtn = EntityUtils.toString(response.getEntity());
                        } catch (Exception e) {
                            rtn = "异常信息:" + e.getMessage();
                        }

                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = rtn;
                        txtHanlder.sendMessage(msg);
                    }
                };
                thread.start();

            }
        });
    }
}