package com.kalbenutritionals.kalcaremobieTesting.Data.AlertDialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.kalbe.mobiledevknlibs.Helper.clsMainExlActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dewi Oktaviani on 1/8/2018.
 */

public class CustomDatePicker {
    private static Date dt_hidden;
    private static Calendar calendar;
    private static int year,month, day;
    public static String YEAR = "year";
    public static String MONTH =  "month";
    public static String DAY_OF_MONTH = "day";
    public static String DATE_MAX = "date_max";
    public static String DATE_MIN = "date_min";
    public static void showHint(EditText editText, Bundle bundle, int format) {
        calendar= Calendar.getInstance();
        year = bundle.getInt(YEAR);
        month = bundle.getInt(MONTH);
        day =  bundle.getInt(DAY_OF_MONTH);
        calendar.set(year, month, day);
        editText.setHint(formatSimpleDate(calendar.getTime(), format));
    }
    public static void showDatePicker(Activity activity, Context context, final EditText editText, String title, final int format, Bundle bundle) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View promptView = layoutInflater.inflate(com.kalbe.mobiledevknlibs.R.layout.custom_popup_date_picker, null);
        final DatePicker dp = (DatePicker) promptView.findViewById(com.kalbe.mobiledevknlibs.R.id.dp_tgl);

        if ( editText.getText().toString().equals("")) {
            int year = bundle.getInt(YEAR);
            int month = bundle.getInt(MONTH);
            int day = bundle.getInt(DAY_OF_MONTH);
            dp.init(year, month, day, null);
        } else {
            if (dt_hidden != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dt_hidden);
                dp.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
            }
        }

        long max = bundle.getLong(DATE_MAX);
        long min = bundle.getLong(DATE_MIN);
        if (max!=0){
            dp.setMaxDate(max);
        }
        if (min!=0){
            dp.setMinDate(min);
        }

        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(activity);
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       String date = formatDate(dp, format);
                        editText.setText(date);
                        editText.setHint("");

                        int day = dp.getDayOfMonth();
                        int month = dp.getMonth();
                        int year = dp.getYear();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);

                        dt_hidden = calendar.getTime();
                    }
                })
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final android.support.v7.app.AlertDialog alertD = alertDialogBuilder.create();
        alertD.setTitle(title);
        alertD.show();

    }

    public static String formatDate(DatePicker dp, int format){
        String date = "";
        String month2 = clsMainExlActivity.months[dp.getMonth() + 1];
        switch (format){
            case 0:
                date = String.valueOf(dp.getDayOfMonth()) + " - " + month2 + " - " + String.valueOf(dp.getYear());
                break;
            case 1:
                date = String.valueOf(dp.getDayOfMonth()) + " " + month2 + " " + String.valueOf(dp.getYear());
                break;
            case 2:
                date = String.valueOf(dp.getYear()) + "/" + String.valueOf(dp.getMonth() + 1) + "/"+ String.valueOf(dp.getDayOfMonth())  ;
                break;
            case 3:
                date = String.valueOf(dp.getYear()) + "-" + String.valueOf(dp.getMonth() + 1) + "-"+ String.valueOf(dp.getDayOfMonth())  ;
                break;
            default:
                date = String.valueOf(dp.getYear()) + "-" + String.valueOf(dp.getMonth() + 1) + "-"+ String.valueOf(dp.getDayOfMonth())  ;
        }
        return date;
    }

    public static String formatSimpleDate(Date dates, int format){
        String date = "";
        SimpleDateFormat simpleDateFormat;
        switch (format){
            case 0:
                simpleDateFormat = new SimpleDateFormat("dd - MMM - yyyy");
                break;
            case 1:
                simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
                break;
            case 2:
                simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                break;
            case 3:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                break;
            default:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        date = simpleDateFormat.format(dates);
        return date;
    }


    public static class format {
        public static final int standard0 = 0; //01 - JAN - 2008
        public static final int standard1 = 1; //01 JAN 2008
        public static final int standard2 = 2; //31/12/2008
        public static final int standard3 = 3; //31-12-2008
    }

}
