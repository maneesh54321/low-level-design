package com.ms.show.price;

import com.ms.cinemahall.SeatGroup;
import com.ms.cinemahall.SeatingLayout;
import com.ms.show.exception.SeatNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SeatPriceCatalog {
    private final SeatingLayout seatingLayout;
    private final Map<Integer, Integer> seatGroupPriceMap;

    public SeatPriceCatalog(SeatingLayout seatingLayout) {
        this.seatingLayout = seatingLayout;
        this.seatGroupPriceMap = new HashMap<>();
    }

    public void setSeatGroupPrice(int seatGroupId, int amount) {
        var seatGroup = seatingLayout.seatGroups().stream().filter(group -> group.id() == seatGroupId).findFirst();
        if(seatGroup.isPresent()) {
            seatGroupPriceMap.put(seatGroup.get().id(), amount);
        } else {
            throw new SeatNotFoundException();
        }
    }

    public int getSeatGroupPrice(int seatGroupId){
        return seatGroupPriceMap.get(seatGroupId);
    }

    public int getSeatPrice(int seatNum) {
        Optional<SeatGroup> group = seatingLayout.seatGroups().stream().filter(seatGroup -> seatGroup.hasSeat(seatNum)).findFirst();
        if(group.isPresent() && seatGroupPriceMap.containsKey(group.get().id())) {
            return seatGroupPriceMap.get(group.get().id());
        }
        throw new SeatNotFoundException();
    }
}
