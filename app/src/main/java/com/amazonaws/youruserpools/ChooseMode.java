package com.amazonaws.youruserpools;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseMode extends AppCompatActivity implements Adapter.OnItemClicked{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);

        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        RecyclerView recyclerView=findViewById(R.id.rv_list);
        List<item> mlist=new ArrayList<>();
        mlist.add(new item(R.drawable.uber,"Comfy Cabs","Service at your doorstep"));
        mlist.add(new item(R.drawable.bus1,"Busy Buses","Everyday routine buses across city"));
        mlist.add(new item(R.drawable.train,"Livid Locals","Connecting intra and inter cities"));

        Adapter adapter=new Adapter(this,mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    public void onItemClick(int position){
        if(position==0){
            Intent intent=new Intent(getApplicationContext(),Dashboard.class);
            getApplicationContext().startActivity(intent);
        }
        else if(position==1){
            Intent intent=new Intent(getApplicationContext(),Dashboard.class);
            getApplicationContext().startActivity(intent);
        }
        else if(position==2){
            Intent intent=new Intent(getApplicationContext(),Dashboard.class);
            getApplicationContext().startActivity(intent);
        }
    }


    public void book(View view){
        Intent intent=new Intent(this, BusBook.class);
        startActivityForResult(intent,16);
    }

   /*
    @Override
    public void onItemClick(View view,int position){
        Context context=view.getContext();
        Intent intent=new Intent();
        switch (position){
            case 0:
                intent=new Intent(context,Book.class);
                context.startActivity(intent);
                break;
        }
    }
     */
}