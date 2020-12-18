package com.tickshareba.communication;

public interface IASAPManager {
    /**
     * Wird später Verbindungen aufbauen, am Besten wäre es einen connection pool zu bilden in der alle ASAP Peers drin sind, die es gibt
     * fungiert als Sender und empfänger
     * Kümmert sich um die Device discovery
     * Grundlegender Aufbau meiner Kommunikation: Objekte in Json parsen (via GSON), diese dann in
     * Byte Arrays parsen und dann versenden, auf der anderen Seite den gleichen Schritt rückwärts machen
     */

    public boolean send();

    public boolean receive();
}
