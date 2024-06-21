package com.ms.cinemahall;

import com.ms.show.Show;
import com.ms.show.ShowSchedule;
import java.util.List;
import java.util.Objects;

public record CinemaHall(String id, String name, Address address, List<Screen> screens) {

    public boolean hasShow(Show inputShow) {
        return screens.stream().flatMap(screen -> screen.showSchedules().stream())
            .map(ShowSchedule::getShow)
            .anyMatch(show -> show.equals(inputShow));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaHall that = (CinemaHall) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CinemaHall{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", screens=" + screens +
                '}';
    }
}
