package org.example.button;

public class Button {

    private ButtonState state;

    public Button() {
        this.state = new InActiveButtonState(this);
    }

    public void press(boolean activate) {
        if(activate) {
            activate();
        }
    }

    public void activate() {
        this.state.handlePress();
    }

    public void setState(ButtonState state) {
        this.state = state;
    }
}
