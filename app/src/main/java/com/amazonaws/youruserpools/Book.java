package com.amazonaws.youruserpools;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Book extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);
        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker=new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");


            }
        });


        Button search = (Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowBuses.class);
                view.getContext().startActivity(intent);}
        });



    }
    @Override
    public void onDateSet(DatePicker view,int year,int month,int dayOfMonth){
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currenntDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView=(TextView) findViewById(R.id.textView);
        textView.setText(currenntDateString);

    }


}
