package com.rosed.sMPEvents;

public enum EventState {
    // When there are no events going on
    IDLE,

    // Event is selected, gathering players to play event
    PREPARE,

    // Event is live
    LIVE;
}
