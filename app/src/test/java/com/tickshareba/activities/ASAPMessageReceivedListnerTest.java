package com.tickshareba.activities;

import com.tickshareba.Constants;

import net.sharksystem.asap.ASAPMessageReceivedListener;
import net.sharksystem.asap.internals.ASAPMessages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ASAPMessageReceivedListnerTest implements ASAPMessageReceivedListener {


    @Override
    public void asapMessagesReceived(ASAPMessages asapMessages) throws IOException {
        CharSequence uri = asapMessages.getURI();

        Iterator<CharSequence> messageIterator = asapMessages.getMessagesAsCharSequence();

        StringBuilder sb = new StringBuilder();
        if (uri.equals(Constants.URI.getValue())) {
            while (messageIterator.hasNext()) {
                String receivedMessage = messageIterator.next().toString();
                ReceiveTripTest.tripModelList.add(receivedMessage);
                sb.append(receivedMessage);
            }
        }

    }
}
