package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.entities.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import sun.jvm.hotspot.utilities.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PowerupsCollectorTest {
    private GameState gamestateBuilder(String filePath) throws IOException {
        String state = new String(Files.readAllBytes(Paths.get(filePath)));
        GameState gameState = new Gson().fromJson(state, GameState.class);

        return gameState;
    }

    @Test
    public void createCollector() {
        try{
            PowerupsCollector pc = new PowerupsCollector(this.gamestateBuilder("./test/inputTest/Powerups/Powerups-1.json"));
        }catch (Exception e){
            Assertions.fail();
        }
    }
}
