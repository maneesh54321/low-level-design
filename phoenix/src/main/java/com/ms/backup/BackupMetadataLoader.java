package com.ms.backup;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

public class BackupMetadataLoader {

    public static Stream<BackupLocation> loadSourceLocations() {
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

    public static BackupLocation loadTargetLocation() {
        Properties properties = new Properties();
        try (InputStream propertiesFileStream = BackupMetadataLoader.class.getResourceAsStream("/application.properties")) {
            properties.load(propertiesFileStream);
            String targetLocation = (String) properties.get("backup.target.location");
            return new BackupLocation(targetLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<Path, String> loadCurrentBackupHash(){
        return null;
    }
}
