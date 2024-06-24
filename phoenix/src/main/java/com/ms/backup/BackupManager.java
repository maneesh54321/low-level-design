package com.ms.backup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class BackupManager {

    private final BackupMetadataLoader backupMetadataLoader;

    public BackupManager(BackupMetadataLoader backupMetadataLoader) {
        this.backupMetadataLoader = backupMetadataLoader;
    }

    public void startBackup() {
        var targetLocation = backupMetadataLoader.loadTargetLocation();
        if (!targetLocation.exists()) {
            try {
                Files.createDirectories(targetLocation.location());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try(var backupLocations = backupMetadataLoader.loadSourceLocations()) {
            backupLocations
                    .filter(BackupLocation::exists)
                    .forEach(backupLocation -> {
                        try {
                            Path targetDirectory = targetLocation.location().resolve(backupLocation.location().getFileName());
                            Files.createDirectory(targetDirectory);
                            copy(backupLocation.location(),targetDirectory);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

    }

    private void copy(Path sourceLocation, Path targetLocation) {
        System.out.println("Source: " + sourceLocation + " Target: " + targetLocation);
        try (Stream<Path> fileList = Files.list(sourceLocation)){
            System.out.println("Copying files...");
            fileList.forEach(path -> {
                try {
                    System.out.println(path);
                    Path targetFile = targetLocation.resolve(path.getFileName());
                    if(Files.isDirectory(path)) {
                        Files.createDirectory(targetFile);
                        copy(path, targetFile);
                    } else {
                        Files.copy(path, targetFile);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
