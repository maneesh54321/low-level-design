package org.example.elevator;

public class Door {

    private boolean open;

    public void open() {
        if (this.isClosed()) {
            this.open = true;
            try {
                Thread.sleep(2000);
                System.out.println("Automatically closing door!!");
                this.close();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Door is already open!!!");
        }
    }

    public void close() {
        if (this.isOpen()) {
            this.open = false;
        } else {
            System.out.println("Door is already closed!!!");
        }
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isClosed() {
        return !open;
    }
}
