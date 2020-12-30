package com.tickshareba.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tickshareba.R;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] tripsToShow;
    private IOnTripClick onTripClick;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        IOnTripClick onTripClick;
        public MyViewHolder(TextView v, IOnTripClick onTripClick) {
            super(v);
            textView = v;
            this.onTripClick = onTripClick;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onTripClick.onTripClick(getAdapterPosition());
        }
    }

    public MyAdapter(String[] myDataset, IOnTripClick onTripClick) {
        this.onTripClick = onTripClick;
        tripsToShow = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);

        MyViewHolder vh = new MyViewHolder(v, onTripClick);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(tripsToShow[position]);

    }

    @Override
    public int getItemCount() {
        return tripsToShow.length;
    }
}