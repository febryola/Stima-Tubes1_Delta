package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.ChangeLaneCommand;
import com.delta.stima.tubes1.command.DecelerateCommand;
import com.delta.stima.tubes1.command.LizardCommand;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.entities.Lane;
import com.delta.stima.tubes1.enums.PowerUps;
import com.delta.stima.tubes1.enums.SteerDirection;
import com.delta.stima.tubes1.enums.Terrain;

enum Damage {

}

public class ObstacleAvoid extends BaseAnalyzer {
    public ObstacleAvoid(GameState gameState) {
        super(gameState);
    }

    private boolean checkIsLizardExist(){
        for(PowerUps ps: this.gameState.player.powerups) {
            if(ps == PowerUps.LIZARD){
                return true;
            }
        }

        return false;
    }

    private int cybertruckDamage(int lane) {
        int currentBlock = this.playerCar.position.block;
        int cnt = 0;

        for(Lane l: this.gameState.lanes.get(lane - 1)){
            if(l.position.block > this.playerCar.speed){
                return cnt * 2;
            } else if(l.position.block > currentBlock && l.isOccupiedByCyberTruck){
                cnt++;
            }
        }
        return cnt * 2;
    }

    private int terrainDamage(int lane) {
        int currentBlock = this.playerCar.position.block;
        int damage = 0;

        for(Lane l: this.gameState.lanes.get(lane - 1)){
            if(l.position.block > this.playerCar.position.block + this.playerCar.speed){
                return damage;
            } else if(l.position.block > currentBlock){
                switch (l.terrain){
                    case MUD:
                        damage += 1;
                        break;
                    case OIL_SPILL:
                    case WALL:
                        damage += 2;
                        break;
                }
            }
        }

        return damage;
    }

    private boolean isObstacleExist(int lane){
        return this.totalDamage(lane) > 0;
    }

    private int totalDamage(int lane){
        return this.cybertruckDamage(lane) + this.terrainDamage(lane);
    }

    public void analyze() {
        int currentLane = this.playerCar.position.lane;

        if(!isObstacleExist(currentLane)){
            return;
        }

        // Lizard checking
        if(this.checkIsLizardExist()){
            this.setSolution(new LizardCommand());
            return;
        }

        // Obstacle checking


        // Another trick, decrease speed
        this.setSolution(new DecelerateCommand());
    }
}
