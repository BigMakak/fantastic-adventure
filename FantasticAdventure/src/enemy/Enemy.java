package enemy;

/**
 * Created by codecadet on 10/07/2017.
 */
public class Enemy {

    private int health;
    private String name;

    public Enemy(int health,String name) {
        this.health = health;
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }
}
