package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.ChangeLaneCommand;
import com.delta.stima.tubes1.command.DecelerateCommand;
import com.delta.stima.tubes1.command.LizardCommand;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.enums.RelativePosition;
import com.delta.stima.tubes1.enums.State;

import java.util.ArrayList;
import java.util.List;

public class ObstacleAvoid extends BaseAnalyzer {
    public  ObstacleAvoid(GameState gameState) {
        super(gameState);
    }

    private void setCommandResult(int pos) {
        switch (pos){
            case 1:
                this.setSolution(new LizardCommand());
            case 2:
                this.setSolution(new ChangeLaneCommand(-1));
                break;
            case 3:
                this.setSolution(new ChangeLaneCommand(1));
                break;
            case 4:
                this.setSolution(new DecelerateCommand());
        }
    }

    private List<List<State>> getLanes(){
        List<List<State>> l = new ArrayList<>();

        this.getVisibleLanes(1, RelativePosition.FRONT);

        l.add(this.getVisibleLanes(this.playerCar.position.lane, RelativePosition.FRONT));
        l.add(this.getVisibleLanes(this.playerCar.position.lane - 1, RelativePosition.FRONT));
        l.add(this.getVisibleLanes(this.playerCar.position.lane + 1, RelativePosition.FRONT));

        return l;
    }

    public void analyze() {
        List<List<State>> l = this.getLanes();

        final State[] obstacle = {
                State.HIT_EMP,
                State.HIT_CYBER_TRUCK,
                State.HIT_MUD,
                State.HIT_OIL,
                State.HIT_WALL
        };


        for(State type: obstacle){
            for(int i = 0; i < l.size() - 1; i++){
                if(l.get(i+1).contains(type)){
                    this.setCommandResult(i);
                    return;
                }
            }
        }
    }
}
