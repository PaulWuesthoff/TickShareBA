package com.tickshareba.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tickshareba.R;

import java.util.Arrays;

public class ShowTripsActivity extends AppCompatActivity implements IOnTripClick {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter tripsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static int position = 0;
    private String[] possibleTrips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triplistview);
        recyclerView = findViewById(R.id.tripListRecyclerView);
        possibleTrips = new String[MainActivity.tripManager.getTripList().size()];

        for (int i = 0; i < MainActivity.tripManager.getTripList().size(); i++) {
            possibleTrips[i] = MainActivity.tripManager.getTripList().get(i).toString();
        }

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        tripsAdapter = new MyAdapter(possibleTrips, this);

        recyclerView.setAdapter(tripsAdapter);

        recyclerView.setClickable(true);

    }

    @Override
    public void onTripClick(int position) {
        this.position = position;
        Intent intent = new Intent(this, ShowSingleTripActivity.class);
        startActivity(intent);
    }

    public void onButtonBackClick(View view) {
        Intent intent = new Intent(this, PlanTripActivity.class);
        startActivity(intent);
        Arrays.fill(possibleTrips, null);
        MainActivity.tripManager.getTripList().clear();
        tripsAdapter.notifyItemRangeRemoved(0, possibleTrips.length);
        finish();
    }

}