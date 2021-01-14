package com.tickshareba.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tickshareba.R;
import com.tickshareba.models.TripModel;

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
        possibleTrips = new String[PlanTripActivity.tripList.size()];

        for (int i = 0; i < PlanTripActivity.tripList.size(); i++) {
            possibleTrips[i] = PlanTripActivity.tripList.get(i).toString();
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
        // das ist vielleicht nicht so schön :/
        Arrays.fill(possibleTrips, null);
        MainActivity.tripManager.getTripList().clear();
        tripsAdapter.notifyItemRangeRemoved(0, possibleTrips.length);
        finish();
    }

}