package com.example.shan.expenseapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class AddExpense extends AppCompatActivity {

    EditText expName;
    EditText expAmount;
    Spinner spinner; // category
    Button addExpButton;
    ImageButton receiptButton;
    //DatePicker datePicker;
    TextView dateView;
    Calendar calendar;
    int year;
    int month;
    int day;

    public String name;
    public String category;
    public String date;
    public double amount;
    public Uri uri;

    private int PICK_IMAGE_REQUEST = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        expName = (EditText) findViewById(R.id.expNameText);
        expAmount = (EditText) findViewById(R.id.expAmountText);
        dateView = (EditText) findViewById(R.id.dateView);

        addExpButton = (Button) findViewById(R.id.addNewExp);
        receiptButton = (ImageButton) findViewById(R.id.receiptButton);

        // fill the dropdown menu
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.expense_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        // use date picker dialog
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        addExpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = expName.getText().toString();
                category = spinner.getSelectedItem().toString();
                amount = Double.parseDouble(expAmount.getText().toString());
                date = dateView.getText().toString();
                // no idea how to do uri
                uri = Uri.parse("file://drawable/receipt1.gif");


                Intent returnMain = new Intent(AddExpense.this, MainActivity.class);
                Expense e = new Expense(name, category, amount, date, uri);
                Bundle b = new Bundle();
                b.putParcelable("NEW_EXPENSE", e);
                startActivity(returnMain);
                returnMain.putExtras(b);

            }
        });

        receiptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });

    }

        // more date picker dialog stuff
        @SuppressWarnings("deprecation")
        public void setDate(View view) {
            showDialog(999);

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
