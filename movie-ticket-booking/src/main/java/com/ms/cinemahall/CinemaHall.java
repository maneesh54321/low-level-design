package com.ms.cinemahall;

import java.util.List;

public record CinemaHall(String name, Address address, List<Screen> screens) {
}
