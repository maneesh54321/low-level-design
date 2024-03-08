package org.example.button;

public class InActiveButtonState implements ButtonState {
    private final Button button;

    public InActiveButtonState(Button button) {
        this.button = button;
    }

    @Override
    public void handlePress() {
        System.out.println("activating button " + button);
        this.button.setState(new ActiveButtonState(button));
    }
}
