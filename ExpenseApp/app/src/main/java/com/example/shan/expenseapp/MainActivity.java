package com.example.shan.expenseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * HW 2
 * Akhil Ramlakan
 * Shannon Butler
 */

public class MainActivity extends AppCompatActivity {

    Button addExpButton;
    Button editExpButton;
    Button deleteExpButton;
    Button showExpButton;
    Button finishButton;

    ArrayList<Object> expObjList[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addExpButton = (Button) findViewById(R.id.addExpenseButton);
        editExpButton = (Button) findViewById(R.id.editExpenseButton);
        deleteExpButton = (Button) findViewById(R.id.deleteExpenseButton);
        showExpButton = (Button) findViewById(R.id.showExpenseButton);
        finishButton = (Button) findViewById(R.id.finishButton);
        final Intent addIntent = new Intent(MainActivity.this, AddExpense.class);
        final Intent editIntent = new Intent(MainActivity.this, EditExpense.class);
        final Intent deleteIntent = new Intent(MainActivity.this, DeleteExpense.class);
        final Intent showIntent = new Intent(MainActivity.this, ShowExpenses.class);

        // go to Home screen
        final Intent finish = new Intent(Intent.ACTION_MAIN);
        finish.addCategory(Intent.CATEGORY_HOME);
        finish.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);



        addExpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(addIntent);
            }
        });
        editExpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(editIntent);
            }
        });
        deleteExpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(deleteIntent);
            }
        });
        showExpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(showIntent);
            }
        });
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(finish);
            }
        });


    }
}
