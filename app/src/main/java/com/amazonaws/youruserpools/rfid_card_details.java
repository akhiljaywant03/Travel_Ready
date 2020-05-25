package com.amazonaws.youruserpools;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;

import java.util.concurrent.ExecutionException;

public class rfid_card_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        TextView cardView = (TextView) findViewById(R.id.cardNumber);
        TextView balanceView = (TextView) findViewById(R.id.cardBalance);


        String cardID = getIntent().getStringExtra("RFIDNo");
        Log.i("TAG", "cardId: " + cardID);

        GetCardDetails getAllDevicesTask = new GetCardDetails(cardID);

        try {
            Document cardDetails = getAllDevicesTask.execute().get();

            String cardBalance = cardDetails.get("Balance").convertToAttributeValue().getS();
            cardView.setText(cardID);
            balanceView.setText("Curret Balance : "+cardBalance);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Button rechargeCard = (Button) findViewById(R.id.rechargeCard);

        rechargeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Payment.class);
                view.getContext().startActivity(intent);
            }
        });


    }


    private class GetCardDetails extends AsyncTask<String, Void, Document> {
        String cardID;

        public GetCardDetails(String cardID) {
            this.cardID = cardID;
        }


        @Override
        protected Document doInBackground(String... strings) {

            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(rfid_card_details.this);

            Document DataRecieved_R = databaseAccess.getItem_from_R(cardID);


            return DataRecieved_R;
        }
    }


}