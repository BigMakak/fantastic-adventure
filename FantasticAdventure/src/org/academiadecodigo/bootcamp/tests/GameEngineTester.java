package org.academiadecodigo.bootcamp.tests;

import org.academiadecodigo.bootcamp.GameEngine;
import org.academiadecodigo.bootcamp.Player;
import org.academiadecodigo.bootcamp.RandomGenerator;
import org.academiadecodigo.bootcamp.enemy.CharacterSkills;
import org.academiadecodigo.bootcamp.enemy.Enemy;
import org.academiadecodigo.bootcamp.enemy.EnemyFactory;

/**
 * Created by codecadet on 11/07/17.
 */
public class GameEngineTester {


    public static void main(String[] args) {

        GameEngine gameEngine = new GameEngine();

       Player player = new Player("bob",10,10,10,10,10,10,10);

       // System.out.println(GameEngine.skillCheck(player,22, CharacterSkills.INTELLIGENCE));
    }
}
