
package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.enemy.Character;
import org.academiadecodigo.bootcamp.enemy.CharacterSkills;
import org.academiadecodigo.bootcamp.enemy.Enemy;

import java.util.Random;


/**
 * Created by codecadet on 10/07/17.
 */
public class GameEngine {


    //info from players and enemies
    public void getFIleInfo(String fileName) {

    }

    public void sendFileInfo() {

    }


    private void attack(Player player, Enemy enemy, CharacterSkills skill) {


        int playerDice = diceRoll() + player.getForce();
        int enemyDice = diceRoll() + enemy.getForce();

        if (player.getHealth() > 0 && enemy.getHealth() > 0) {

            if (playerDice > enemyDice) {
                giveDamage(enemy, diceRoll());
                return;
            }
            giveDamage(player, diceRoll());
        }
    }


    private void givePoints(Player player, int value, CharacterSkills skill) {

        if (skillCheck(player, value, skill)) {
            player.changeState(true, diceRoll(), skill);
        }
    }


    private boolean skillCheck(Player player, int value, CharacterSkills skill) {


        switch(skill) {

            case HEALTH:
                return player.getHealth() > value;

            case CHARISMA:
                return player.getCharisma() > value;

            case INTELLIGENCE:
                return player.getIntelligence() > value;

            case WILLPOWER:
                return player.getWillpower() > value;

            case SELFCONTROL:
                return player.getSelfControl() > value;

            case FORCE:
                return player.getForce() > value;

            case FORCEBALANCE:
                return player.getBalance() > value;
        }
        return false;
    }


    private void giveDamage(Character character, int value) {

        character.changeState(false, value, CharacterSkills.HEALTH);
    }



    private static int diceRoll() {

        return RandomGenerator.randomGenerator(1,6);
    }
}
