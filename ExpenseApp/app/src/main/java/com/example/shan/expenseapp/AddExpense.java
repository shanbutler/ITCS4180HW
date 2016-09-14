package com.example.shan.expenseapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddExpense extends AppCompatActivity {

    EditText expName;
    Spinner spinner;
    //ImageButton calendarButton;
    DatePicker datePicker;
    TextView dateView;
    Calendar calendar;
    int year;
    int month;
    int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        //calendarButton = (ImageButton) findViewById(R.id.calendarButton);

        // fill the dropdown menu
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.expense_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // use date picker dialog
        dateView = (TextView) findViewById(R.id.textView3);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }

        // more date picker dialog stuff
        @SuppressWarnings("deprecation")
        public void setDate(View view) {
            showDialog(999);
            //Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
             //       .show();
        }

        @Override
        protected Dialog onCreateDialog(int id) {
            if (id == 999) {
                return new DatePickerDialog(this, myDateListener, year, month, day);
            }
            return null;
        }

        private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                // arg1 = year
                // arg2 = month
                // arg3 = day
                showDate(arg1, arg2+1, arg3);
            }
        };

        private void showDate(int year, int month, int day) {
            dateView.setText(new StringBuilder().append(month).append("/")
                    .append(day).append("/").append(year));
        }


}
