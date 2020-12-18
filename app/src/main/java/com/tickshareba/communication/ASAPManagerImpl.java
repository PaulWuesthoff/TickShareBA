package com.tickshareba.communication;

public class ASAPManagerImpl implements IASAPManager {
    @Override
    public boolean send() {
        return true;
    }

    @Override
    public boolean receive() {
        return false;
    }
}
