package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.ChangeLaneCommand;
import com.delta.stima.tubes1.enums.SteerDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

public class ObstacleAvoidTest extends BaseAnalyzerTest {
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
    @Test
    public void testObstacle03(){
        ObstacleAvoid oa = new ObstacleAvoid(this.gamestateBuilder("./test/inputTest/Obstacle/Obstacle-3.json"));
        Assertions.assertNotNull(oa);
        oa.analyze();
        Assertions.assertTrue(oa.isSolutionExist());
        Assertions.assertEquals(new ChangeLaneCommand(SteerDirection.LEFT).render(), oa.getSolution().render());
    }

    /*
        Map:
        [░░░░░░░░*▓▓░░░░░░░░░░░░░░░]
        [░░░░░1░»░▓░░░░░░░░░░░░Φ░░░]
        [░░░░░░░░░▓░░░░░*░░░░░░░░░░]
        [░░▓░░░░░░░░░░░░░░░░░░░T░░░]

        Speed: 6
     */
    @Test
    public void testObstacle04(){
        ObstacleAvoid oa = new ObstacleAvoid(this.gamestateBuilder("./test/inputTest/Obstacle/Obstacle-4.json"));
        Assertions.assertNotNull(oa);
        oa.analyze();
        Assertions.assertFalse(oa.isSolutionExist());
    }
}
