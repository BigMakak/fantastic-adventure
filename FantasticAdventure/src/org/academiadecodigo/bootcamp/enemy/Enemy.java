package org.academiadecodigo.bootcamp.enemy;

/**
 * Created by codecadet on 10/07/2017.
 */
public class Enemy extends Character {

    private int health;
    private String name;
    private int force;

    public Enemy(int health,String name,int force) {
        this.health = health;
        this.name = name;
        this.force = force;
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

    public int getForce() {
        return this.force;
    }
}
