package com.tickshareba.activities;

import com.TestConstants;
import com.google.gson.Gson;
import com.tickshareba.Constants;
import com.tickshareba.management.ITripManager;
import com.tickshareba.management.TripManagerImpl;


import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.apps.testsupport.ASAPPeerWrapperMock;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class OfferTripTest {

    private ITripManager tripManager;

    @Before
    public void setUp(){
        tripManager = new TripManagerImpl();
        tripManager.createTrip("1", "Teststadt", "Ankunftstadt",
                getCurrentDatePlusOneWeek(), "3", "Token");

    }

    @Test
    public void testSendTrip(){
        Collection<CharSequence> formats = new ArrayList<>();
        formats.add(TestConstants.APP_NAME.getValue());
        ASAPPeerWrapperMock alice = new ASAPPeerWrapperMock(TestConstants.ALICE.getValue());
        Gson gson = new Gson();
        String tripsToString = gson.toJson(tripManager.getTripList());
        byte[] byteContent = tripsToString.getBytes();
        boolean send = false;
        try {
            alice.sendASAPMessage(TestConstants.APP_NAME.getValue(), TestConstants.URI.getValue(), byteContent);
            send = true;
        } catch (ASAPException e) {
            send = false;
            e.printStackTrace();
        }
        assertTrue(send);
    }


    private String getCurrentDatePlusOneWeek() {
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT.getValue());
        Date date = new Date(millis + (7 * millis));
        return simpleDateFormat.format(date);
    }
}
