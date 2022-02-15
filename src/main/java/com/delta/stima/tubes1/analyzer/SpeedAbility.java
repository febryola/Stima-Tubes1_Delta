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
<<<<<<< HEAD

    public boolean checkKondisiAmanJikaSpeed(){
        int playerBlock = this.playerCar.position.block;
        int opponentBlock = this.opponentCar.position.block;
        for(PowerUps ps: this.gameState.player.powerups) {
            if(ps == PowerUps.BOOST){
                if((opponentBlock-playerBlock>=15)) {
                    return true;
                }
=======
    public void analyze(){
        if(this.checkIsBoostExist()){
            this.setSolution(new BoostCommand());
            return;
        }
>>>>>>> 604eeb13e6eee2f4e206b1f96952aa2346885d18

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
