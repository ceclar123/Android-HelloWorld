package org.bond.hello.provider;

import android.content.Context;
import android.content.Intent;
import org.bond.hello.model.TreeElement;
import org.bond.hello.widget.control.TextViewActivity;
import org.bond.hello.widget.layout.*;
import org.bond.hello.widgets.examples.HandlerActivity;
import org.bond.hello.widgets.examples.HandlerThreadActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形控件数据提供类
 *
 * @author chengmingwei
 */
public class TreeDataProvider implements DataProvider {
    private Context context = null;
    private List<TreeElement> nodes = new ArrayList<TreeElement>();

    public TreeDataProvider(Context context) {
        super();
        this.context = context;
    }

    private void initDataSource() {
        //一级菜单
        TreeElement firstElement1 = new TreeElement("01", "用户界面和控件");
        TreeElement firstElement2 = new TreeElement("02", "菜单和对话框");
        TreeElement firstElement3 = new TreeElement("03", "2D动画示例");
        TreeElement firstElement4 = new TreeElement("04", "常用类示例");
        nodes.add(firstElement1);
        nodes.add(firstElement2);
        nodes.add(firstElement3);
        nodes.add(firstElement4);

        TreeElement secondElement1 = new TreeElement("01_01", "android中的常见控件");
        TreeElement secondElement2 = new TreeElement("01_02", "android中的其它控件");
        TreeElement secondElement3 = new TreeElement("01_03", "android 布局");
        firstElement1.addChild(secondElement1);
        firstElement1.addChild(secondElement2);
        firstElement1.addChild(secondElement3);

        TreeElement thirdElement1 = new TreeElement("01_01_01", "文本控件");
        TreeElement thirdElement2 = new TreeElement("01_01_02", "按钮控件");
        TreeElement thirdElement3 = new TreeElement("01_01_03", "列表控件");
        TreeElement thirdElement4 = new TreeElement("01_01_04", "网格控件");
        TreeElement thirdElement5 = new TreeElement("01_01_05", "日期和时间控件");
        secondElement1.addChild(thirdElement1);
        secondElement1.addChild(thirdElement2);
        secondElement1.addChild(thirdElement3);
        secondElement1.addChild(thirdElement4);
        secondElement1.addChild(thirdElement5);

        TreeElement fourthElement1 = new TreeElement("01_01_01_01", "TextView控件", getIntent(TextViewActivity.class));
        thirdElement1.addChild(fourthElement1);

        TreeElement thirdElement0301 = new TreeElement("01_03_01", "LinearLayout布局示例", getIntent(LinearLayoutActivity.class));
        TreeElement thirdElement0302 = new TreeElement("01_03_02", "TableLayout布局示例", getIntent(TableLayoutActivity.class));
        TreeElement thirdElement0303 = new TreeElement("01_03_03", "LinearLayout复杂布局示例", getIntent(LinearLayoutComplexActivity.class));
        TreeElement thirdElement0304 = new TreeElement("01_03_04", "Linear Table 组合布局", getIntent(Linear_Table_LayoutComplexActivity.class));
        TreeElement thirdElement0305 = new TreeElement("01_03_05", "RelitaveLayout 布局", getIntent(RelativeLayoutActivity.class));
        TreeElement thirdElement0306 = new TreeElement("01_03_06", "RelitaveLayout复杂示例", getIntent(RelativeLayoutComplexActivity.class));
        secondElement3.addChild(thirdElement0301);
        secondElement3.addChild(thirdElement0302);
        secondElement3.addChild(thirdElement0303);
        secondElement3.addChild(thirdElement0304);
        secondElement3.addChild(thirdElement0305);
        secondElement3.addChild(thirdElement0306);

        TreeElement secondElement4_1 = new TreeElement("04_01", "Handler 定时移动小球示例", getIntent(HandlerActivity.class));
        TreeElement secondElement4_2 = new TreeElement("04_02", "HandlerThread 文件下载进度演示", getIntent(HandlerThreadActivity.class));
        firstElement4.addChild(secondElement4_1);
        firstElement4.addChild(secondElement4_2);
    }

    private Intent getIntent(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        return intent;
    }


    @Override
    public void foward(String caption) throws Exception {

    }

    @Override
    public List<TreeElement> getDataSource() {
        if (null == nodes || nodes.size() == 0) initDataSource();
        return nodes;
    }

}
