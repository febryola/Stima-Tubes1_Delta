package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.Command;
import com.delta.stima.tubes1.entities.Car;
import com.delta.stima.tubes1.entities.GameState;

public class BaseAnalyzer {
  protected GameState gameState;
  protected Car playerCar;
  protected Car opponentCar;
  private boolean solutionExist;
  private Command result;

  public BaseAnalyzer(GameState gameState) {
    this.gameState= gameState;
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

  public void analyze() {}
}
