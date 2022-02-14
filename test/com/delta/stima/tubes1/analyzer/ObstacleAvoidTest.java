package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.ChangeLaneCommand;
import com.delta.stima.tubes1.entities.GameState;
import com.delta.stima.tubes1.enums.SteerDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ObstacleAvoidTest {
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
    public void createObstacle(){
        try{
            ObstacleAvoid oa = new ObstacleAvoid(this.gamestateBuilder("./test/inputTest/Obstacle/Obstacle-1.json"));
            Assertions.assertNotNull(oa);
        }catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    public void testObstacle02(){
        ObstacleAvoid oa = new ObstacleAvoid(this.gamestateBuilder("./test/inputTest/Obstacle/Obstacle-2.json"));
        Assertions.assertNotNull(oa);
        oa.analyze();
        Assertions.assertTrue(oa.isSolutionExist());
        Assertions.assertEquals(new ChangeLaneCommand(SteerDirection.RIGHT).render(), oa.getSolution().render());
    }

    /*
        Map:
        [░░░░░░░░▓░░░░░░░░▓░░*░░░░░]
        [░░░░░1░░░▓▓░∱░░▓░░░░░░T░░░]
        [░░░░░░░░░░Φ░▓▓░░░░░░░░░░░░]
        [░░░░░░░░░░░░░░»░░░░░░░░░░░]

        Speed: 10g
     */
    @Disabled("Masih menunggu implementasinya")
    @Test
    public void testObstacle03(){
        ObstacleAvoid oa = new ObstacleAvoid(this.gamestateBuilder("./test/inputTest/Obstacle/Obstacle-3.json"));
        Assertions.assertNotNull(oa);
        oa.analyze();
        Assertions.assertTrue(oa.isSolutionExist());
        Assertions.assertEquals(new ChangeLaneCommand(SteerDirection.RIGHT).render(), oa.getSolution().render());
    }

    /*
        Map:
        [░░░░░░░░*▓▓░░░░░░░░░░░░░░░]
        [░░░░░1░»░▓░░░░░░░░░░░░Φ░░░]
        [░░░░░░░░░▓░░░░░*░░░░░░░░░░]
        [░░▓░░░░░░░░░░░░░░░░░░░T░░░]

        Speed: 6
     */
    @Disabled("Masih menunggu implementasinya")
    @Test
    public void testObstacle04(){
        ObstacleAvoid oa = new ObstacleAvoid(this.gamestateBuilder("./test/inputTest/Obstacle/Obstacle-3.json"));
        Assertions.assertNotNull(oa);
        oa.analyze();
        Assertions.assertFalse(oa.isSolutionExist());
    }
}
