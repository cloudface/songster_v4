package com.samples.songster.settings.events;

/**
 * Created by chrisbraunschweiler1 on 03/03/16.
 */
public class Event<T> {
    private T mPayload;

    public Event(T payload){
        this.mPayload = payload;
    }

    public T getPayload() {
        return mPayload;
    }
}
