package org.bond.hello.widget.receiver;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-6-24.
 */
public class IpNumberSet extends Activity {
    private EditText txtNumber;
    SharedPreferences sp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.receiver_ip_dial);
        txtNumber = (EditText) this.findViewById(R.id.txtNumber);

        sp = this.getSharedPreferences("config", Context.MODE_PRIVATE);
        String ipNumber = sp.getString("ipNumber", "");
        txtNumber.setText(ipNumber);
    }

    public void btnSet_Click(View view) {
        String ipNumber = txtNumber.getText().toString().trim();
        if (TextUtils.isEmpty(ipNumber)) {
            Toast.makeText(IpNumberSet.this, "号码必须设置", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("ipNumber", ipNumber);
            editor.commit();

            Toast.makeText(IpNumberSet.this, "设置成功", Toast.LENGTH_SHORT).show();
        }
    }
}