package com.amazonaws.youruserpools;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;

public class ManualRegister extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        final EditText rfidNo= (EditText)findViewById(R.id.rfidNo);


        Button insertID = (Button) findViewById(R.id.manualregister);
        insertID.setOnClickListener(new View.OnClickListener() {
            SharedPreferences myPrefs;
            @Override
            public void onClick(View view) {
                String CardID = rfidNo.getText().toString();
                //set up SharedPreferences
                myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putString("nameKey", CardID);
                editor.apply();

                Intent intent = new Intent(view.getContext(), registerRFID.class);
                intent.putExtra("ManualcardDetails",CardID);
                startActivity(intent);
            }
        });

    }






}
