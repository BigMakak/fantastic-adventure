/**
 * Created by codecadet on 10/07/17.
 */
public class GameEngine {


    //info from players and enemies
    public void getFIleInfo() {

    }

    public void sendFileInfo(){

    }


    private boolean checkState(Player player, int value, CharacterSkills skill){

        switch(skill){

            case HEALTH:
                return player.getHealth() > value;

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

        player.changeState(false, value, CharacterSkills.HEALTH);

    }

    private void givePoints(Player player, int value, CharacterSkills skill) {

        player.changeState(true, value, skill);

    }
}
