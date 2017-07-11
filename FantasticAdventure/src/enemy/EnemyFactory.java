package enemy;

import enemy.Enemy;

/**
 * Created by codecadet on 10/07/2017.
 */
public class EnemyFactory {

    public Enemy createEnemy(EnemyTypes enemyTypes) {
        switch (enemyTypes) {
            case MERCENARY:
                return new Enemy(50, "Mercenary");
            case BOUNTYHUNTER:
                return new Enemy(60, "Bounty Hunter");
            case JEDI:
                return new Enemy(100, "Jorge");
            case SITH:
                return new Enemy(100, "Carl");
        }

        return null;
    }
}
