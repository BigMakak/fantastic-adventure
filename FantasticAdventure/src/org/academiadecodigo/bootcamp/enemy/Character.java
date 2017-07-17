package org.academiadecodigo.bootcamp.enemy;


/**
 * Created by codecadet on 11/07/17.
 */
public abstract class Character {

    private String name;
    private int health;
    private int charisma;
    private int intelligence;
    private int willpower;
    private int selfControl;
    private int force;
    private int forceBalance;




    public void changeState(boolean positive, int value, CharacterSkills skill) {

        switch (skill) {

            case HEALTH:
                if (positive) {
                    health += value;
                    break;
                }
                health -= value;
                break;

            case CHARISMA:
                if (positive) {
                    charisma += value;
                    break;
                }
                charisma -= value;
                break;

            case INTELLIGENCE:
                if (positive) {
                    intelligence += value;
                    break;
                }
                intelligence -= value;
                break;

            case WILLPOWER:
                if (positive) {
                    willpower += value;
                    break;
                }
                willpower -= value;
                break;

            case SELFCONTROL:
                if (positive) {
                    selfControl += value;
                    break;
                }
                selfControl -= value;
                break;

            case FORCE:
                if (positive) {
                    force += value;
                    break;
                }
                force -= value;
                break;

            case FORCEBALANCE:
                if (positive) {
                    forceBalance += value;
                    break;
                }
                forceBalance += value;
                break;
        }
    }
}


