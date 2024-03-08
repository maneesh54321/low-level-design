package org.example.button;

public class ActiveButtonState implements ButtonState {

    private final Button button;

    public ActiveButtonState(Button button) {
        this.button = button;
    }

    @Override
    public void handlePress() {
        // do nothing
    }
}
