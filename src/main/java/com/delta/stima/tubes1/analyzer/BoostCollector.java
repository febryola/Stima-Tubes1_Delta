package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.ChangeLaneCommand;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.enums.RelativePosition;
import com.delta.stima.tubes1.enums.Terrain;

import java.util.ArrayList;
import java.util.List;

public class BoostCollector extends BaseAnalyzer {
    public BoostCollector(GameState gameState){
        super(gameState);
    }

    private void setCommandResult(int pos) {
        switch (pos){
            case 1:
                this.setSolution(new ChangeLaneCommand(-1));
                break;
            case 2:
                this.setSolution(new ChangeLaneCommand(1));
                break;
        }
    }

    private List<List<Terrain>> getLanes(){
        List<List<Terrain>> l = new ArrayList<>();

        this.getVisibleLanes(1, RelativePosition.FRONT);

        l.add(this.getVisibleLanes(this.playerCar.position.lane, RelativePosition.FRONT));
        l.add(this.getVisibleLanes(this.playerCar.position.lane - 1, RelativePosition.FRONT));
        l.add(this.getVisibleLanes(this.playerCar.position.lane + 1, RelativePosition.FRONT));

        return l;
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


        for(Terrain type: powerups){
            for(int i = 0; i < l.size(); i++){
                if(l.get(i).contains(type)){
                    this.setCommandResult(i);
                    return;
                }
            }
        }
    }
}
