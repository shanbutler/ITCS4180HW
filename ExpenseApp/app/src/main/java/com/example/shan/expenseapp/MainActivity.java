package com.example.shan.expenseapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.Serializable;
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

    ArrayList<Expense> expObjList[];
    int numExpenses = 0;
    Expense exp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // doesn't work
        addPicToGallery(MainActivity.this, "@drawable/receipt1");

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

        // retrieve expenses
        if(getIntent().getExtras() != null){
            exp = getIntent().getParcelableExtra("NEW_EXPENSE");
            expObjList[numExpenses].add(exp);
            numExpenses++;

        }


        addExpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(addIntent);
            }
        });
        editExpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send arraylist of expense names
                //String[] expNames = new String[expObjList.length];
                //for(int i = 0; i < expObjList.length; i++){
                //    expNames[i] = expObjList[i].getName();

                //}

                editIntent.putExtra("EXPENSES_LIST", expObjList);
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

    public static void addPicToGallery(Context context, String photoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }
}
