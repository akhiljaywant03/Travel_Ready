package com.amazonaws.youruserpools;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BusBook extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    BusList BusDetails = new BusList();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");


            }
        });

        final EditText startLocation = (EditText)findViewById(R.id.FromLocation);
        final EditText endLocation = (EditText)findViewById(R.id.ToLocation);

        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String from = startLocation.getText().toString();
                String to = endLocation.getText().toString();

                GetAllItemsAsyncTask getAllDevicesTask = new GetAllItemsAsyncTask(from ,to);
                getAllDevicesTask.execute();

                try {

                    Log.i("TAG", "insertBuses3: "+ BusDetails.getBus());
                    Document documents = new GetAllItemsAsyncTask(from ,to).execute().get();

                    String fare = documents.get("fare").convertToAttributeValue().getN();
                    String busID= documents.get("vehicle_id").convertToAttributeValue().getS();

                    BusDetails.setFare(fare);
                    BusDetails.setBus(busID);
                    BusDetails.setCrowd("65");



                    Log.i("TAG", "insertBuses4: "+ BusDetails.getBus());
                    Intent intent = new Intent(view.getContext(), ShowBuses.class);
                    intent.putExtra("busList", BusDetails);
                    view.getContext().startActivity(intent);


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }




            }
        });
    }



    private class GetAllItemsAsyncTask extends AsyncTask<String, Void, Document> {

        private String from;
        private String to;

        public GetAllItemsAsyncTask(String from, String to){
            this.from=from;
            this.to=to;
        }

        @Override
        protected Document doInBackground(String... params) {

            BusListDatabaseAccess databaseAccess = BusListDatabaseAccess.getInstance(BusBook.this);
            Log.d("Data recieved", "databases content"+databaseAccess.getItem(from,to).toString());

            return databaseAccess.getItem(from,to);
        }

        @Override
        protected void onPostExecute(Document documents) {

        }

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