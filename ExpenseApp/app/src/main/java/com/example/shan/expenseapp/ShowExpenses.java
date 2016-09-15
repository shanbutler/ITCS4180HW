package com.example.shan.expenseapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowExpenses extends AppCompatActivity {

    Button finishButton;
    ImageButton firstButton;
    ImageButton lastButton;
    ImageButton previousButton;
    ImageButton nextButton;

    TextView showName;
    TextView showCategory;
    TextView showAmount;
    TextView showDate;

    ArrayList<Expense> expenses;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expenses);

        finishButton = (Button) findViewById(R.id.finishButton);
        firstButton = (ImageButton) findViewById(R.id.firstButton);
        nextButton = (ImageButton) findViewById(R.id.nextButton);
        lastButton = (ImageButton) findViewById(R.id.lastButton);
        previousButton = (ImageButton) findViewById(R.id.previousButton);

        showName = (TextView) findViewById(R.id.showNameView);
        showCategory = (TextView) findViewById(R.id.showCategoryView);
        showAmount = (TextView) findViewById(R.id.showAmountView);
        showDate = (TextView) findViewById(R.id.showDateView);

        if (getIntent().getExtras().getParcelableArrayList("EXPENSE_LIST") != null) {
            expenses = getIntent().getExtras().getParcelableArrayList("EXPENSE_LIST");
            setView(expenses.get(index));
        }

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                setView(expenses.get(0));
            }
        });

        lastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = expenses.size() - 1;
                setView(expenses.get(index));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < expenses.size() - 1) {
                    index ++;
                    setView(expenses.get(index));
                }
            }
        });
        
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    index--;
                    setView(expenses.get(index));
                }
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fin = new Intent(ShowExpenses.this, MainActivity.class);
                fin.putExtra("EXPENSE_LIST", expenses);
                startActivity(fin);
            }
        });
    }

    public void setView(Expense e) {
        showName.setText(e.name);
        showCategory.setText(e.category);
        showAmount.setText(e.amount + "");
        showDate.setText(e.date);
    }
}
