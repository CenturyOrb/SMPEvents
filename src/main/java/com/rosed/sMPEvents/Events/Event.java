package com.rosed.sMPEvents.Events;

import java.util.Random;

public enum Event {
    BAIT(Bait.class),
    PARKOUR(Parkour.class);

    private final Class<?> eventClass;

    Event(Class<?> evenClass) {
        this.eventClass = evenClass;
    }

    public Class<?> getEventClass() { return eventClass; }

    public static Event getRandomEvent() {
        Event[] events = values();
        return events[new Random().nextInt(events.length)];
    }

    public static EventGame createRandomEvent() {
        EventGame event = null;
        try  {
            Event randomEvent = Event.getRandomEvent();
            event = (EventGame) randomEvent.getEventClass().getDeclaredConstructor().newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return event;
    }
}
