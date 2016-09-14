package com.example.shan.expenseapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class EditExpense extends MainActivity {

    Button saveButton;
    Button cancelButton;
    Button selectExpense;
    ArrayList<Expense> expenses = new ArrayList<>();
    //String[] stringExp = new String[];
    Bundle b = new Bundle();

    Intent main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        saveButton = (Button) findViewById(R.id.saveButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        selectExpense = (Button) findViewById(R.id.selectExpButton);
        main = new Intent(EditExpense.this, MainActivity.class);

        // retrieve expenses
        if(getIntent().getExtras() != null){

            //b.putParcelable("EXPENSES", getIntent().getParcelableExtra("EXPENSES_LIST"));

            expenses = getIntent().getParcelableArrayListExtra("EXPENSES_LIST");
            //String[] expList;



        }



        selectExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditExpense.this)
                        .setTitle("Expenses")
                        .setItems(expenses, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                // Do something with the selection

                            }
                        })
                        .setCancelable(true)
                        .show();



            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(main);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(main);
            }
        });


    }

}
