package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.*;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.entities.Lane;
import com.delta.stima.tubes1.enums.PowerUps;

public class SpeedAbility extends BaseAnalyzer {
    public SpeedAbility(GameState gameState) {
        super(gameState);
    }

    private boolean checkIsBoostExist() {
        for (PowerUps ps : this.gameState.player.powerups) {
            if (ps == PowerUps.BOOST) {
                return true;
            }
        }

        return false;
    }

    public boolean checkKondisiAmanJikaSpeed() {
        int playerBlock = this.playerCar.position.block;
        int opponentBlock = this.opponentCar.position.block;
        for (PowerUps ps : this.gameState.player.powerups) {
            if (ps == PowerUps.BOOST) {
                if ((opponentBlock - playerBlock >= 15)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int cybertruckDamage(int lane, int speed) {
        int currentBlock = this.playerCar.position.block;
        int cnt = 0;

        for (Lane l : this.gameState.lanes.get(lane - 1)) {
            if (l.position.block > currentBlock + speed) {
                return cnt * 2;
            } else if (l.position.block > currentBlock && l.isOccupiedByCyberTruck) {
                cnt++;
            }
        }
        return cnt * 2;
    }

    private int terrainDamage(int lane, int speed) {
        int currentBlock = this.playerCar.position.block;
        int damage = 0;

        for (Lane l : this.gameState.lanes.get(lane - 1)) {
            if (l.position.block > this.playerCar.position.block + speed) {
                return damage;
            } else if (l.position.block > currentBlock) {
                switch (l.terrain) {
                    case MUD:
                        damage += 1;
                        break;
                    case OIL_SPILL:
                    case WALL:
                        damage += 2;
                        break;
                    default:
                        break;
                }
            }
        }

        return damage;
    }

    private boolean isDamageExcedeed(int lane, int speed) {
        return this.totalDamage(lane, speed) >= 2;
    }

    private int totalDamage(int lane, int speed) {
        return this.cybertruckDamage(lane, speed) + this.terrainDamage(lane, speed);
    }

    private int getMaxSpeed() {
        switch (this.playerCar.damage) {
            case 0:
                return 15;
            case 1:
                return 9;
            case 2:
                return 8;
            case 3:
                return 6;
            case 4:
                return 3;
            default:
                return 0;
        }
    }

    private int nextSpeedWhenAccelerate() {
        int nextSpeed = 15;

        if (this.playerCar.speed < 15)
            nextSpeed = 15;
        if (this.playerCar.speed < 9)
            nextSpeed = 9;
        if (this.playerCar.speed < 8)
            nextSpeed = 8;
        if (this.playerCar.speed < 6)
            nextSpeed = 6;
        if (this.playerCar.speed < 5)
            nextSpeed = 5;
        if (this.playerCar.speed < 3)
            nextSpeed = 3;

        return Math.min(this.getMaxSpeed(), nextSpeed);
    }

    public void analyze() {
        if (this.checkIsBoostExist()
                && !this.isDamageExcedeed(this.playerCar.position.lane, this.getMaxSpeed())
                && !this.playerCar.boosting) {
            this.setSolution(new BoostCommand());

        } else if (!this.isDamageExcedeed(this.playerCar.position.lane, this.nextSpeedWhenAccelerate())
                && this.playerCar.speed < this.getMaxSpeed()) {
            this.setSolution(new AccelerateCommand());
        }
    }
}
