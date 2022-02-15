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
            if ((opponentLane == playerLane)
                    && (opponentBlock > playerBlock)
                    && (opponentBlock - playerBlock <= 20)
                    && (opponentBlock + this.opponentCar.speed > playerBlock + this.playerCar.speed)) {
                this.setSolution(new EmpCommand());
                return;
            }
        }

        if (this.checkIsOilExist()) {
            if ((opponentLane == playerLane)
                    && (opponentBlock < playerBlock)
                    && ((playerBlock - opponentBlock) <= 5)
                    && (opponentBlock + opponentCar.speed >= playerBlock)) {
                this.setSolution(new OilCommand());
                return;
            }
        }

        if (this.checkIsTweetExist()) {
            boolean isOpponenFrontClear = true;
            if(playerBlock - opponentBlock > 5 && playerBlock > opponentBlock ||
                opponentBlock > playerBlock && opponentBlock - playerBlock > 20){
                isOpponenFrontClear = false;
            }else{
                // Prediksi Depan Player
                isOpponenFrontClear = true;
                final Terrain[] destructiveTerrain = {
                        Terrain.WALL,
                        Terrain.MUD,
                        Terrain.OIL_SPILL
                };

                for(Lane l: this.gameState.lanes.get(opponentLane - 1)){
                    if(l.position.block <= opponentBlock){
                        continue;
                    } else if(l.position.block > opponentCar.speed){
                        break;
                    }

                    if(l.isOccupiedByCyberTruck){
                        isOpponenFrontClear = false;
                    }

                    for(Terrain t: destructiveTerrain){
                        if(t == l.terrain) isOpponenFrontClear = false;
                    }

                    if(!isOpponenFrontClear){
                        break;
                    }
                }
            }

            if(isOpponenFrontClear){
                // Pasang Cybertruck
                this.setSolution(new TweetCommand(opponentLane, opponentBlock + opponentCar.speed));
            }
        }
    }
}


