package com.kalbenutritionals.kalcaremobieTesting.Data.AlertDialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Robert on 05/01/2018.
 */

public class MyEditTextDatePicker  implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText _editText;
    private int _day;
    private int _month;
    private int _birthYear;
    private Context _context;

    private void showDatePicker(EditText editText) {
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(_context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

        Bundle args = new Bundle();
        args.putInt("year", calendar.get(Calendar.YEAR));
        args.putInt("month", calendar.get(Calendar.MONTH));
        args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));


    }

    public MyEditTextDatePicker(Fragment fragment, final EditText edDate)
    {

        DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
//            String month2 = clsMainMonth.months[monthOfYear+1];

                String he = String.format("%02d", dayOfMonth);
                String ha = String.format("%02d", monthOfYear + 1);
                edDate.setText(he + " - " + ha + " - " + String.valueOf(year));
                edDate.setHint("");
            }
        };
        Activity act = (Activity) fragment.getActivity();
        this._editText = edDate;
        this._editText.setOnClickListener(this);
        this._context = fragment.getContext();

    }
    public MyEditTextDatePicker(Activity activity, EditText editText)
    {
//        Activity act = (Activity)context;
        this._editText = editText;
        this._editText.setOnClickListener(this);
        this._context = activity.getApplicationContext();
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(_context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        _birthYear = year;
        _month = monthOfYear;
        _day = dayOfMonth;
        updateDisplay();
    }
    @Override
    public void onClick(View v) {


    }

    // updates the date in the birth date EditText
    private void updateDisplay() {

        _editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(_day).append("/").append(_month + 1).append("/").append(_birthYear).append(" "));
    }
}
