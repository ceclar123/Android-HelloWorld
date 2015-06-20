package org.bond.hello.layout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-4-5.
 */
public class Linear extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_linear);

        //提交按钮
        Button btnSubmit = (Button) this.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText txtUserName = (EditText) v.getRootView().findViewById(R.id.txtUserName);
                    EditText txtPassword = (EditText) v.getRootView().findViewById(R.id.txtPassword);

                    Log.d("userName", txtUserName.getText().toString());
                    Log.d("password", txtPassword.getText().toString());

                    Toast toast = Toast.makeText(v.getContext(), txtUserName.getText().toString(), Toast.LENGTH_LONG);
                    toast.show();
                } catch (Exception ex) {
                    Log.e("btnSubmit", ex.getMessage() + ex.getStackTrace());

                    Toast toast = Toast.makeText(v.getContext(), ex.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}