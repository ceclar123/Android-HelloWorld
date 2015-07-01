package org.bond.hello.activity.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.bond.hello.R;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2015-6-21.
 */
public class MThreadDownload extends Activity {
    /**
     * 线程数
     */
    protected final int THREAD_QTY = 3;
    /**
     * 下载总进度
     */
    protected static int currentProgress = 0;
    /**
     * 正在下载的线程数
     */
    protected static int doingThread = 0;
    private EditText txtUrl;
    private EditText txtFileName;
    private ProgressBar progressBar1;

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(MThreadDownload.this, "下载错误:" + (String) msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(MThreadDownload.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.http_mthread_download);

        txtUrl = (EditText) this.findViewById(R.id.txtUrl);
        txtFileName = (EditText) this.findViewById(R.id.txtFileName);
        progressBar1 = (ProgressBar) this.findViewById(R.id.progressBar1);

        Button btnDownload = (Button) this.findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fileName = txtFileName.getText().toString().trim();
                final String filePath = txtUrl.getText().toString().trim();
                if (TextUtils.isEmpty(filePath)) {
                    Toast.makeText(MThreadDownload.this, "下载路径不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fileName)) {
                    Toast.makeText(MThreadDownload.this, "存放路径不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(filePath);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.setConnectTimeout(5000);
                            int respCode = conn.getResponseCode();
                            if (respCode == 200) {
                                int maxLength = conn.getContentLength();
                                progressBar1.setMax(maxLength);
                                progressBar1.setProgress(0);
                                //本地创建同样大小文件
                                RandomAccessFile raf = new RandomAccessFile(fileName, "rwd");
                                raf.setLength(maxLength);
                                raf.close();

                                int blockSize = maxLength / THREAD_QTY;
                                doingThread = THREAD_QTY;
                                for (int i = 0; i < THREAD_QTY; i++) {
                                    int begin = i * blockSize;
                                    int end = (i + 1) * blockSize;
                                    if (i == THREAD_QTY - 1) {
                                        end = maxLength;
                                    }
                                    System.out.println("线程" + (i + 1) + "开始下载");
                                    MyThread thread = new MyThread((i + 1), begin, end, fileName, filePath);
                                    thread.start();
                                }
                            } else {
                                System.out.println("服务器错误");
                                Message msg = new Message();
                                msg.what = 1;
                                msg.obj = "服务器错误:" + respCode;
                                myHandler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(MThreadDownload.class.toString(), e.getMessage());
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = e.getMessage();
                            myHandler.sendMessage(msg);
                        }
                    }
                }.start();
            }
        });
    }

    class MyThread extends Thread {
        private int threadId;
        private int beginIndex;
        private int endIndex;
        private String fileName;
        private String fileUrl;

        public MyThread(int threadId, int beginIndex, int endIndex, String fileName, String fileUrl) {
            this.threadId = threadId;
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.fileName = fileName;
            this.fileUrl = fileUrl;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(this.fileUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                //请求资源范围(开始结束为止)
                conn.setRequestProperty("Range", "bytes=" + this.beginIndex + "-" + this.endIndex);
                conn.setConnectTimeout(5000);
                //200全部资源;206部分资源
                int respCode = conn.getResponseCode();

                InputStream is = conn.getInputStream();
                RandomAccessFile raf = new RandomAccessFile(fileName, "rwd");
                raf.seek(this.beginIndex);//定位文件位置
                //循环写文件
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    raf.write(buffer, 0, len);
                    currentProgress += len;
                    //单线程更新进度
                    synchronized (MThreadDownload.this) {
                        //ProgressBar,ProgressDialog可以在子线程更新数据
                        progressBar1.setProgress(currentProgress);
                    }
                }
                is.close();
                raf.close();

                System.out.println("线程" + this.threadId + "下载完毕");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(MThreadDownload.class.toString(), e.getMessage());
                Message msg = new Message();
                msg.what = 1;
                msg.obj = e.getMessage();
                myHandler.sendMessage(msg);
            } finally {
                threadFinish();
            }
        }

        private synchronized void threadFinish() {
            doingThread--;
            if (doingThread == 0) {
                Message msg = new Message();
                msg.what = 2;
                msg.obj = "下载完成";
                myHandler.sendMessage(msg);
            }
        }
    }
}

