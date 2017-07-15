package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.enemy.EnemyFactory;

/**
 * Created by codecadet on 12/07/2017.
 */
public class Game implements MessageHandler {


    private Player player;
    private EnemyFactory enemyFactory;
    private GameEngine gameEngine;
    private Server server;
    private String[] headerBody;
    private String[] clientInfo;
    private String fileName = "001";
    private String option;


    public void init() {
        enemyFactory = new EnemyFactory();
        gameEngine = new GameEngine();
        player = new Player("Jorge", 10, 10, 10, 10, 10, 10, 10);
    }

    private void parseHeader() {
       // headerBody = FileManager.load(fileName).split("\\n", 2);



        //toServer(headerBody[1]);
    }

    public void sendStory(String clientChoice, String header) {
        //parseHeader();
        //String header = headerBody[0];


        String currentFile = header.substring(0, 3);
        int numberOfOptions = Integer.parseInt(header.substring(3, 4));
        String option1 = header.substring(4, 7);
        String option2 = header.substring(7, 10);
        String option3 = header.substring(10, 13);
        String option4 = header.substring(13);
        System.out.println(option1 + " " + option2 + " " + option3);
        System.out.println(currentFile);

        switch (clientChoice) {

            case "1":
                fileName = option1;
                System.out.println(fileName);
                break;
            case "2":
                fileName = option2;
                System.out.println(fileName);
                break;
            case "3":
                fileName = option3;
                System.out.println(fileName);
                break;
            case "4":
                fileName = option4;
                System.out.println(fileName);
                break;
            default:
                System.out.println("somos burros!");
                break;
        }


        toServer();

        //return fileName;
    }


    public void setServer(Server server) {
        this.server = server;
    }


    @Override
    public void fromServer(String fromClient) {

        System.out.println(fromClient);

        int parse = Integer.parseInt(fromClient);
        if(!(parse > 0) && !(parse < 5)) {
            return;
        }

        headerBody = FileManager.load(fileName).split("\\n", 2);

        System.out.println("poi");
        sendStory(fromClient, headerBody[0]);




/*
        option = server.sendClientOption();
        System.out.println(option);
        sendStory();
*/
    }

    @Override
    public String toServer() {
        return fileName + headerBody[1];
    }
}
