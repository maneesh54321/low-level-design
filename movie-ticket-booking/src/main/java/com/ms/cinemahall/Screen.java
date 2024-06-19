package com.ms.cinemahall;

import com.ms.show.ShowSchedule;

import java.util.ArrayList;
import java.util.List;

public record Screen(String screenNo, List<ShowSchedule> showSchedules, SeatingLayout seatingLayout) {

    public Screen(String screenNo, SeatingLayout seatingLayout) {
        this(screenNo, new ArrayList<>(), seatingLayout);
    }

    public void addShowSchedule(ShowSchedule showSchedule) {
        showSchedules.add(showSchedule);
    }

    public void removeShowSchedule(ShowSchedule showSchedule) {
        showSchedules.remove(showSchedule);
    }
}
