package org.bond.hello.widget.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import org.bond.hello.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015-4-18.
 */
public class ControlListView extends Activity {
    private EditText searchText = null;
    private ListView listView = null;
    private SimpleAdapter adapter = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ctl_listview);

        this.searchText = (EditText) this.findViewById(R.id.searchText);
        this.listView = (ListView) this.findViewById(R.id.detailList);
        List<Map<String, String>> list = this.init();

        this.adapter = new SimpleAdapter(this, list, R.layout.ctl_listview_item, new String[]{"_id", "name"}, new int[]{R.id._id, R.id.userName});
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> map = (Map<String, String>) ControlListView.this.adapter.getItem(position);
                Toast.makeText(ControlListView.this, map.get("name"), Toast.LENGTH_SHORT).show();
            }
        });
        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> map = (Map<String, String>) ControlListView.this.adapter.getItem(position);
                if (map != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setClass(ControlListView.this, ControlListView1.class);
                    intent.putExtra("title", "通讯录");
                    ControlListView.this.startActivityForResult(intent, 0);
                }
                return false;
            }
        });


        //文本框键盘事件
        this.searchText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == 0 || keyCode == 3) && event != null) {
                    Toast.makeText(ControlListView.this, event.getCharacters(), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                String message = bundle.getString("message");
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
            case RESULT_CANCELED:
                Toast.makeText(this, "取消了", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private List<Map<String, String>> init() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        for (int i = 0; i < 20; i++) {
            map = new HashMap<String, String>();
            map.put("_id", String.valueOf(i + 1));
            map.put("name", "测试用户" + (i + 1));

            list.add(map);
        }
        return list;
    }
}