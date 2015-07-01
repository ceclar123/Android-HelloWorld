package org.bond.hello.activity.control;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import org.bond.hello.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015-4-18.
 */
public class ControlListView1 extends ListActivity {
    private SimpleAdapter adapter = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Map<String, String>> list = this.init();
        this.adapter = new SimpleAdapter(this, list, R.layout.ctl_listview_item, new String[]{"_id", "name"}, new int[]{R.id._id, R.id.userName});
        this.setListAdapter(adapter);
        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> map = (Map<String, String>) ControlListView1.this.adapter.getItem(position);
                if (map != null) {
                    Intent intent = new Intent(ControlListView1.this, ControlListView.class);
                    intent.putExtra("message", map.get("name"));
                    ControlListView1.this.setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }


    private List<Map<String, String>> init() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        for (int i = 0; i < 20; i++) {
            map = new HashMap<String, String>();
            map.put("_id", String.valueOf(i + 1));
            map.put("name", "张三" + (i + 1));

            list.add(map);
        }
        return list;
    }
}