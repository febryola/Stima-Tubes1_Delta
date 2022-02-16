package com.delta.stima.tubes1.analyzer;

import com.delta.stima.tubes1.command.EmpCommand;
import com.delta.stima.tubes1.command.OilCommand;
import com.delta.stima.tubes1.command.TweetCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttackAbilityTest extends BaseAnalyzerTest {
    /*
        TEST 1:

        Map:
        [░ΦT░░1*░░░Φ░░░░░*░░░░░░░░░]
        [░░░░░░░░░░░░░▓▓░░░░░░#░░░░]
        [░░░░░░Φ░░░░░▓▓░░░T░░Φ░░░░░]
        [░░░*Φ░░▓░░░▓▓▓░░2░»░∱░░░░░]

        Ability :
        "OIL",
        "TWEET",
        "BOOST",
        "LIZARD",
        "OIL",
        "TWEET"
     */

    @Test
    public void testAttack01(){
        AttackAbility ab = new AttackAbility(this.gamestateBuilder("./test/inputTest/Attack/Attack-1.json"));
        ab.analyze();
        Assertions.assertTrue(ab.isSolutionExist());
        Assertions.assertEquals(ab.getSolution().render(), new TweetCommand(4,66).render());
    }

    /*
        TEST 2:

        Map:
        [░ΦT░░1*░░░Φ░2░░░*░░░░░░░░░]
        [░░░░░░░░░░░░░▓▓░░░░░░#░░░░]
        [░░░░░░Φ░░░░░▓▓░░░T░░Φ░░░░░]

        [░░░*Φ░░▓░░░▓▓▓░░░░»░∱░░░░░]

        Ability :
        "OIL",
        "TWEET",
        "BOOST",
        "LIZARD",
        "OIL",
        "TWEET",
        "EMP
     */
    @Test
    public void testAttack02(){
        AttackAbility ab = new AttackAbility(this.gamestateBuilder("./test/inputTest/Attack/Attack-2.json"));
        ab.analyze();
        Assertions.assertTrue(ab.isSolutionExist());
        Assertions.assertEquals(ab.getSolution().render(), new EmpCommand().render());
    }

    /*
        TEST 3:
        [▓░░░░░░░░░░░░░░░░░░▓░░░░░░]
        [▓░░░░░░░░░░░░░░░░░░░▓▓░∱░░]
        [2░░░░1░░*░░░░░░░░░░░░Φ░▓▓░]
        [░▓░░░░░░░Φ░░░░░░░░░░░░░░░»]

        Ability : Oil, EMP
    */
    @Test
    public void testAttack03(){
        AttackAbility ab = new AttackAbility(this.gamestateBuilder("./test/inputTest/Attack/Attack-3.json"));
        ab.analyze();
        Assertions.assertTrue(ab.isSolutionExist());
        Assertions.assertEquals(ab.getSolution().render(), new OilCommand().render());
    }

}
