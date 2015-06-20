package org.bond.hello.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-4-5.
 */
public class ViewUtil {
    private ViewUtil() {
        //隐藏
    }

    /**
     * 获取Activity中所有View
     *
     * @return
     */
    public static List<View> getAllChildViews(Activity activity) {
        View view = activity.getWindow().getDecorView();
        return getAllChildViews(view);
    }

    /**
     * 递归获取所有View
     *
     * @param view
     * @return
     */
    private static List<View> getAllChildViews(View view) {
        List<View> children = new ArrayList<View>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View child = vp.getChildAt(i);
                children.add(child);
                children.addAll(getAllChildViews(child));
            }
        }
        return children;
    }
}
