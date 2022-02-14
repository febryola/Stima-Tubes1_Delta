package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.enums.RelativePosition;
import com.delta.stima.tubes1.enums.Terrain;

import java.util.ArrayList;
import java.util.List;



public class BoostCollector extends BaseAnalyzer {
    public BoostCollector(GameState gameState){
        super(gameState);
    }

    public void analyze(){
        List<List<Terrain>> l = new ArrayList<>();

        l.add(this.getVisibleLanes(this.playerCar.position.lane - 1, RelativePosition.FRONT));
        l.add(this.getVisibleLanes(this.playerCar.position.lane, RelativePosition.FRONT));
        l.add(this.getVisibleLanes(this.playerCar.position.lane + 1, RelativePosition.FRONT));


    }
}
