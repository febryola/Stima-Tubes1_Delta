package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.ChangeLaneCommand;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.enums.RelativePosition;
import com.delta.stima.tubes1.enums.SteerDirection;
import com.delta.stima.tubes1.enums.Terrain;

import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

public class PowerupsCollector extends BaseAnalyzer {
    public PowerupsCollector(GameState gameState){
        super(gameState);
    }

    private void setCommandResult(int pos) {
        switch (pos){
            case 1:
                this.setSolution(new ChangeLaneCommand(SteerDirection.LEFT));
                break;
            case 2:
                this.setSolution(new ChangeLaneCommand(SteerDirection.RIGHT));
                break;
        }
    }

    public void analyze(){
        List<List<Terrain>> l = this.getLanes();

        final Terrain[] powerups = {
           Terrain.BOOST,
           Terrain.LIZARD,
           Terrain.TWEET,
           Terrain.EMP,
           Terrain.OIL_POWER
        };

        for(Terrain type: powerups) {
            for(int i = 0; i < l.size(); i++){
                for(int j = 0; j < Math.min(this.playerCar.speed + 1, l.get(i).size()); j++){
                    if(l.get(i).get(j) == type){
                        this.setCommandResult(i);
                        return;
                    }
                }
            }
        }
    }
}
