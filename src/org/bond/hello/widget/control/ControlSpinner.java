package org.bond.hello.widget.control;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import org.bond.hello.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-4-18.
 */
public class ControlSpinner extends Activity {
    private ArrayAdapter<CharSequence> adapterColor = null;
    private ArrayAdapter<CharSequence> adapterEdu = null;

    private List<CharSequence> listEdu = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ctl_spinner);

        Spinner spinnerColor = (Spinner) this.findViewById(R.id.spinnerColor);
        Spinner spinnerEdu = (Spinner) this.findViewById(R.id.spinnerEdu);

        //颜色
        this.adapterColor = ArrayAdapter.createFromResource(this, R.array.color, android.R.layout.simple_spinner_item);
        this.adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(this.adapterColor);

        //学历
        this.listEdu = new ArrayList<CharSequence>();
        this.listEdu.add("高中");
        this.listEdu.add("中专");
        this.listEdu.add("大专");
        this.listEdu.add("本科");
        this.listEdu.add("硕士");
        this.listEdu.add("博士");

        this.adapterEdu = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, this.listEdu);
        this.adapterEdu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdu.setAdapter(this.adapterEdu);
    }
}