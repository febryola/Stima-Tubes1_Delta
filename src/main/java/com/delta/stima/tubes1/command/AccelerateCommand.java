package com.delta.stima.tubes1.command;

public class AccelerateCommand implements Command {
    @Override
    public String render() {
        return String.format("ACCELERATE");
    }
}
