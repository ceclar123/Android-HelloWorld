package org.bond.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.bond.hello.util.ViewUtil;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        //子菜单
//        SubMenu subMenu = menu.addSubMenu(1, 1, 1, "文件");
//        subMenu.setIcon(R.drawable.clock);
//        subMenu.add(1, 2, 1, "新建");
//        subMenu.add(1, 3, 2, "打开");
//
//        subMenu = menu.addSubMenu(1, 4, 2, "编辑");
//        subMenu.add(1, 5, 1, "复制");
//        subMenu.add(1, 6, 2, "剪切");
//        subMenu.add(1, 7, 3, "粘贴");
//
//        MenuItem item = menu.add(1, 8, 3, "导航");

        //图标菜单
        this.setIconEnable(menu, true);//必须调一下
//        MenuItem item = menu.add(1, 1, 1, "菜单1");
//        item.setIcon(R.drawable.clock);
//
//        item = menu.add(1, 2, 2, "菜单2");
//        item.setIcon(R.drawable.settings);
//
//        item = menu.add(1, 3, 3, "菜单3");
//        item.setIcon(R.drawable.ic_launcher);
//
//        item = menu.add(1, 4, 4, "菜单4");
//        item.setIcon(R.drawable.calendar);


        //采用菜单配置文件
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                Toast.makeText(MainActivity.this, "" + "关于", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_settings:

                Toast.makeText(MainActivity.this, "" + "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_quit:

                Toast.makeText(MainActivity.this, "" + "退出", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 动态菜单处理,可以禁用显示
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.getItem(0);
        if (item != null) {
            item.setVisible(Calendar.getInstance().get(Calendar.SECOND) % 2 == 0 ? true : false);
        }

        return true;
    }

    /**
     * Android 4.0图标菜单显示不出来
     *
     * @param menu
     * @param enable
     */
    private void setIconEnable(Menu menu, boolean enable) {
        try {
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);

            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(MainActivity.class.toString(), e.getMessage());
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
            case R.id.btn_ctl_gridView:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.control.ControlGridView.class);
                this.startActivity(intent);
                break;
            case R.id.btn_ctl_suDoku:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.control.ControlGridView_SuDoKu.class);
                this.startActivity(intent);
                break;
            case R.id.btn_ctl_gallery:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.control.ControlGallery.class);
                this.startActivity(intent);
                break;
            case R.id.btn_ctl_http:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(this, org.bond.hello.http.Main.class);
                this.startActivity(intent);
                break;


            default:
                Toast toast = Toast.makeText(v.getContext(), "无对应的Actity", Toast.LENGTH_LONG);
                toast.show();
                break;
        }
    }
}
