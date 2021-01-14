package com.tickshareba.activities;

import android.app.Activity;

import net.sharksystem.asap.ASAP;
import net.sharksystem.asap.android.apps.ASAPApplication;

import java.util.ArrayList;
import java.util.Collection;

public class ASAPTickShareApplication extends ASAPApplication {
    public static final String ASAP_EXAMPLE_APPNAME = "ASAP_EXAMPLE_APP";
    private CharSequence id;
    static ASAPTickShareApplication instance = null;

    static ASAPTickShareApplication initializeASAPExampleApplication(Activity initialActivity) {
        if(ASAPTickShareApplication.instance == null) {
            Collection<CharSequence> formats = new ArrayList<>();
            formats.add(ASAP_EXAMPLE_APPNAME);

            // create
            ASAPTickShareApplication.instance = new ASAPTickShareApplication(formats, initialActivity);

            // there could be some other steps. Setting up sub components. But there are non here.
            // launch
            ASAPTickShareApplication.instance.startASAPApplication();
        } // else - already initialized - nothing happens.

        return ASAPTickShareApplication.instance;
    }

    private ASAPTickShareApplication(Collection<CharSequence> formats, Activity initialActivity) {
        super(formats, initialActivity);

        this.id = ASAP.createUniqueID();

    }

    public CharSequence getOwnerID() {
        return this.id;
    }
}
