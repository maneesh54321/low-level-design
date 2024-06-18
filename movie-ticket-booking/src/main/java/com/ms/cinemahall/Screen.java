package com.ms.cinemahall;

import com.ms.show.ShowSchedule;

import java.util.ArrayList;
import java.util.List;

public record Screen(String screenNo, List<ShowSchedule> showSchedules, SeatingConfiguration seatingConfiguration) {

    public Screen(String screenNo, SeatingConfiguration seatingConfiguration) {
        this(screenNo, new ArrayList<>(), seatingConfiguration);
    }

    public void addShowSchedule(ShowSchedule showSchedule) {
        showSchedules.add(showSchedule);
    }

    public void removeShowSchedule(ShowSchedule showSchedule) {
        showSchedules.remove(showSchedule);
    }
}
