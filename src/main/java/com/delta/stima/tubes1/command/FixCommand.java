package com.delta.stima.tubes1.command;

public class FixCommand implements Command {

    @Override
    public String render() {
        return String.format("FIX");
    }
}
