package org.bond.hello.activity.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-6-23.
 */
public class IntentResult extends Activity {
    private EditText txtMessage;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.intent_result);

        txtMessage = (EditText) this.findViewById(R.id.txtMessage);
        Button btnOK = (Button) this.findViewById(R.id.btnOK);
        Button btnCancel = (Button) this.findViewById(R.id.btnCancel);

        final Intent intent = this.getIntent();
        if (intent != null) {
            String message = intent.getStringExtra("message");
            txtMessage.setText(message);
        }

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent != null) {
                    Intent result = new Intent(Intent.ACTION_VIEW);
                    result.putExtra("message", txtMessage.getText().toString().trim());
                    setResult(Activity.RESULT_OK, result);
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent != null) {
                    setResult(Activity.RESULT_CANCELED, null);
                    finish();
                }
            }
        });

        Log.i(IntentResult.class.toString(), "onCreate初始化操作");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(IntentResult.class.toString(), "onStart界面用户可见的时候");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(IntentResult.class.toString(), "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(IntentResult.class.toString(), "onResume获取焦点");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(IntentResult.class.toString(), "onPostResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(IntentResult.class.toString(), "onPause失去焦点");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(IntentResult.class.toString(), "onStop界面不可见");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(IntentResult.class.toString(), "onDestroy系统销毁了");
    }
}