package com.kalbenutritionals.kalcaremobieTesting.Data.AlertDialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import com.kalbe.mobiledevknlibs.AlertDialog.clsDatePicker;

/**
 * Created by aan.junianto on 15/02/2018.
 */

class AlertDialogCustom extends clsDatePicker {

    public static int ALERT_DIALOG_EXIT = 1;

    public static void alertDialog(final Context activity, int intTypeAlert, AlertDialogCommon alertDialogCommon){
        if(intTypeAlert==1){
            alerDialogExit(activity, alertDialogCommon);
        }
    }

    private static void alerDialogExit(final Context activity, AlertDialogCommon alertDialogCommon) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);

        builder.setTitle(alertDialogCommon.getTxtTitle());
        builder.setMessage(alertDialogCommon.getTxtMessage());

        builder.setPositiveButton(alertDialogCommon.getTxtsPositiveButton(), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                ((Activity)activity).finish();
            }
        });

        builder.setNegativeButton(alertDialogCommon.getTxtNegativeButton(), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }
}
