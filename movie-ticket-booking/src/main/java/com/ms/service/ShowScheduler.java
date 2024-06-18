package com.ms.service;

import com.ms.cinemahall.Screen;
import com.ms.price.SeatPriceConfiguration;
import com.ms.show.Interval;
import com.ms.show.Show;
import com.ms.show.ShowSchedule;

public class ShowScheduler {
    public void scheduleShow(Show show, Screen screen, Interval interval, SeatPriceConfiguration seatPriceConfig) {
        screen.addShowSchedule(new ShowSchedule(show, screen, interval, seatPriceConfig));
    }

    public void removeShowSchedule(ShowSchedule showSchedule) {
        showSchedule.getScreen().removeShowSchedule(showSchedule);
    }
}