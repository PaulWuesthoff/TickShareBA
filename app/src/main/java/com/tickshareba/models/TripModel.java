package com.tickshareba.models;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TripModel {
    private String id;
    private String startingLocation;
    private String destination;
    private String startingTime;
    private String seatsLeft;
    private String userToken;



    public TripModel(String startingLocation, String destination, String startingTime, String seatsLeft, String userToken){
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.startingTime = startingTime;
        this.seatsLeft = seatsLeft;
        this.userToken = userToken;
    }
    /**
     * Creates a Model for unregisterd users
     * @param startingLocation
     * @param destination
     * @param startingTime
     * @param seatsLeft
     */
    public TripModel(String startingLocation, String destination, String startingTime, String seatsLeft){
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.startingTime = startingTime;
        this.seatsLeft = seatsLeft;
    }

    @Override
    public String toString(){
        return "Id: "+id +", Starting location: "+startingLocation+", Destination: "+destination+
                ", Starting Time: "+startingTime+", Seats left: "+seatsLeft+", userToken: "+userToken;
    }

}
