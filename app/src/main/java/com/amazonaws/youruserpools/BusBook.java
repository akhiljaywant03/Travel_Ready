package com.amazonaws.youruserpools;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BusBook extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    ArrayList<BusList> mBuses =new ArrayList<>();
    ArrayList<BusList> mBusesTemp =new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TAG", "initialstate: "+ mBuses);
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

                try {


                    mBuses =  getAllDevicesTask.execute().get();

                    Log.i("TAG", "insertBuses4: "+ mBuses);
                    Intent intent = new Intent(view.getContext(), ShowBuses.class);
                    intent.putParcelableArrayListExtra("busList", mBuses);
                    view.getContext().startActivity(intent);


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }




            }
        });
    }



    private class GetAllItemsAsyncTask extends AsyncTask<String, Void, ArrayList<BusList>> {

        private String from;
        private String to;


        public GetAllItemsAsyncTask(String from, String to){
            this.from=from;
            this.to=to;

        }

        @Override
        protected ArrayList<BusList> doInBackground(String... params) {



            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(BusBook.this);
            Log.d("Data recieved", "databases content"+databaseAccess.getItem_from_PJ(from,to).toString());
            Log.d("DatabaseAccess", databaseAccess.toString());


            Document DataRecieved_PJ=databaseAccess.getItem_from_PJ(from,to);

            String fare = DataRecieved_PJ.get("fare").convertToAttributeValue().getN();
            List<String> busIDs= DataRecieved_PJ.get("vehicle_ID").convertToAttributeValue().getSS();
            String route = DataRecieved_PJ.get("route_id").convertToAttributeValue().getS();

            for(String busID : busIDs){

                BusList BusDetails = new BusList();
                Log.d("Data recievedhere", "busID"+busID);

                Document DataRecieved_V=databaseAccess.getItem_from_V(busID);
                String crowd= DataRecieved_V.get("Count_of_Travellers").convertToAttributeValue().getS();

                BusDetails.setFare(fare);
                BusDetails.setBus(busID);
                BusDetails.setCrowd(crowd);
                BusDetails.setRoute_ID(route);

                mBuses.add(BusDetails);

            }

            Log.d("Data recieved2", "initialstate2"+mBuses);

            Log.d("Data recieved2", "initialstate3"+mBuses);
            return mBuses;
        }

        @Override
        protected void onPostExecute(ArrayList<BusList> documents) {

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