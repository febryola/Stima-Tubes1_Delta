package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.*;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.entities.Lane;
import com.delta.stima.tubes1.enums.PowerUps;
import com.delta.stima.tubes1.enums.Terrain;

public class AttackAbility extends BaseAnalyzer {
    public AttackAbility(GameState gameState) {
        super(gameState);
    }

    private boolean checkIsOilExist() {
        for (PowerUps ps : this.gameState.player.powerups) {
            if (ps == PowerUps.OIL) {
                return true;
            }
        }

        return false;
    }

    private boolean checkIsEmpExist() {
        for (PowerUps ps : this.gameState.player.powerups) {
            if (ps == PowerUps.EMP) {
                return true;
            }
        }

        return false;
    }

    private boolean isCybertruckExist(int lane) {
        int currentBlock = this.playerCar.position.block;

        for (Lane l : this.gameState.lanes.get(lane - 1)) {
            if (l.position.block > this.playerCar.speed) {
                return false;
            } else if (l.position.block > currentBlock && l.isOccupiedByCyberTruck) {
                return true;
            }
        }

        return false;
    }

    private boolean checkIsTweetExist() {
        for (PowerUps ps : this.gameState.player.powerups) {
            if (ps == PowerUps.TWEET) {
                return true;
            }
        }

        return false;
    }

    public void analyze() {
        int opponentLane = this.opponentCar.position.lane;
        int playerLane = this.playerCar.position.lane;
        int opponentBlock = this.opponentCar.position.block;
        int playerBlock = this.playerCar.position.block;
        if (this.checkIsEmpExist()) {
            if ((opponentLane == playerLane) && (opponentBlock > playerBlock) && ((opponentBlock - playerBlock) < 20)) {
                this.setSolution(new EmpCommand());
            }
            return;

        } else if (this.checkIsOilExist()) {
            if ((opponentLane == playerLane) &&
                    (opponentBlock < playerBlock) && ((playerBlock - opponentBlock) < 5)) {
                this.setSolution(new OilCommand());
            } else if ((opponentLane == playerLane) &&
                    (opponentBlock < playerBlock) && ((playerBlock - opponentBlock) < 5) &&
                    ((this.opponentCar.speed) > this.playerCar.speed)) {
                this.setSolution(new OilCommand());
            }
            return;
        } else if (this.checkIsTweetExist()) {
            if (this.isCybertruckExist(opponentLane)) {
                this.setSolution(new TweetCommand(opponentLane, opponentBlock));
            }
            return;
        }
    }
}


