package com.delta.stima.tubes1.command;

public class DecelerateCommand implements Command {

    @Override
    public String render() {
        return String.format("DECELERATE");
    }
}
