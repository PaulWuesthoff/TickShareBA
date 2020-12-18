package com.tickshareba.models;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TripModel {
    private long id;
    private String startingLocation;
    private String destination;
    private String startingTime;
    private String seatsLeft;
    private String userToken;

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

    /**
     * Checks if a user is logged in or not, if not the Usertoken would be empty
     * @return true if user is logged in, false if user isnt
     */
    public boolean isUserLoggedIn(){
        return StringUtils.isNotEmpty(userToken);
    }
}
