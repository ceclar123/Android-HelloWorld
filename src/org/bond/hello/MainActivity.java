package org.bond.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.bond.hello.util.ViewUtil;

import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        List<View> views = ViewUtil.getAllChildViews(this);
        for (View item : views) {
            if (item instanceof Button) {
                item.setOnClickListener(this);
            }
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            //布局
            case R.id.btn_lay_frame:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.layout.Frame.class);
                this.startActivity(intent);
                break;

            case R.id.btn_lay_linear:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.layout.Linear.class);
                this.startActivity(intent);
                break;

            case R.id.btn_lay_absolue:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.layout.Absolute.class);
                this.startActivity(intent);
                break;
            case R.id.btn_lay_relative:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.layout.Relative.class);
                this.startActivity(intent);
                break;
            case R.id.btn_lay_table:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.layout.Table.class);
                this.startActivity(intent);
                break;
            case R.id.btn_lay_grid:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.layout.Grid.class);
                this.startActivity(intent);
                break;
            //控件
            case R.id.btn_ctl_text:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.control.ControlText.class);
                this.startActivity(intent);
                break;
            case R.id.btn_ctl_button:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.control.ControlButton.class);
                this.startActivity(intent);
                break;
            case R.id.btn_ctl_imageView:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.control.ControlImageView.class);
                this.startActivity(intent);
                break;
            case R.id.btn_ctl_dateTime:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.control.ControlDateTime.class);
                this.startActivity(intent);
                break;
            case R.id.btn_ctl_webView:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.control.ControlWebView.class);
                this.startActivity(intent);
                break;
            case R.id.btn_ctl_spinner:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.control.ControlSpinner.class);
                this.startActivity(intent);
                break;
            case R.id.btn_ctl_listView:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.control.ControlListView.class);
                this.startActivity(intent);
                break;


            default:
                Toast toast = Toast.makeText(v.getContext(), "无对应的Actity", Toast.LENGTH_LONG);
                toast.show();
                break;
        }
    }
}
