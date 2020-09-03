package com.amazonaws.youruserpools;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.renderscript.Double4;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;

import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class ShowBuses extends AppCompatActivity implements BusListAdapter.OnBusListener {


    private static final String TAG = "List of vehicles";



    // ui components

    private RecyclerView mRecyclerView;

    // var components
    private ArrayList<BusList> mBuses = new ArrayList<>();
    private BusListAdapter mBusRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_buses);
        mRecyclerView = findViewById(R.id.recyclerView);

        initRecyclerView();
        insertFakeBuses();

    }


    private void insertFakeBuses() {
        /*for (int i = 0; i < 10; i++) {
            BusList bus = new BusList();
            bus.setBus("Bus No #" + i);
            bus.setCrowd("" + i + "");
            bus.setFare(""+i * 10+"");
            mBuses.add(bus);
            Log.i(TAG, "insertBuses5.0: "+ mBuses);
        }*/
        //mBuses.clear();
        ArrayList<BusList> Buses  = getIntent().getParcelableArrayListExtra("busList");
        for (BusList bus : Buses) {
            System.out.println(bus);
            mBuses.add(bus);
        }

        Log.i(TAG, "insertBuses5: "+ mBuses);
        mBusRecyclerAdapter.notifyDataSetChanged();

    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        BusListItemDecorator itemDecorator = new BusListItemDecorator(8);
        mRecyclerView.addItemDecoration(itemDecorator);
        // new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mBusRecyclerAdapter = new BusListAdapter(mBuses,this);
        mRecyclerView.setAdapter(mBusRecyclerAdapter);
    }


    @Override
    public void onBusClick(int position) {
        BusList busItem = mBuses.get(position);
        String routeID = busItem.getRoute_ID();

        GetAllItemsAsyncTask getAllDevicesTask = new GetAllItemsAsyncTask(routeID);

        try {

            ArrayList<LatLng> temp = getAllDevicesTask.execute().get();
            Log.i("the list is ", "list :" + temp);
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putParcelableArrayListExtra("latsNlons" ,temp);
            startActivity(intent);
           // mBuses =  getAllDevicesTask.execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

////////////////////////////first async task
    private class GetAllItemsAsyncTask extends AsyncTask<String, Void, ArrayList<LatLng>> {
        private String routeID;
     public GetAllItemsAsyncTask(String routeID){
            this.routeID=routeID;
        }
        @Override
        protected ArrayList<LatLng> doInBackground(String... params) {

            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ShowBuses.this);
            Document DataRecieved_RT=databaseAccess.getItem_from_RT(routeID);

            ArrayList<LatLng> LatNLon=new ArrayList<LatLng>();
            List<AttributeValue> routeData = DataRecieved_RT.get("Stops").convertToAttributeValue().getL();
            Log.d("DataRecieved_RT", "DataRecieved_RT is "+DataRecieved_RT + "routeID" );
            Log.i("DataRecieved_RT2", "DataRecieved_RT 2: " + routeID + "routeData" +routeData);
            for (AttributeValue data1: routeData )
            {
                Log.i("AttributeValue", "AttributeValues are: " + data1);
                Map<String , AttributeValue> temp= data1.getM();
                Log.i("temp", "temp are: " + temp);
                List<AttributeValue> latlon=temp.get("latlons").getL();
                Log.i("latlon", "latlon are: " + latlon);
                Double lat =Double.parseDouble(latlon.get(0).getS());
                Double lon =Double.parseDouble(latlon.get(1).getS());
                Log.i("lats ans longs", "doInBackground: " + lat + " " +lon);
                LatLng point=new LatLng(lat,lon);
                LatNLon.add(point);

            }
            return LatNLon;
        }

        @Override
        protected void onPostExecute(ArrayList<LatLng> latsNlons) {

        }

    }



}