package com.learning.parking;

import java.util.Timer;
import java.util.TimerTask;

public class Gate {
    private boolean open;

    public boolean isOpen() {
        return open;
    }

    public void open() {
        this.open = true;
        Timer timer = new Timer();
        timer.schedule(new GateCloseTask(this, timer), 5000);
    }

    public void close() {
        this.open = false;
    }

    private static class GateCloseTask extends TimerTask {

        private final Gate gate;
        private final Timer timer;

        public GateCloseTask(Gate gate, Timer timer) {
            this.gate = gate;
            this.timer = timer;
        }

        @Override
        public void run() {
            this.gate.close();
            timer.cancel();
        }
    }
}
