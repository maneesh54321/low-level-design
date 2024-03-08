package org.example.elevator;

public class Door {

    private boolean open;

    public void open() {
        if (this.isClosed()) {
            this.open = true;
        }
    }

    public void close() {
        if (this.isOpen()) {
            this.open = false;
        }
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isClosed() {
        return !open;
    }
}
