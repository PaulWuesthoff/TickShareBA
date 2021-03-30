package com.tickshareba.activities;

import android.content.Context;
import android.content.ContextWrapper;

import com.tickshareba.Constants;
import com.tickshareba.models.TripModel;

import net.sharksystem.asap.ASAPMessageReceivedListener;
import net.sharksystem.asap.internals.ASAPMessages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TickshareASAPMessageReceivedListener implements ASAPMessageReceivedListener {

    private List<String> receivedMessages;

    @Override
    public void asapMessagesReceived(ASAPMessages asapMessages) throws IOException {
        receivedMessages = new ArrayList<>();
        CharSequence uri = asapMessages.getURI();

        Iterator<CharSequence> messageIterator = asapMessages.getMessagesAsCharSequence();

        StringBuilder sb = new StringBuilder();
        if (uri.equals(Constants.TRIP_URI.getValue())) {
            while (messageIterator.hasNext()) {
                String receivedMessage = messageIterator.next().toString();
                MainActivity.tripModelList.add(receivedMessage);
                sb.append(receivedMessage);
            }
        }
        if (uri.equals(Constants.ACKNWOLAGE_URI.getValue())) {
            while (messageIterator.hasNext()) {
                String message = messageIterator.next().toString();

                try {
                    MainActivity.showAcknwloagementAlert(message);
                    String id = message.replaceAll("\\D+", "");
                    TripModel tripModel = MainActivity.tripPersistenceManager.getTripWithID(id);
                    int seatsLeft = Integer.parseInt(tripModel.getSeatsLeft());
                    if(seatsLeft > 1){
                        seatsLeft--;
                        tripModel.setSeatsLeft(String.valueOf(seatsLeft));
                        MainActivity.tripPersistenceManager.update(id, tripModel);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
