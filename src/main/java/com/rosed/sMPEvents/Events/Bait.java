package com.rosed.sMPEvents.Events;

import com.rosed.sMPEvents.ConfigLocations;
import com.rosed.sMPEvents.Listeners.BaitListener;
import com.rosed.sMPEvents.Listeners.PlayerConnectionListener;

public class Bait extends EventGame {

    Bait() {
        super();
        spawn = ConfigLocations.BAIT;
        listeners.add(new PlayerConnectionListener());
        listeners.add(new BaitListener());
    }

}
