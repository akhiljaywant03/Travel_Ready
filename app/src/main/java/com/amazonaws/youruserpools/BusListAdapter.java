package com.amazonaws.youruserpools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;

import java.util.ArrayList;

public class BusListAdapter extends  RecyclerView.Adapter<BusListAdapter.ViewHolder>{

    private ArrayList<BusList> mbuses = new ArrayList<>();
    private OnBusListener mOnBusListener;


    public BusListAdapter(ArrayList<BusList> buses ,OnBusListener onBusListener) {
        this.mbuses = buses;
        this.mOnBusListener=onBusListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_activity_bus_list, parent, false);
        return new ViewHolder(view, mOnBusListener);
    }

    @Override // this method is called for every sing entry in the list
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Buses.setText(mbuses.get(position).getBus());
        holder.fare.setText(mbuses.get(position).getFare());
        holder.crowd.setText(mbuses.get(position).getCrowd());
    }

    @Override
    public int getItemCount() {
        return mbuses.size();
    }

    // class responsible to hold the view of each indivisual list item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView Buses, crowd , fare;
        OnBusListener onBusListener;

        public ViewHolder(@NonNull View itemView ,OnBusListener onBusListener) {
            super(itemView);


            Buses=itemView.findViewById(R.id.buses);
            crowd=itemView.findViewById(R.id.crowd);
            fare=itemView.findViewById(R.id.fare);
            this.onBusListener=onBusListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onBusListener.onBusClick(getAdapterPosition());

        }
    }

    public interface OnBusListener{
        void onBusClick(int position);
    }

}