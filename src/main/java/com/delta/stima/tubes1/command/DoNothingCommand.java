package com.delta.stima.tubes1.command;

public class DoNothingCommand implements Command {
    @Override
    public String render() {
        return "NOTHING";
    }
}
