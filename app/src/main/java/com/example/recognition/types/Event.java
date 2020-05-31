package com.example.recognition.types;

import java.util.ArrayList;
import java.util.List;

public final class Event<T> {
    private List<Action<T>> m_Listeners;

    public Event() {
        m_Listeners = new ArrayList<>();
    }

    public void fire(T... args)
    {
        for(Action<T> listener : m_Listeners) {
            if (null != listener) {
                listener.onEventFired(args);
            }
        }
    }
    public void addListener(Action<T> action) {
        m_Listeners.add(action);
    }
    public void removeListener(Action<T> action) {
        m_Listeners.remove(action);
    }
    public void removeAllListeners() {
        m_Listeners.clear();
    }
}
