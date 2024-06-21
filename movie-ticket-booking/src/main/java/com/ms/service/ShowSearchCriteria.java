package com.ms.service;

import java.time.LocalDate;

public record ShowSearchCriteria(String title, String language, String genre,
                                 LocalDate releaseDate) {

}
