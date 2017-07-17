package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.enemy.CharacterSkills;
import org.academiadecodigo.bootcamp.enemy.Enemy;
import org.academiadecodigo.bootcamp.enemy.EnemyFactory;
import org.academiadecodigo.bootcamp.enemy.EnemyTypes;

/**
 * Created by codecadet on 12/07/2017.
 */
public class Game implements MessageHandler {


    private Player player;
    private EnemyFactory enemyFactory;
    //private GameEngine gameEngine;
    private String[] headerBody;
    private String fileName = "";
    private String messageToServer;
    private boolean calcDone;


    public void init() {
        enemyFactory = new EnemyFactory();
        //gameEngine = new GameEngine();
        player = new Player("Jorge", 10, 10, 10, 10, 10, 10, 10);
        headerBody = FileManager.load("000").split("\\n", 2);
        messageToServer = headerBody[1];
    }


    public void sendStory(String clientChoice, String header) {

        int numberOfOptions = Integer.parseInt(header.substring(3, 4));
        System.out.println("Number of Options : " + numberOfOptions);

        String option1 = header.substring(4, 7);
        String option2 = header.substring(7, 10);
        String option3 = header.substring(10, 13);
        String option4 = header.substring(13, 16);
        System.out.println("did substring");

        String[] checksInfo = header.split(" ", 8);
        System.out.println("did split");

        String force = checksInfo[1];
        String skill = checksInfo[2];
        int checkScore = Integer.parseInt(checksInfo[3]);

        String points = checksInfo[4];
        String hasEnemy = checksInfo[5];
        int enemyHealth = Integer.parseInt(checksInfo[6]);
        String fileIfFails = checksInfo[7].trim();
        System.out.println("file if fails: " + fileIfFails);

        System.out.println("checks array: " + force + "-" + skill + "-" + checkScore + "-" + points + "-" + hasEnemy + "-" + enemyHealth);


        // SkillChecks
        boolean skillCheck = true;
        System.out.println("skill is: " + skill);

        if (!skill.equals("0") && checkScore != 0) {
            System.out.println("entered skillcheck, checkscore: " + checkScore);

            if (GameEngine.skillCheck(player, checkScore, skill)) {
                skillCheck = true;
            } else {
                skillCheck = false;
            }
        }


        System.out.println(option1 + " " + option2 + " " + option3);
        System.out.println("Client choice is " + clientChoice);

        System.out.println("passed skillCheck: " + skillCheck);

        if (skillCheck) {

            switch (clientChoice) {

                case "1":
                    fileName = option1;
                    System.out.println("File selected :" + fileName);
                    break;
                case "2":
                    if (numberOfOptions >= 2) {
                        fileName = option2;
                        System.out.println("File selected :" + fileName);
                    }
                    break;
                case "3":
                    if (numberOfOptions >= 3) {
                        fileName = option3;
                        System.out.println("File selected :" + fileName);
                    }
                    break;
                case "4":
                    if (numberOfOptions >= 4) {
                        fileName = option4;
                        System.out.println("File selected :" + fileName);
                    }
                    break;
                default:
                    System.out.println("Default donkey!");
                    break;
            }
        } else if (!skillCheck) {
            fileName = fileIfFails;
            System.out.println("failed check file to go: " + fileName);
        }


        // GivePoints
        if (!points.equals("0")) {
            System.out.println("entered in point");

            if (points.equals("+") && skillCheck) {
                GameEngine.givePoints(player, 1, "B");
                System.out.println("balance ++ ");

            }
            if (points.equals("-")) {
                System.out.println("entrou no -");
                GameEngine.givePoints(player, -1, "B");
                System.out.println("balance --");

            }
            if (points.matches("[1-9]")) {
                int pointsInt = Integer.parseInt(points);

                GameEngine.givePoints(player, pointsInt, skill);
                System.out.println("point given: " + pointsInt + " to " + skill);

            }
        }

        // Battles
        Enemy enemy = null;

        if (hasEnemy.equals("1")) {
            enemy = enemyFactory.createEnemy(EnemyTypes.MERCENARY, enemyHealth);
            System.out.println("enemy!");

            while (player.getHealth() > 0 && enemy.getHealth() > 0) {

                int playerHealth = player.getHealth();
                int playerForce = player.getForce();

                GameEngine.attack(player, enemy);

                if (playerHealth == 0) {
                    fileName = fileIfFails;
                    return;
                }
                if (enemyHealth == 0) {
                    return;
                }

                System.out.println("player force: " + player.getForce() + " enemy force: " + enemy.getForce() + " player health: " + player.getHealth() + " enemy health: " + enemy.getHealth());

            }
        }

        messageToServer = fileName;
        System.out.println("message to server: " + fileName);
        headerBody = FileManager.load(fileName).split("\\n", 2);
        messageToServer += headerBody[1];
        calcDone = true;

    }


    @Override
    public void fromServer(String fromClient) {

        if (fromClient.matches("resetFile")) {
            headerBody = FileManager.load("000").split("\\n", 2);
            messageToServer = headerBody[1];
            return;
        }


        System.out.println("From server confirmed");
        sendStory(fromClient, headerBody[0]);

    }

    @Override
    public String toServer() {

        if (calcDone) {
            calcDone = false;
            return messageToServer;
        }
        return headerBody[1];
    }

}
