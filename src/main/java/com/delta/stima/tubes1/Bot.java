package com.delta.stima.tubes1;

import com.delta.stima.tubes1.analyzer.BoostCollector;
import com.delta.stima.tubes1.command.*;
import com.delta.stima.tubes1.controller.Controller;
import com.delta.stima.tubes1.entities.*;

public class Bot {
    
    private GameState gameState;
    private Controller ctr;
    
    public Bot(GameState gameState) {
        this.gameState = gameState;
        this.ctr = new Controller();
        this.register();
    }
    
    private void register(){
        this.ctr.addAnalyzer(new BoostCollector(gameState));
    }

    public Command run() {
        return ctr.nextAction();
    }
}
