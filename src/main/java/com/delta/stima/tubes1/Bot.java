package com.delta.stima.tubes1;

import com.delta.stima.tubes1.analyzer.ObstacleAvoid;
import com.delta.stima.tubes1.analyzer.PowerupsCollector;
import com.delta.stima.tubes1.analyzer.FixingCar;
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
        // ....
        this.ctr.addAnalyzer(new ObstacleAvoid(gameState));
        this.ctr.addAnalyzer(new PowerupsCollector(gameState));
        this.ctr.addAnalyzer(new FixingCar(gameState));
    }

    public Command run() {
        return this.ctr.nextAction();
    }
}
