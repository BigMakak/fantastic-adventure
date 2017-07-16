package org.academiadecodigo.bootcamp.enemy;

/**
 * Created by codecadet on 10/07/2017.
 */
public class EnemyFactory {

    public Enemy createEnemy(EnemyTypes enemyTypes, int health) {
        switch (enemyTypes) {
            case MERCENARY:
                return new Enemy(health, "Mercenary",2);
            case BOUNTYHUNTER:
                return new Enemy(health, "Bounty Hunter",1);
            case JEDI:
                return new Enemy(health, "Jorge",10);
            case SITH:
                return new Enemy(health, "Carl",10);
        }

        return null;
    }
}
