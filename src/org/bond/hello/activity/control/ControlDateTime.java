package org.bond.hello.activity.control;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-4-6.
 */
public class ControlDateTime extends Activity {
    private String dateTimeText = null;
    private TextView tv_ctl_1 = null;
    private TimePicker tp_ctl_1 = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ctl_datetime);

        tv_ctl_1 = (TextView) this.findViewById(R.id.tv_ctl_1);
        tp_ctl_1 = (TimePicker) this.findViewById(R.id.tp_ctl_1);

        tp_ctl_1.setIs24HourView(true);
        tp_ctl_1.setCurrentHour(16);
        tp_ctl_1.setCurrentMinute(20);
        tv_ctl_1.setText(tp_ctl_1.getCurrentHour() + "时" + tp_ctl_1.getCurrentMinute() + "分");
        tp_ctl_1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                tv_ctl_1.setText(tp_ctl_1.getCurrentHour() + "时" + tp_ctl_1.getCurrentMinute() + "分");
                Log.d("time", hourOfDay + "时" + minute + "分'");
            }
        });
    }

    public void btnTime_Click(View view) {
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dateTimeText = hourOfDay + "时" + minute + "分";
            }
        }, 10, 12, true);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                tv_ctl_1.setText(dateTimeText);
            }
        });
    }

    public void btnDate_Click(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateTimeText = year + "年" + monthOfYear + "月" + dayOfMonth + "日";
            }
        }, 2015, 1, 1);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                tv_ctl_1.setText(dateTimeText);
            }
        });
        dialog.show();
    }
}