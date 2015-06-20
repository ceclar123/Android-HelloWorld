package org.bond.hello.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
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

        //邮箱文本框注册弹出菜单
        EditText txtEmail = (EditText) this.findViewById(R.id.txtEmail);
        registerForContextMenu(txtEmail);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("弹出菜单");
        menu.add(1, 1, 1, "全选");
        menu.add(1, 2, 2, "复制");
        menu.add(1, 3, 3, "剪切");
        menu.add(1, 4, 4, "粘贴");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //return super.onContextItemSelected(item);
        if (item.getItemId() == 1) {
            Toast.makeText(this, "哈哈", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}