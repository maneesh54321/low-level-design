package com.learning.parking;

import java.util.Timer;
import java.util.TimerTask;

public class Gate {

    private final int gateNo;
    private boolean open = false;

    public Gate(int gateNo) {
        this.gateNo = gateNo;
    }

    public boolean isOpen() {
        return open;
    }

    public void open() {
        this.open = true;
        System.out.println("Gate " + gateNo + " opens!!");
        Timer timer = new Timer();
        timer.schedule(new GateCloseTask(this, timer), 500);
    }

    public int getGateNo() {
        return gateNo;
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
            System.out.println("Gate " + this.gate.gateNo + " closed!!");
            timer.cancel();
        }
    }
}
