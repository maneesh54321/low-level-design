package com.ms;

import com.ms.backup.BackupManager;
import com.ms.backup.BackupMetadataLoader;

public class Main {
    public static void main(String[] args) {
        BackupManager backupManager = new BackupManager();

        backupManager.startBackup();
    }
}