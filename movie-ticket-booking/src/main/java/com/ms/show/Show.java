package com.ms.show;

import java.time.LocalDate;

public record Show(String title, String genre, String language, LocalDate releaseDate, ShowType type) {
}
