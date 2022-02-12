package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.DoNothingCommand;
import com.delta.stima.tubes1.entities.GameState;

public class BoostCollector extends BaseAnalyzer {
    public BoostCollector(GameState gameState){
        super(gameState);
    }

    public void analyze(){
        // Tentukan kondisinya gimana
        if(kondisi){
            this.setSolution(new DoNothingCommand()); // Klo emang memenuhi syarat, pasang solusi
        }
    }
}
