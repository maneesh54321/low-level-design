package com.ms.backup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class BackupManager {

    private DiffFinder diffFinder;

    public BackupManager(DiffFinder diffFinder) {
        this.diffFinder = diffFinder;
    }

    public void startBackup() {
        var targetLocation = BackupMetadataLoader.loadTargetLocation();
        if (!targetLocation.exists()) {
            try {
                Files.createDirectories(targetLocation.location());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (var backupLocations = BackupMetadataLoader.loadSourceLocations()) {
            backupLocations
                    .filter(BackupLocation::exists)
                    .forEach(backupLocation -> {
                        try {
                            Path targetDirectory = targetLocation.location().resolve(backupLocation.location().getFileName());
                            Files.createDirectory(targetDirectory);
                            copy(backupLocation.location(), targetDirectory);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

    }

    private void copy(Path sourceLocation, Path targetLocation) {
        System.out.println("Source: " + sourceLocation + " Target: " + targetLocation);
        try (Stream<Path> fileList = Files.list(sourceLocation)) {
            System.out.println("Copying files...");
            fileList.forEach(path -> {
                try {
                    System.out.println(path);
                    Path targetFile = targetLocation.resolve(path.getFileName());
                    if (Files.isDirectory(path)) {
                        Files.createDirectory(targetFile);
                        copy(path, targetFile);
                    } else {
                        if (diffFinder.isDifferent(path, targetFile)) {
                            System.out.println("Copying file..");
                            Files.copy(path, targetFile);
                        } else {
                            System.out.println("Skipping as the file has not changed... b    ");
                        }
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
