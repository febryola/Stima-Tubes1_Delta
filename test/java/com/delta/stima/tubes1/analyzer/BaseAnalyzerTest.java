package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.entities.GameState;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;

import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseAnalyzerTest {
    protected GameState gamestateBuilder(String filePath) {
        try{
            String state = new String(Files.readAllBytes(Paths.get(filePath)));
            GameState gameState = new Gson().fromJson(state, GameState.class);

            return gameState;
        }catch(Exception err) {
            Assertions.fail();
            return null;
        }
    }
}
