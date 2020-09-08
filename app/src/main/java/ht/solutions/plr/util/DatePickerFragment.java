package ht.solutions.plr.util;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

@SuppressLint({"ValidFragment"})
public class DatePickerFragment extends DialogFragment implements OnDateSetListener {
    int day;
    public TextView mTextView;
    int month;
    int year;

    public DatePickerFragment(TextView tv) {
        this.mTextView = tv;
    }

    public DatePickerFragment() {
        Calendar c = Calendar.getInstance();
        this.year = c.get(1);
        this.month = c.get(2);
        this.day = c.get(5);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        this.year = c.get(1);
        this.month = c.get(2);
        this.day = c.get(5);
        return new DatePickerDialog(getActivity(), this, this.year, this.month, this.day);
    }

    public TextView getmTextView() {
        return this.mTextView;
    }

    public void setmTextView(TextView mTextView) {
        this.mTextView = mTextView;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        this.mTextView.setText("" + day + "/" + (month + 1) + "/" + year);
    }
}
