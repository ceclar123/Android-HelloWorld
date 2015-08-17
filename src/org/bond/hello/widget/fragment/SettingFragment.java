package org.bond.hello.widget.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-8-17.
 */
public class SettingFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        TextView item_01 = (TextView) view.findViewById(R.id.item_01);
        item_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingFragment.this.getActivity().getBaseContext(), "测试选项", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}