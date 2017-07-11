import enemy.Character;
import enemy.CharacterSkills;

/**
 * Created by codecadet on 10/07/17.
 */
public class Player extends Character {

    private String name;
    private int health;
    private int charisma;
    private int intelligence;
    private int willpower;
    private int selfControl;
    private int force;
    private int forceBalance;


    public Player(String name, int health, int charisma, int intelligence, int willpower, int selfControl, int force, int forceBalance) {
        this.name = name;
        this.health = health;
        this.charisma = charisma;
        this.intelligence = intelligence;
        this.willpower = willpower;
        this.selfControl = selfControl;
        this.force = force;
        this.forceBalance = forceBalance;
    }


    @Override
    public void changeState(boolean positive, int value, CharacterSkills skill) {
        super.changeState(positive, value, skill);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWillpower() {
        return willpower;
    }

    public int getSelfControl() {
        return selfControl;
    }

    public int getForce() {
        return force;
    }

    public int getForceBalance() {
        return forceBalance;
    }
}
