package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.FixCommand;
import com.delta.stima.tubes1.entities.GameState;

public class FixingCar extends BaseAnalyzer {
    public FixingCar(GameState gs) {
        super(gs);
    }

    public void analyze() {
        if (this.gameState.player.damage > 0) {
            this.setSolution(new FixCommand());
        }
    }
}
