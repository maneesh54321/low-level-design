package com.ms.backup;

import java.nio.file.Path;
import java.util.Map;

public class DiffFinder {

    private Map<Path, String> currentBackupHash;

    public void init() {
        this.setCurrentBackupHash(BackupMetadataLoader.loadCurrentBackupHash());
    }

    public void setCurrentBackupHash(Map<Path, String> currentBackupHash) {
        this.currentBackupHash = currentBackupHash;
    }

    public boolean isDifferent(Path sourceFile, Path targetFile) {
        if (hasDifferentSizes(sourceFile, targetFile)) {
            return true;
        }
        if (hasDifferentLastModifiedDate(sourceFile, targetFile)) {
            return true;
        }
        return hasDifferentHashes(sourceFile, targetFile);
    }

    private boolean hasDifferentLastModifiedDate(Path sourceFile, Path targetFile) {
        return true;
    }

    private boolean hasDifferentSizes(Path sourceFile, Path targetFile) {
        return true;
    }

    private boolean hasDifferentHashes(Path sourceFile, Path targetFile) {
        String srcFileHash = generateHash(sourceFile);
        return srcFileHash.equals(currentBackupHash.get(targetFile));
    }

    private String generateHash(Path sourceFile) {
        return "DummyHash";
    }
}
