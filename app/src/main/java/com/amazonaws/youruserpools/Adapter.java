package com.amazonaws.youruserpools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {
    private OnItemClicked onClick;

    Context mContext;
    List<item> mData;
    public interface OnItemClicked{
        void onItemClick(int position);
    }

    public Adapter(Context mContext, List<item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(mContext);
        View v=inflater.inflate(R.layout.card_item,parent,false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        holder.background_img.setImageResource(mData.get(position).getBackground());
        holder.mode_title.setText(mData.get(position).getModeName());
        holder.mode_dessc.setText(mData.get(position).getModeDesc());
        holder.background_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent;

                switch (position){
                    case 0:
                        intent=new Intent(mContext,Dashboard.class);
                        break;
                    case 1:
                        intent=new Intent(mContext, BusBook.class);
                        break;
                    case 2:
                        intent=new Intent(mContext,Dashboard.class);
                        break;
                    default:
                        intent=new Intent(mContext,Dashboard.class);
                        break;

                }
                mContext.startActivity(intent);


                mContext.startActivity(intent);


            }
        });





    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView background_img;
        TextView mode_title,mode_dessc;
        public myViewHolder(View itemView){
            super(itemView);
            background_img=itemView.findViewById(R.id.card_background);
            mode_title=itemView.findViewById(R.id.card_title);
            mode_dessc=itemView.findViewById(R.id.card_desc);

        }
    }
    public void setOnClick(OnItemClicked onClick){
        this.onClick=onClick;
    }
}