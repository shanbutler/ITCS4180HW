package com.example.shan.expenseapp;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * HW2
 * Shannon Butler
 * Akhil Ramlakan
 */
public class Expense implements Parcelable{

    public String name;
    public String category;
    public String date;
    public double amount;
    public Uri uri;

    // add uri later
    public Expense(String n, String c, double a, String d)
    {
        this.name = n;
        this.category = c;
        this.amount = a;
        this.date = d;
        //this.uri = u;
    }

    @Override
    public String toString(){
        String str = name + " " + category;
        return str;
    }
    public Expense(Parcel in){
        readFromParcel(in);

    }

    public static Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Expense createFromParcel(Parcel source) {
            return new Expense(source);
        }
        public Expense[] newArray(int size) {
            return new Expense[size];
        }

    };


    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel in)
    {
        name = in.readString();
        category = in.readString();
        amount = in.readDouble();
        date = in.readString();
        //String u = uri.toString();
        //u = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(category);
        dest.writeDouble(amount);
        dest.writeString(date);
        //dest.writeString(String.valueOf(uri));


    }
}
