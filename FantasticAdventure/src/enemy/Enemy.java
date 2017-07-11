package enemy;

/**
 * Created by codecadet on 10/07/2017.
 */
public class Enemy extends Character {

    private int health;
    private String name;

    public Enemy(int health,String name) {
        this.health = health;
        this.name = name;
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
