package com.fresh.company.fresh.Component;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.fresh.company.fresh.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by CJH on 2016/9/17.
 */

public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE="com.fresh.date";
    public static final int REQUEST_DATE=0;
    private Date mDate;
    public static DatePickerFragment newInstance(Date date)
    {
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_DATE,date);
        DatePickerFragment fragment=new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DatePickerFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate=(Date)getArguments().getSerializable(EXTRA_DATE);
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(mDate);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);

        DatePicker datePicker=(DatePicker)v.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDate=new GregorianCalendar(year,monthOfYear,dayOfMonth).getTime();
                getArguments().putSerializable(EXTRA_DATE,mDate);//为防止设备旋转时发生Date数据的丢失，在onDateChanged(...)方法的尾部，我们将Date
                //对象回写保存到了fragment argument中。
            }
        });
        /*首先，通过传入Context参数给AlertDialog.Builder类的构造方法，返回一个AlertDialog.
        Builder实例。
        然后，调用以下两个AlertDialog.Builder方法，配置对话框：
        调用setPositiveButton(...)方法，需传入两个参数：一个字符串资源以及一个实现Dialog-
                Interface.OnClickListener接口的对象。代码清单12-2中传入的资源ID是Android的OK常量。对于
        监听器参数，暂时传入null值*/
        return new AlertDialog.Builder(getActivity()).setView(v).
                setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                }).setNegativeButton(android.R.string.cancel,null)
                .create();
        // return new AlertDialog.Builder(getActivity()).setTitle(R.string.date_picker_title).setPositiveButton(android.R.string.ok,null).create();
    }

    private void sendResult(int resultCode){
        Intent i=new Intent();
        i.putExtra(EXTRA_DATE,mDate);
        if(getActivity() instanceof GetDateFromFragment){
            ((GetDateFromFragment) getActivity()).GetDate(REQUEST_DATE,resultCode,i);
        }
    }

    public static interface GetDateFromFragment{
        void GetDate(int requestCode, int resultCode,Intent date);
    }
}
