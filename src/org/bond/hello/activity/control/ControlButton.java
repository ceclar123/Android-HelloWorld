package org.bond.hello.activity.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-4-6.
 */
public class ControlButton extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ctl_button);

        Button btn_ctl1 = (Button) this.findViewById(R.id.btn_ctl_1);
        Button btn_ctl2 = (Button) this.findViewById(R.id.btn_ctl_2);
        Button btn_ctl3 = (Button) this.findViewById(R.id.btn_ctl_3);
        Button btn_ctl4 = (Button) this.findViewById(R.id.btn_ctl_4);
        Button btn_ctl5 = (Button) this.findViewById(R.id.btn_ctl_5);
        Button btn_ctl6 = (Button) this.findViewById(R.id.btn_ctl_6);
        Button btn_ctl7 = (Button) this.findViewById(R.id.btn_ctl_7);

        btn_ctl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                v.getContext().startActivity(intent);
            }
        });

        btn_ctl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "哈哈哈", Toast.LENGTH_LONG).show();
            }
        });

        btn_ctl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(v.getContext()).create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.setIcon(android.R.drawable.ic_dialog_info);
                dialog.setTitle("系统提示");
                dialog.setMessage("提示内容信息");
                //确定按钮
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("OK", "确定了");
                    }
                });
                //取消按钮
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Cancel", "取消了");
                    }
                });
                //中间按钮
                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Neutral", "忽略'");
                    }
                });
                dialog.show();
            }
        });

        btn_ctl4.setOnClickListener(new View.OnClickListener() {
            EditText txtMsg = null;

            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(v.getContext()).create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.setIcon(android.R.drawable.ic_dialog_info);
                dialog.setTitle("输入数据");
                txtMsg = new EditText(v.getContext());
                dialog.setView(txtMsg);
                //确定按钮
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (txtMsg.getText().toString().length() > 0) {
                            Toast.makeText(txtMsg.getContext(), txtMsg.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        Log.d("OK", "确定了" + txtMsg.getText().toString());
                    }
                });
                //取消按钮
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Cancel", "取消了");
                    }
                });
                dialog.show();
            }
        });

        btn_ctl5.setOnClickListener(new View.OnClickListener() {
            String[] data = new String[]{"选项1", "选项2", "选项3", "选项4", "选项5", "选项6", "选项7", "选项8", "选项9", "选项10"};

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("请选择");
                //单选项
                builder.setSingleChoiceItems(data, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Toast.makeText(getBaseContext(), data[which], Toast.LENGTH_SHORT).show();
                                Log.d("Select", "Select:" + data[which]);
                            }
                        }
                );
                //取消按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Log.d("Cancel", "取消了");
                    }
                });
                builder.show();
            }
        });

        btn_ctl6.setOnClickListener(new View.OnClickListener() {
            String selectData = null;
            String[] data = new String[]{"选项1", "选项2", "选项3", "选项4", "选项5", "选项6", "选项7", "选项8", "选项9", "选项10"};

            @Override
            public void onClick(View v) {
                selectData = ",";
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("Dialog复选框对话框");
                builder.setMultiChoiceItems(data, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (true == isChecked) {
                            selectData += data[which] + ",";
                            Log.d("click", "选中数据:" + data[which]);
                        } else {
                            selectData = selectData.replace("," + data[which] + ",", "");
                            Log.d("click", "取消数据:" + data[which]);
                        }

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getBaseContext(), selectData, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });

        btn_ctl7.setOnClickListener(new View.OnClickListener() {
            String selectData = null;
            String[] data = new String[]{"选项1", "选项2", "选项3", "选项4", "选项5", "选项6", "选项7", "选项8", "选项9", "选项10"};

            @Override
            public void onClick(View v) {
                selectData = ",";
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("Items对话框");
                builder.setItems(data, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectData += data[which] + ",";
                        Toast.makeText(getBaseContext(), selectData, Toast.LENGTH_SHORT).show();
                        Log.d("click", "选中数据:" + data[which]);
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getBaseContext(), selectData, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });


        CheckBox chb_ctl_1 = (CheckBox) this.findViewById(R.id.chb_ctl_1);
        chb_ctl_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (true == isChecked) {
                    Toast.makeText(getBaseContext(), "Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "UnChecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RadioGroup rg_ctl1 = (RadioGroup) this.findViewById(R.id.rg_ctl_1);
        rg_ctl1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                switch (checkedId) {
                    case R.id.rb_ctl_1:
                        Toast.makeText(getBaseContext(), rb.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_ctl_2:
                        Toast.makeText(getBaseContext(), rb.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_ctl_3:
                        Toast.makeText(getBaseContext(), rb.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getBaseContext(), "默认", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        Spinner spinner = (Spinner) this.findViewById(R.id.spinner);
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.country);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        spinner.setAdapter(adapter);
    }
}