import enemy.Character;
import enemy.CharacterSkills;
import enemy.Enemy;

/**
 * Created by codecadet on 10/07/17.
 */
public class GameEngine {


    //info from players and enemies
    public void getFIleInfo(String fileName) {

    }

    public void sendFileInfo() {

    }


    private void attack(Player player, Enemy enemy, CharacterSkills skill) {

        int playerDice = diceRoll() + player.getForce();
        int enemyDice = diceRoll() + enemy.getForce(); //TODO: getter is missing in Enemy

        if (player.getHealth() > 0 && enemy.getHealth() > 0) {

            if (playerDice > enemyDice) {
                giveDamage(enemy, diceRoll());
                return;
            }
            giveDamage(player, diceRoll());
        }
    }


    private void givePoints(Player player, int value, CharacterSkills skill) {

        if (skillCheck(player, value, skill)) {
            player.changeState(true, diceRoll(), skill);
        }
    }


    private boolean skillCheck(Player player, int value, CharacterSkills skill) {

        switch (skill) {

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


    private void giveDamage(Character character, int value) {

        character.changeState(false, value, CharacterSkills.HEALTH);
    }


    private static int diceRoll() { //TODO: change to randomGenerator

        return (int) (Math.random() * 6) + 1;
    }
}
