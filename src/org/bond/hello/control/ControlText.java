package org.bond.hello.control;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-4-5.
 */
public class ControlText extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ctl_text);

        //AutoCompleteTextView
        AutoCompleteTextView actv = (AutoCompleteTextView) this.findViewById(R.id.actv_ctl_1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
                new String[]{"English", "China", "Spanish", "German"});
        actv.setAdapter(adapter);

        //MultiAutoCompleteTextView
        MultiAutoCompleteTextView mactv = (MultiAutoCompleteTextView) this.findViewById(R.id.mactv_ctl_1);
        mactv.setAdapter(adapter);
        mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}