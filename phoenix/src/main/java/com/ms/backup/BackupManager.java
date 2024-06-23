package com.ms.backup;

import java.io.IOException;
import java.nio.file.Files;

public class BackupManager {

    private final BackupMetadataLoader backupMetadataLoader;

    public BackupManager(BackupMetadataLoader backupMetadataLoader) {
        this.backupMetadataLoader = backupMetadataLoader;
    }

    public void startBackup() {
        var backupLocations = backupMetadataLoader.loadSourceLocations();
        var targetLocation = backupMetadataLoader.loadTargetLocation();

        if (!targetLocation.exists()) {
            try {
                Files.createDirectories(targetLocation.location());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        backupLocations
                .filter(BackupLocation::exists)
                .forEach(backupLocation -> copy(backupLocation, targetLocation));
    }

    private void copy(BackupLocation sourceLocation, BackupLocation targetLocation) {
        System.out.println("Source: " + sourceLocation + " Target: " + targetLocation);
        try {
            System.out.println("Copying files...");
            Files.list(sourceLocation.location()).forEach(path -> {
                try {
                    Files.copy(path, targetLocation.location());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
