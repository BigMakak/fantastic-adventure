package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.enemy.EnemyFactory;

/**
 * Created by codecadet on 12/07/2017.
 */
public class Game implements MessageHandler {


    private Player player;
    private EnemyFactory enemyFactory;
    private GameEngine gameEngine;
    private String[] headerBody;
    private String fileName = "";
    private String messageToServer;
    private boolean calcDone;


    public void init() {
        enemyFactory = new EnemyFactory();
        gameEngine = new GameEngine();
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
        String option4 = header.substring(13);

        //TODO gameEngine.skillcheck
        System.out.println(option1 + " " + option2 + " " + option3);
        System.out.println("Client choice is " + clientChoice);

        switch (clientChoice) {

            case "1":
                fileName = option1;
                System.out.println("File selected :" + fileName);
                break;
            case "2":
                if(numberOfOptions >= 2) {
                    fileName = option2;
                    System.out.println("File selected :" + fileName);
                }
                break;
            case "3":
                if(numberOfOptions >= 3) {
                    fileName = option3;
                    System.out.println("File selected :" + fileName);
                }
                break;
            case "4":
                if(numberOfOptions >= 3) {
                    fileName = option4;
                    System.out.println("File selected :" + fileName);
                }
                break;
            default:
                System.out.println("Default donkey!");
                break;
        }

        messageToServer = fileName;

        headerBody = FileManager.load(fileName).split("\\n", 2);

        messageToServer += headerBody[1];

        calcDone = true;
    }


    @Override
    public void fromServer(String fromClient) {


        if (fromClient != null && !fromClient.equals("")) {

            int parse = Integer.parseInt(fromClient);
            if (!(parse > 0) && !(parse < 5)) {
                return;
            }
        } else {
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
