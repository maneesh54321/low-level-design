package com.ms;

import com.ms.backup.BackupManager;
import com.ms.backup.DiffFinder;

public class Main {
    public static void main(String[] args) {
        var diffFinder = new DiffFinder();
        diffFinder.init();
        BackupManager backupManager = new BackupManager(diffFinder);

        backupManager.startBackup();
    }
}