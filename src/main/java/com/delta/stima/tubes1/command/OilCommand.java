package com.delta.stima.tubes1.command;

public class OilCommand implements Command {

    @Override
    public String render() {
        return String.format("USE_OIL");
    }
}
