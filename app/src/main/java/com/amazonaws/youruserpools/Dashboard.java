package com.amazonaws.youruserpools;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;

public class Dashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button scanQR = (Button) findViewById(R.id.qrCodeRegister);
        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), registerRFID.class);
                view.getContext().startActivity(intent);}
        });

    }

    public void choose(View view) {
        Intent intent = new Intent(this, ChooseMode.class);
        startActivityForResult(intent, 11);
    }
    public void recharge(View view){
        Intent intent=new Intent(this,Payment.class);
        startActivity(intent);
    }


}
