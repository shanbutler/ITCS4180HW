package com.example.shan.expenseapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    //Intent in;
    ArrayList<Expense> expObjList = new ArrayList<Expense>();
    Expense exp;
    Expense saveExp;
    int position;

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
            if (getIntent().getExtras().getParcelable("NEW_EXPENSE") != null) {
                Log.d("debug", getIntent().getType());
                exp = getIntent().getExtras().getParcelable("NEW_EXPENSE");
                expObjList.add(exp);
            }

            // overwrite old expense with changes:
            // this breaks everything
               // saveExp = getIntent().getExtras().getParcelable("SAVE_EXPENSE");
               // position = getIntent().getIntExtra("POS_IN_ARRAY", 0);
               // expObjList.set(position, saveExp);

            Log.d("demo", "received expense");
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
                if(expObjList.size() > 0){
                    editIntent.putExtra("EXPENSES_LIST", expObjList);
                    startActivity(editIntent);
                }
                else{
                    // error, no expense
                    Toast.makeText(MainActivity.this, "No expenses", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteExpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expObjList.size() > 0){
                    showIntent.putExtra("EXPENSES_LIST", expObjList);
                    startActivity(deleteIntent);
                }
                else{
                    // error, no expense
                    Toast.makeText(MainActivity.this, "No expenses", Toast.LENGTH_SHORT).show();
                }
            }
        });
        showExpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expObjList.size() > 0){
                    showIntent.putExtra("EXPENSES_LIST", expObjList);
                    startActivity(showIntent);
                }
                else{
                    // error, no expense
                    Toast.makeText(MainActivity.this, "No expenses", Toast.LENGTH_SHORT).show();
                }
            }
        });
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(finish);
            }
        });
    }
// this don't work
    public static void addPicToGallery(Context context, String photoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }
}
