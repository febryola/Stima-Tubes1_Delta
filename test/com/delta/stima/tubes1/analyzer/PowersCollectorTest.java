package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.ChangeLaneCommand;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.enums.SteerDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PowersCollectorTest {
    private GameState gamestateBuilder(String filePath) {
        try{
            String state = new String(Files.readAllBytes(Paths.get(filePath)));
            GameState gameState = new Gson().fromJson(state, GameState.class);

            return gameState;
        }catch(Exception err) {
            Assertions.fail();
            return null;
        }
    }

    @Test
    public void createCollector() {
        try{
            PowerupsCollector pc = new PowerupsCollector(this.gamestateBuilder("./test/inputTest/Powerups/Powerups-1.json"));
            Assertions.assertNotNull(pc);
        }catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    public void collectorEffect1(){
        PowerupsCollector pc = new PowerupsCollector(this.gamestateBuilder("./test/inputTest/Powerups/Powerups-1.json"));
        Assertions.assertNotNull(pc);
        pc.analyze();
        Assertions.assertFalse(pc.isSolutionExist());
    }

    @Test
    public void collectorEffect2(){
       PowerupsCollector pc = new PowerupsCollector(this.gamestateBuilder("./test/inputTest/Powerups/Powerups-2.json"));
       Assertions.assertNotNull(pc);
       pc.analyze();
       Assertions.assertTrue(pc.isSolutionExist());
       Assertions.assertEquals(pc.getSolution().render(), new ChangeLaneCommand(SteerDirection.RIGHT).render());
    }

    @Test
    public void collectorEffect3(){
        PowerupsCollector pc = new PowerupsCollector(this.gamestateBuilder("./test/inputTest/Powerups/Powerups-3.json"));
        Assertions.assertNotNull(pc);
        pc.analyze();
        Assertions.assertTrue(pc.isSolutionExist());
        Assertions.assertEquals(pc.getSolution().render(), new ChangeLaneCommand(SteerDirection.LEFT).render());
    }
}
