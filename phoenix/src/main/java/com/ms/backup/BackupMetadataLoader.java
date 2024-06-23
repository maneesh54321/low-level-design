package com.ms.backup;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

public class BackupMetadataLoader {

    public Stream<BackupLocation> loadSourceLocations() {
        try {
            Stream<String> lines = Files.lines(Path.of(Objects.requireNonNull(BackupMetadataLoader.class.getResource("/locations.data")).toURI()));
            return lines.map(line -> {
                System.out.println(line);
                return new BackupLocation(line);
            });
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public BackupLocation loadTargetLocation() {
        Properties properties = new Properties();
        try {
            properties.load(BackupMetadataLoader.class.getResourceAsStream("/application.properties"));
            String targetLocation = (String) properties.get("backup.target.location");
            return new BackupLocation(targetLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
