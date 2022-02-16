package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.Command;
import com.delta.stima.tubes1.entities.Car;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.entities.Lane;
import com.delta.stima.tubes1.enums.RelativePosition;
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

  public boolean isSolutionExist() {
    return this.solutionExist;
  }

  protected void setSolution(Command solution) {
    this.result = solution;
    this.solutionExist = true;
  }

  public Command getSolution() {
    return this.result;
  }

  protected List<Terrain> getVisibleLanes(int lane, RelativePosition pos) {
    try {
      Lane[] selectedLane = this.gameState.lanes.get(lane - 1);
      List<Terrain> result = new ArrayList<>();

      for (Lane l : selectedLane) {
        if (pos == RelativePosition.FRONT &&
            l.position.block >= this.playerCar.position.block ||
            pos == RelativePosition.BACK &&
                l.position.block <= this.playerCar.position.block) {
          result.add(l.terrain);
        }
      }

      return result;
    } catch (IndexOutOfBoundsException exception) {
      return new ArrayList<>();
    }
  }

  protected List<List<Terrain>> getLanes() {
    List<List<Terrain>> l = new ArrayList<>();

    l.add(this.getVisibleLanes(this.playerCar.position.lane, RelativePosition.FRONT));
    l.add(this.getVisibleLanes(this.playerCar.position.lane - 1, RelativePosition.FRONT));
    l.add(this.getVisibleLanes(this.playerCar.position.lane + 1, RelativePosition.FRONT));

    return l;
  }

  private int cybertruckDamage(int lane, int speed) {
    int currentBlock = this.playerCar.position.block;
    int cnt = 0;

    for (Lane l : this.gameState.lanes.get(lane - 1)) {
      if (l.position.block > currentBlock + speed) {
        return cnt * 2;
      } else if (l.position.block > currentBlock && l.isOccupiedByCyberTruck) {
        cnt++;
      }
    }
    return cnt * 2;
  }

  private int terrainDamage(int lane, int speed) {
    int currentBlock = this.playerCar.position.block;
    int damage = 0;

    for (Lane l : this.gameState.lanes.get(lane - 1)) {
      if (l.position.block > this.playerCar.position.block + speed) {
        return damage;
      } else if (l.position.block > currentBlock) {
        switch (l.terrain) {
          case MUD:
            damage += 1;
            break;
          case OIL_SPILL:
          case WALL:
            damage += 2;
            break;
          default:
            break;
        }
      }
    }

    return damage;
  }

  protected boolean isDamageExcedeed(int lane, int speed) {
    return this.totalDamage(lane, speed) >= 2;
  }

  protected int totalDamage(int lane, int speed) {
    return this.cybertruckDamage(lane, speed) + this.terrainDamage(lane, speed);
  }

  protected int getMaxSpeed() {
    switch (this.playerCar.damage) {
      case 0:
        return 15;
      case 1:
        return 9;
      case 2:
        return 8;
      case 3:
        return 6;
      case 4:
        return 3;
      default:
        return 0;
    }
  }

  protected int nextSpeedWhenAccelerate() {
    int nextSpeed = 15;

    if (this.playerCar.speed < 15)
      nextSpeed = 15;
    if (this.playerCar.speed < 9)
      nextSpeed = 9;
    if (this.playerCar.speed < 8)
      nextSpeed = 8;
    if (this.playerCar.speed < 6)
      nextSpeed = 6;
    if (this.playerCar.speed < 5)
      nextSpeed = 5;
    if (this.playerCar.speed < 3)
      nextSpeed = 3;

    return Math.min(this.getMaxSpeed(), nextSpeed);
  }

  public abstract void analyze();
}
