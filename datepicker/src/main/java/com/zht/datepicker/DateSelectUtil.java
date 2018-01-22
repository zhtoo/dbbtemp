package com.zht.datepicker;

import android.content.Context;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhanghaitao on 2017/7/17.
 */

public class DateSelectUtil {

    private String[] weekString;

    /**
     * 日期选择
     */
    public static void showSelectDateDialog(Context context, final TextView textView) {
        SelectDateDialog mSelectDateDialog = new SelectDateDialog(context);
        mSelectDateDialog.setOnClickListener(new SelectDateDialog.OnClickListener() {
            @Override
            public boolean onSure(int mYear, int mMonth, int mDay, long time) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                textView.setText(dateFormat.format(time));
                return false;
            }

            @Override
            public boolean onCancel() {
                return false;
            }
        });

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        String[] nowDate = str.split("-");

        mSelectDateDialog.show(Integer.parseInt(nowDate[0]),
                Integer.parseInt(nowDate[1])-1,
                Integer.parseInt(nowDate[2]));
    }

    /**
     * 时间选择
     */
    public void showSelectTimeDialog(Context context, final TextView textView) {
        SelectTimeDialog mSelectTimeDialog = new SelectTimeDialog(context, new SelectTimeDialog.OnClickListener() {
            @Override
            public boolean onSure(int day,int hour, int minute, int setTimeType) {
                String result = String.format("%2d天%2d时%2d分",day ,hour, minute);
                textView.setText(result);
                return false;
            }

            @Override
            public boolean onCancel() {
                return false;
            }
        });
        mSelectTimeDialog.show(0, 0, 0);
    }

    /**
     * 星期选择
     */
    public void showSelectWeekDialog(Context context, final TextView textView) {
        weekString = context.getResources().getStringArray(R.array.weeks);
        SelectWeekDialog mSelectWeekDialog = new SelectWeekDialog(context, new SelectWeekDialog.OnClickListener() {
            @Override
            public boolean onSure(int item, int setTimeType) {
                textView.setText(weekString[item]);
                return false;
            }

            @Override
            public boolean onCancel() {
                return false;
            }
        });
        mSelectWeekDialog.show(3);
    }

}
