package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.entities.GameState;

public class BoostCollector extends BaseAnalyzer {
    public BoostCollector(GameState gameState){
        super(gameState);
    }

    public void analyze(){
        // ...
        if(kondisi){
            this.setSolution(...);
        }
    }
}
