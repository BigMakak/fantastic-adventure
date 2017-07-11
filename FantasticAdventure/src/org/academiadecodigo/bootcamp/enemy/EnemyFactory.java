package org.academiadecodigo.bootcamp.enemy;

/**
 * Created by codecadet on 10/07/2017.
 */
public class EnemyFactory {

    public Enemy createEnemy(EnemyTypes enemyTypes) {
        switch (enemyTypes) {
            case MERCENARY:
                return new Enemy(50, "Mercenary",2);
            case BOUNTYHUNTER:
                return new Enemy(60, "Bounty Hunter",1);
            case JEDI:
                return new Enemy(100, "Jorge",10);
            case SITH:
                return new Enemy(100, "Carl",10);
        }

        return null;
    }
}
