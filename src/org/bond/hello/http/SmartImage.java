package org.bond.hello.http;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.loopj.android.image.SmartImageTask;
import com.loopj.android.image.SmartImageView;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-6-21.
 */
public class SmartImage extends Activity {
    private EditText txtUrl;
    SmartImageView img;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.http_smart_image);

        img = (SmartImageView) this.findViewById(R.id.img_1);
        txtUrl = (EditText) this.findViewById(R.id.txtUrl);
        Button btnLoad = (Button) this.findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageUrl(txtUrl.getText().toString().trim(), R.drawable.ic_launcher, R.drawable.clock, new SmartImageTask.OnCompleteListener() {
                    @Override
                    public void onComplete() {
                        Toast.makeText(SmartImage.this, "加载完成", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        //获取激活它的意图
        Intent intent = this.getIntent();
        if (intent != null) {
            Uri uri = intent.getData();
            Toast.makeText(SmartImage.this, "意图数据:" + uri.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}