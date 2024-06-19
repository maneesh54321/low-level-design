package com.ms.cinemahall;

import com.ms.show.ShowSchedule;
import com.ms.show.exception.ShowOverlapException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record Screen(String screenNo, List<ShowSchedule> showSchedules, SeatingLayout seatingLayout) {

    public Screen(String screenNo, SeatingLayout seatingLayout) {
        this(screenNo, new ArrayList<>(), seatingLayout);
    }

    public void addShowSchedule(ShowSchedule showSchedule) {
        if(!hasShowPlaying(showSchedule.getInterval().startTime())) {
            showSchedules.add(showSchedule);
        } else {
            throw new ShowOverlapException();
        }
    }

    private boolean hasShowPlaying(LocalDateTime time){
        return showSchedules.stream().anyMatch(showSchedule -> showSchedule.overlaps(time));
    }

    public void removeShowSchedule(ShowSchedule showSchedule) {
        showSchedules.remove(showSchedule);
    }
}
