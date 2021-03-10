package com.tickshareba.activities;

import com.TestConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tickshareba.Constants;
import com.tickshareba.management.ITripManager;
import com.tickshareba.management.TripManagerImpl;
import com.tickshareba.models.TripModel;

import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.apps.testsupport.ASAPPeerWrapperMock;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReceiveTripTest {
    private ITripManager tripManager;
    public static List<String> tripModelList;
    private static List<TripModel> receivedTrips;

    @BeforeClass
    public static void initalize() {
        tripModelList = new ArrayList<>();
        receivedTrips = new ArrayList<>();
    }

    @Before
    public void setUp() {
        tripManager = new TripManagerImpl();
        tripManager.createTrip("1", "Teststadt", "Ankunftstadt",
                getCurrentDatePlusOneWeek(), "3", "Token");

    }

    @Test
    public void testReceiveMessage() throws InterruptedException {
        Collection<CharSequence> formats = new ArrayList<>();
        formats.add(TestConstants.APP_NAME.getValue());
        ASAPPeerWrapperMock alice = new ASAPPeerWrapperMock(TestConstants.ALICE.getValue());
        ASAPPeerWrapperMock bob = new ASAPPeerWrapperMock(TestConstants.BOB.getValue());
        ASAPMessageReceivedListnerTest listner = new ASAPMessageReceivedListnerTest();
        bob.addASAPMessageReceivedListener(TestConstants.APP_NAME.getValue(), listner);

        Gson gson = new Gson();
        String tripsToString = gson.toJson(tripManager.getTripList());
        byte[] byteContent = tripsToString.getBytes();
        try {
            alice.sendASAPMessage(TestConstants.APP_NAME.getValue(), TestConstants.URI.getValue(), byteContent);
        } catch (ASAPException e) {
            e.printStackTrace();
        }

        Thread.sleep(100);
        bob.startEncounter(alice);


        Gson received = new GsonBuilder().setLenient().create();
        Type listOfClass = new TypeToken<List<TripModel>>() {
        }.getType();

        for (String model : tripModelList) {
            receivedTrips = received.fromJson(model, listOfClass);
        }

        assertThat(tripManager.getTripList().get(0).getStartingLocation(), equalTo(receivedTrips.get(0).getStartingLocation()));

    }

    private String getCurrentDatePlusOneWeek() {
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT.getValue());
        Date date = new Date(millis + (7 * millis));
        return simpleDateFormat.format(date);
    }
}
