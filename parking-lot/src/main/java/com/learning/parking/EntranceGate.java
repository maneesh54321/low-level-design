package com.learning.parking;

import java.util.Timer;
import java.util.TimerTask;

public class EntranceGate {
    private boolean open;

    public boolean isOpen() {
        return open;
    }

    public void open() {
        this.open = true;
        Timer timer = new Timer();
        timer.schedule(new GateCloseTask(this), 5000);
    }

    public void close() {
        this.open = false;
    }

    private static class GateCloseTask extends TimerTask {

        private final EntranceGate gate;

        public GateCloseTask(EntranceGate gate) {
            this.gate = gate;
        }

        @Override
        public void run() {
            this.gate.close();
            this.cancel();
        }
    }
}
