/**
 * Created by codecadet on 10/07/17.
 */
public abstract class Player {

    private int life;
    private int charisma;
    private int intelligence;
    private int willpower;
    private int selfControl;
    private int force;
    private int forceBalance;


    public void changeState(boolean positive, int value, PlayerSkills skill) {

        switch (skill) {

            case LIFE:
                if (positive) {
                    life += value;
                    break;
                }
                life -= value;
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
                forceBalance -= value;
                break;
        }
    }

    public int getLife() {
        return life;
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
