package com.amazonaws.youruserpools;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


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

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}