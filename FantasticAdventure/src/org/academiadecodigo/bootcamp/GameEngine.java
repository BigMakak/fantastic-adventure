package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.enemy.Character;
import org.academiadecodigo.bootcamp.enemy.CharacterSkills;
import org.academiadecodigo.bootcamp.enemy.Enemy;

import java.util.Random;


/**
 * Created by codecadet on 10/07/17.
 */
public class GameEngine {


    public static void attack(Player player, Enemy enemy/*, CharacterSkills skill*/) {


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


    public static void givePoints(Player player, int value, String skill) {

        if (value > 0) {
            player.changeState(true, value, skillConverter(skill));
        } else if (value < 0) {
            player.changeState(false, value, skillConverter(skill));
        }
    }

    public static CharacterSkills skillConverter(String s) {

CharacterSkills skill = null;

        switch (s) {
            case "I":
                skill = CharacterSkills.INTELLIGENCE;
                break;
            case "H":
                skill = CharacterSkills.HEALTH;
                break;
            case "C":
                skill = CharacterSkills.CHARISMA;
                break;
            case "W":
                skill = CharacterSkills.WILLPOWER;
                break;
            case "S":
                skill = CharacterSkills.SELFCONTROL;
                break;
            case "B":
                skill = CharacterSkills.FORCEBALANCE;
                break;
            case "F":
                skill = CharacterSkills.FORCE;
                break;

        }
        return skill;
    }

    public static boolean skillCheck(Player player, int value, String s) {

        CharacterSkills skill = skillConverter(s);

        switch (skill) {

            case HEALTH:
                return (player.getHealth() + diceRoll()) > value;

            case CHARISMA:
                return (player.getCharisma() + diceRoll()) > value;

            case INTELLIGENCE:
                return (player.getIntelligence() + diceRoll()) > value;

            case WILLPOWER:
                return (player.getWillpower() + diceRoll()) > value;

            case SELFCONTROL:
                return (player.getSelfControl() + diceRoll()) > value;

            case FORCE:
                return (player.getForce() + diceRoll()) > value;

            case FORCEBALANCE:
                return (player.getBalance() + diceRoll()) > value;
        }
        return false;
    }


    public static void giveDamage(Character character, int value) {

        character.changeState(false, value, CharacterSkills.HEALTH);
    }


    public static int diceRoll() {

        int diceRoll = RandomGenerator.randomGenerator(2, 12);
        System.out.println("diceRoll: " + diceRoll);
        return diceRoll;
    }
}
