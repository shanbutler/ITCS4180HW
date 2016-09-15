package com.example.shan.expenseapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditExpense extends AppCompatActivity {

    Button saveButton;
    Button cancelButton;
    Button selectExpense;
    ImageButton receiptButton;
    EditText expNameE;
    EditText expAmountE;
    EditText dateViewE;
    Spinner spinnerE; // category
    ArrayList<Expense> expenses = new ArrayList<>();
    //String[] stringExp = new String[];
    Bundle b = new Bundle();
    String[] expNames;
    int position = -1; // pos of expense in object array

    Intent main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        saveButton = (Button) findViewById(R.id.saveButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        selectExpense = (Button) findViewById(R.id.selectExpButton);
        main = new Intent(EditExpense.this, MainActivity.class);

        expNameE = (EditText) findViewById(R.id.expNameText);
        expAmountE = (EditText) findViewById(R.id.expAmountText);
        dateViewE = (EditText) findViewById(R.id.dateView);
        receiptButton = (ImageButton) findViewById(R.id.receiptButton);

        // fill the dropdown menu
        spinnerE = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> adapterE = ArrayAdapter.createFromResource(this,
                R.array.expense_categories, android.R.layout.simple_spinner_item);
        adapterE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerE.setAdapter(adapterE);

        // retrieve expenses
        if(getIntent().getExtras() != null){
            expenses = getIntent().getParcelableArrayListExtra("EXPENSE_LIST");

            expNames = new String[expenses.size()];
            for(int i = 0; i < expenses.size(); i++){
                expNames[i] = (String) expenses.get(i).name;
            }
        }

        selectExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expNames != null) {

                    new AlertDialog.Builder(EditExpense.this)
                        .setTitle("Expenses")
                        .setItems(expNames, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                // fill out information
                                // find position of category name for spinner selection
                                spinnerE.setSelection(adapterE.getPosition(expenses.get(item).category));
                                expNameE.setText(expenses.get(item).name);
                                expAmountE.setText(String.valueOf(expenses.get(item).amount));
                                dateViewE.setText(expenses.get(item).date);
                                position = item;
                            }
                        })
                        .setCancelable(true)
                        .show();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount;

                String name = expNameE.getText().toString();
                String category = spinnerE.getSelectedItem().toString();

                try{
                    amount = Double.parseDouble(expAmountE.getText().toString());
                }
                catch(NumberFormatException e) {
                    amount = 0.0;
                }

                String date = dateViewE.getText().toString();
                // no idea how to do uri
                //uri = Uri.parse("file://drawable/receipt1.gif");


                if (position == -1) {
                    Toast.makeText(EditExpense.this, "Invalid selection", Toast.LENGTH_SHORT).show();
                } else if (!name.equals("") && !category.equals("Select Category") && amount != 0.0){
                    Intent returnMain = new Intent(EditExpense.this, MainActivity.class);
                    // add in uri
                    Expense e = new Expense(name, category, amount, date);
                    expenses.set(position, e);
                    returnMain.putExtra("EXPENSE_LIST", expenses);
                    startActivity(returnMain);
                } else {
                    Toast.makeText(EditExpense.this, "Invalid field", Toast.LENGTH_SHORT).show();
                }
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
