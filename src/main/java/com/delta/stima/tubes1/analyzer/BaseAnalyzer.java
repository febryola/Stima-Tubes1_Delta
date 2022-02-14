package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.Command;
import com.delta.stima.tubes1.entities.Car;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.entities.Lane;
import com.delta.stima.tubes1.enums.RelativePosition;
import com.delta.stima.tubes1.enums.State;
import com.delta.stima.tubes1.enums.Terrain;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAnalyzer {
  protected GameState gameState;
  protected Car playerCar;
  protected Car opponentCar;

  private boolean solutionExist;
  private Command result;

  public BaseAnalyzer(GameState gameState) {
    this.gameState = gameState;
    this.playerCar = gameState.player;
    this.opponentCar = gameState.opponent;
    this.solutionExist = false;
  }

  public boolean isSolutionExist(){
    return this.solutionExist;
  }

  protected void setSolution(Command solution) {
    this.result = solution;
    this.solutionExist = true;
  }

  public Command getSolution(){
    return this.result;
  }

  protected List<Terrain> getVisibleLanes(int lane, RelativePosition pos){
    try {
      Lane[] selectedLane = this.gameState.lanes.get(lane - 1);
      List<Terrain> result = new ArrayList<>();

      for(Lane l : selectedLane){
        if(pos == RelativePosition.FRONT &&
                l.position.block >= this.playerCar.position.block ||
                pos == RelativePosition.BACK &&
                        l.position.block <= this.playerCar.position.block) {
          result.add(l.terrain);
        }
      }

      return result;
    }catch (IndexOutOfBoundsException exception){
      return new ArrayList<>();
    }
  }

  protected List<List<Terrain>> getLanes(){
    List<List<Terrain>> l = new ArrayList<>();

    this.getVisibleLanes(1, RelativePosition.FRONT);

    l.add(this.getVisibleLanes(this.playerCar.position.lane, RelativePosition.FRONT));
    l.add(this.getVisibleLanes(this.playerCar.position.lane - 1, RelativePosition.FRONT));
    l.add(this.getVisibleLanes(this.playerCar.position.lane + 1, RelativePosition.FRONT));

    return l;
  }

  public abstract void analyze();
}
