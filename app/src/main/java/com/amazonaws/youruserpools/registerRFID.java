package com.amazonaws.youruserpools;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;

import org.w3c.dom.Text;

public class registerRFID extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_rfid);

        SharedPreferences myPrefs;

        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);


        Button scanQR = (Button) findViewById(R.id.AddCard);
        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), QRCodeScan.class);
                view.getContext().startActivity(intent);}
        });

            String card="No cards found";
            TextView results= (TextView)findViewById(R.id.cardDetails);
            Intent intent=getIntent();
            card=intent.getStringExtra("cardDetails");
            String Savecard = myPrefs.getString("nameKey","No Card Found");
            results.setText(Savecard);

            String cardManual="No cards found";
            TextView resultsManual= (TextView)findViewById(R.id.cardDetails);
            Intent intentManual=getIntent();
            card=intent.getStringExtra("ManualcardDetails");
            String SaveManCard = myPrefs.getString("nameKey","No Card Found");
            results.setText(SaveManCard);





    }
}
