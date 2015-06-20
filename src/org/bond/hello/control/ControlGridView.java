package org.bond.hello.control;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import org.bond.hello.R;
import org.bond.hello.util.Maker;

/**
 * Created by Administrator on 2015-6-20.
 */
public class ControlGridView extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ctl_gridview);

        GridView gridView1 = (GridView) this.findViewById(R.id.gridView1);


        //gridView1.setAdapter(getSimpleCursorAdapter());
        gridView1.setAdapter(getArrayAdapterArray());
    }

    /**
     * 简单游标适配器(读取手机联系人)
     *
     * @return
     */
    private SimpleCursorAdapter getSimpleCursorAdapter() {
        String[] from = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
        int[] to = new int[]{android.R.id.text1};
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        return new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    /**
     * 数组单列适配器
     *
     * @return
     */
    private ArrayAdapter<String> getArrayAdapterArray() {
        Maker maker = new Maker();
        String[] names = new String[100];
        for (int i = 0; i < names.length; i++) {
            names[i] = maker.makeRealNames(1);
        }
        return new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
    }
}