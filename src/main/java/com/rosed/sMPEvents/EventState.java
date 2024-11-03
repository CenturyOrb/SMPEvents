package com.rosed.sMPEvents;

public enum EventState {
    // When there are no events going on
    WAITING,

    // Event is selected, gathering players to play event
    LOBBY,

    // Event is live
    LIVE;
}
