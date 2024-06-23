package com.ms.backup;

import java.nio.file.Files;
import java.nio.file.Path;

public record BackupLocation(Path location) {

    public BackupLocation(String location) {
        this(Path.of(location));
    }

    public boolean exists() {
        return Files.exists(location);
    }
}
