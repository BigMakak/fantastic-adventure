package org.academiadecodigo.bootcamp.enemy;

/**
 * Created by codecadet on 10/07/2017.
 */
public class Enemy extends Character {

    private int health;
    private String name;
    private int attack;

    public Enemy(int health,String name,int attack) {
        this.health = health;
        this.name = name;
        this.attack = attack;
    }


    @Override
    public void changeState(boolean positive, int value, CharacterSkills skill) {
        super.changeState(positive, value, skill);
    }


    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }
}
