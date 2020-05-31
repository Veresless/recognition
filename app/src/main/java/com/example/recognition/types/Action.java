package com.example.recognition.types;

public abstract class Action<T> {
    public abstract void onEventFired(T... args);
}
