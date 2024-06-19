package com.ms.service;

import com.ms.cinemahall.Screen;
import com.ms.show.price.SeatPriceCatalog;
import com.ms.show.Interval;
import com.ms.show.Show;
import com.ms.show.ShowSchedule;

public class ShowScheduler {
    public void scheduleShow(Show show, Screen screen, Interval interval, SeatPriceCatalog seatPriceCatalog) {
        System.out.printf("Scheduling show %s on screen %s in interval %s...%n", show, screen.screenNo(), interval);
        screen.addShowSchedule(new ShowSchedule(show, screen, interval, seatPriceCatalog));
        System.out.printf("Show scheduled on screen %s.%n", screen.screenNo());
    }

    public void removeShowSchedule(ShowSchedule showSchedule) {
        showSchedule.getScreen().removeShowSchedule(showSchedule);
    }
}
