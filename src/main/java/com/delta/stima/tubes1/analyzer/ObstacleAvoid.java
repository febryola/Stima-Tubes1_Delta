package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.ChangeLaneCommand;
import com.delta.stima.tubes1.command.DecelerateCommand;
import com.delta.stima.tubes1.command.LizardCommand;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.enums.PowerUps;
import com.delta.stima.tubes1.enums.SteerDirection;

import java.util.HashMap;
import java.util.Map;

enum SteerState {
    NOTHING,
    LEFT,
    RIGHT
}

public class ObstacleAvoid extends BaseAnalyzer {
    public ObstacleAvoid(GameState gameState) {
        super(gameState);
    }

    private int prevSpeed() {
        int result = 0;
        int playerSpeed = this.playerCar.speed;
        if (playerSpeed > 3)
            result = 3;
        if (playerSpeed > 5)
            result = 5;
        if (playerSpeed > 6)
            result = 6;
        if (playerSpeed > 8)
            result = 8;
        if (playerSpeed > 9)
            result = 9;

        return result;
    }

    private boolean checkIsLizardExist() {
        for (PowerUps ps : this.gameState.player.powerups) {
            if (ps == PowerUps.LIZARD) {
                return true;
            }
        }

        return false;
    }

    private boolean isObstacleExist(int lane) {
        return this.totalDamage(lane, this.playerCar.speed) > 0;
    }

    public void analyze() {
        int currentLane = this.playerCar.position.lane;

        if (!isObstacleExist(currentLane)) {
            return;
        }

        // Lizard checking
        if (this.checkIsLizardExist()) {
            this.setSolution(new LizardCommand());
            return;
        }

        // Obstacle checking
        Map<SteerState, Integer> steerDamage = new HashMap<>();
        if (this.playerCar.position.lane > 1) {
            steerDamage.put(SteerState.LEFT, this.totalDamage(this.playerCar.position.lane - 1, this.playerCar.speed));
        } else {
            steerDamage.put(SteerState.LEFT, Integer.MAX_VALUE);
        }

        steerDamage.put(SteerState.NOTHING, this.totalDamage(this.playerCar.position.lane, this.playerCar.speed));

        if (this.playerCar.position.lane < this.gameState.lanes.size()) {
            steerDamage.put(SteerState.RIGHT, this.totalDamage(this.playerCar.position.lane + 1, this.playerCar.speed));
        } else {
            steerDamage.put(SteerState.RIGHT, Integer.MAX_VALUE);
        }

        Map.Entry<SteerState, Integer> min = null;
        for (Map.Entry<SteerState, Integer> entry : steerDamage.entrySet()) {
            if (min == null ||
                    min.getValue() > entry.getValue() ||
                    min.getValue() == entry.getValue() && min.getKey().ordinal() > entry.getKey().ordinal()) {
                min = entry;
            }
        }

        if (min.getKey() == SteerState.LEFT) {
            this.setSolution(new ChangeLaneCommand(SteerDirection.LEFT));
            return;
        } else if (min.getKey() == SteerState.RIGHT) {
            this.setSolution(new ChangeLaneCommand(SteerDirection.RIGHT));
            return;
        }

        int lastSpeed = this.playerCar.speed;
        this.playerCar.speed = this.prevSpeed();

        if (!this.isObstacleExist(this.playerCar.position.lane)) {
            this.playerCar.position.block += this.playerCar.speed;
            int lane = this.playerCar.position.lane;

            if (lane > 1 && !this.isObstacleExist(lane - 1) ||
                    lane < 4 && !this.isObstacleExist(lane + 1)) {
                this.setSolution(new DecelerateCommand());
            }
        }

        this.playerCar.speed = lastSpeed;
    }
}
