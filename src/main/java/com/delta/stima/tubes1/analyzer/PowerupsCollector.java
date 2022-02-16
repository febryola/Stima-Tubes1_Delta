package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.ChangeLaneCommand;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.enums.SteerDirection;
import com.delta.stima.tubes1.enums.Terrain;

import java.lang.Math;
import java.util.List;

public class PowerupsCollector extends BaseAnalyzer {
    public PowerupsCollector(GameState gameState) {
        super(gameState);
    }

    private void setCommandResult(int pos) {
        switch (pos) {
            case 1:
                this.setSolution(new ChangeLaneCommand(SteerDirection.LEFT));
                break;
            case 2:
                this.setSolution(new ChangeLaneCommand(SteerDirection.RIGHT));
                break;
        }
    }

    private int[] LaneDamage() {
        int[] result = new int[3];
        int lane = this.playerCar.position.lane;
        int speed = this.playerCar.speed;

        result[0] = this.totalDamage(lane, speed);

        if (lane > 1) {
            result[1] = this.totalDamage(lane - 1, speed);
        } else {
            result[1] = Integer.MAX_VALUE;
        }

        if (lane < this.gameState.lanes.size()) {
            result[2] = this.totalDamage(lane + 1, speed);
        } else {
            result[2] = Integer.MAX_VALUE;
        }

        return result;
    }

    public void analyze() {
        List<List<Terrain>> l = this.getLanes();

        final Terrain[] powerups = {
                Terrain.BOOST,
                Terrain.LIZARD,
                Terrain.TWEET,
                Terrain.EMP,
                Terrain.OIL_POWER
        };

        final int[] damage = LaneDamage();

        for (Terrain type : powerups) {
            for (int i = 0; i < l.size(); i++) {
                for (int j = 0; j < Math.min(this.playerCar.speed + 1, l.get(i).size()); j++) {
                    if (l.get(i).get(j) == type && (damage[i] < 2 || i == 0)) {
                        this.setCommandResult(i);
                        return;
                    }
                }
            }
        }
    }
}
