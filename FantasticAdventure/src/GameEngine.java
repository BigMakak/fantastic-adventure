/**
 * Created by codecadet on 10/07/17.
 */
public class GameEngine {


    private boolean checkState(Player player, int value, PlayerSkills skill){

        switch(skill){

            case LIFE:
                return player.getLife() > value;

            case CHARISMA:
                return player.getCharisma() > value;

            case INTELLIGENCE:
                return player.getIntelligence() > value;

            case WILLPOWER:
                return player.getWillpower() > value;

            case SELFCONTROL:
                return player.getSelfControl() > value;

            case FORCE:
                return player.getForce() > value;

            case FORCEBALANCE:
                return player.getForceBalance() > value;

        }
        return false;
    }

    private void giveDamage(Player player, int value){

        player.changeState(false, value, PlayerSkills.LIFE);

    }

    private void givePoints(Player player, int value, PlayerSkills skill) {

        player.changeState(true, value, skill);

    }
}
