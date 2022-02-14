package com.delta.stima.tubes1.command;

import com.delta.stima.tubes1.enums.Direction;
import com.delta.stima.tubes1.enums.SteerDirection;

public class ChangeLaneCommand implements Command {

    private Direction direction;

    public ChangeLaneCommand(SteerDirection position) {
        if (position == SteerDirection.RIGHT) {
            this.direction = Direction.valueOf("RIGHT");
        } else {
            this.direction = Direction.valueOf("LEFT");
        }
    }

    @Override
    public String render() {
        return String.format("TURN_%s", direction.getLabel());
    }
}
