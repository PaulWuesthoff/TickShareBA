package com.tickshareba.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tickshareba.R;
import com.tickshareba.models.TripModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowTripsActivity extends AppCompatActivity implements IOnTripClick {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter tripsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static int position = 0;
    private String[] possibleTrips;

    private List<TripModel> tripModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triplistview);

        tripModels = new ArrayList<>();

        Gson gson = new GsonBuilder().setLenient().create();
        Type listOfClass = new TypeToken<List<TripModel>>() {
        }.getType();

        for (String model : MainActivity.tripModelList) {
            tripModels = gson.fromJson(model, listOfClass);
        }

        recyclerView = findViewById(R.id.tripListRecyclerView);
        possibleTrips = new String[tripModels.size()];

        for (int i = 0; i < tripModels.size(); i++) {
            possibleTrips[i] = tripModels.get(i).toString();
        }
        MainActivity.tripManager.setTripList(tripModels);

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