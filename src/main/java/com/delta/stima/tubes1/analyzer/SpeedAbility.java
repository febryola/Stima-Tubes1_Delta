package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.*;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.enums.PowerUps;


public class SpeedAbility extends BaseAnalyzer{
    public SpeedAbility(GameState gameState) {
        super(gameState);
    }
    private boolean checkIsBoostExist(){
        for(PowerUps ps: this.gameState.player.powerups) {
            if(ps == PowerUps.BOOST){
                return true;
            }
        }

        return false;
    }

    public boolean checkKondisiAmanJikaSpeed(){
        int playerBlock = this.playerCar.position.block;
        int opponentBlock = this.opponentCar.position.block;
        for(PowerUps ps: this.gameState.player.powerups) {
            if(ps == PowerUps.BOOST){
                if((opponentBlock-playerBlock>=15)) {
                    return true;
                }

            }
        }

        return false;
    }
    public void analyze(){
        if(this.checkIsBoostExist()){
            if(this.checkKondisiAmanJikaSpeed()) {
                this.setSolution(new BoostCommand());
                return;
            }
        }
        if(this.checkKondisiAmanJikaSpeed()) {
            this.setSolution(new AccelerateCommand());
        }
    }
}
