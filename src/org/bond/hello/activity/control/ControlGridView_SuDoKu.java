package org.bond.hello.activity.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import org.bond.hello.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015-6-20.
 */
public class ControlGridView_SuDoKu extends Activity {
    // 图片封装为一个数组
    private int[] icon = {R.drawable.address_book, R.drawable.calendar,
            R.drawable.camera, R.drawable.clock, R.drawable.games_control,
            R.drawable.messenger, R.drawable.ringtone, R.drawable.settings,
            R.drawable.speech_balloon, R.drawable.weather, R.drawable.world,
            R.drawable.youtube};
    private String[] iconName = {"通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声", "设置", "语音", "天气", "浏览器", "视频"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ctl_gridview_sudoku);
        GridView gview = (GridView) findViewById(R.id.gview_sudoku);

        //获取数据
        List<Map<String, Object>> data_list = getGridViewData();
        //数据源字段
        String[] from = {"image", "text"};
        //目标视图ID
        int[] to = {R.id.image, R.id.text};
        SimpleAdapter sim_adapter = new SimpleAdapter(this, data_list, R.layout.ctl_gridview_sudoku_item, from, to);
        //配置适配器
        gview.setAdapter(sim_adapter);
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = iconName[position];
                Toast.makeText(getBaseContext(), name + id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<Map<String, Object>> getGridViewData() {
        List<Map<String, Object>> data_list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }
}