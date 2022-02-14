package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.ChangeLaneCommand;
import com.delta.stima.tubes1.command.DecelerateCommand;
import com.delta.stima.tubes1.command.FixCommand;
import com.delta.stima.tubes1.command.LizardCommand;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.entities.Lane;
import com.delta.stima.tubes1.enums.PowerUps;
import com.delta.stima.tubes1.enums.SteerDirection;
import com.delta.stima.tubes1.enums.Terrain;

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

    private boolean isCybertruckExist(int lane) {
        int currentBlock = this.playerCar.position.block;

        for(Lane l: this.gameState.lanes.get(lane - 1)){
            if(l.position.block > this.playerCar.speed){
                return false;
            } else if(l.position.block > currentBlock && l.isOccupiedByCyberTruck){
                return true;
            }
        }

        return false;
    }

    private boolean isTerrainObstacleExist(int lane){
        int currentBlock = this.playerCar.position.block;

        for(Lane l: this.gameState.lanes.get(lane - 1)){
            if(l.position.block > this.playerCar.speed){
                return false;
            } else if(l.position.block > currentBlock && (l.terrain == Terrain.MUD || l.terrain == Terrain.OIL_SPILL || l.terrain == Terrain.WALL)){
                return true;
            }
        }

        return false;
    }

    private boolean isObstacleExist(int lane){
        return this.isCybertruckExist(lane) || this.isTerrainObstacleExist(lane);
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

<<<<<<< HEAD
        // Obstacle checking

        // if car position is not on the left side and not on the right side
        if(currentLane > 1 && currentLane < this.gameState.lanes.size() && !this.isObstacleExist(currentLane-1)){
            this.setSolution(new ChangeLaneCommand(-1));
=======
        // Cek Kiri ada obstacle ga?
        if(currentLane > 1 && !this.isObstacleExist(currentLane-1)){
            this.setSolution(new ChangeLaneCommand(SteerDirection.LEFT));
>>>>>>> 303cc81e79ec137220e28221e14f9db88b2a26b9
            return;
        }

        if(currentLane > 1 && currentLane < this.gameState.lanes.size() && !this.isObstacleExist(currentLane+1)) {
            this.setSolution(new ChangeLaneCommand(currentLane+1));
        }

        // if car position is on the left side
        if(currentLane == 1 && !this.isObstacleExist(currentLane+1)) {
            this.setSolution(new ChangeLaneCommand(currentLane+1));
        }

        // if car position is on the right side
        if (currentLane == this.gameState.lanes.size() && !this.isObstacleExist(currentLane-1)) {
            this.setSolution(new ChangeLaneCommand(currentLane-1));
        }

        // Another trick, decrease speed
        this.setSolution(new DecelerateCommand());
    }
}
