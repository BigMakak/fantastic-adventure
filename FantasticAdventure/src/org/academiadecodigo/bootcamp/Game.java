package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.enemy.EnemyFactory;

import java.io.File;
import java.io.StringReader;

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
    private String fileName = "1";
    private String option;


    public void init() {
        enemyFactory = new EnemyFactory();
        gameEngine = new GameEngine();
        player = new Player("Jorge", 10, 10, 10, 10, 10, 10, 10);
    }


    public String sendStory() {

        headerBody = FileManager.load(fileName).split("\\n", 2);
        System.out.println(headerBody[0]);
        System.out.println(headerBody[1]);

        String header = headerBody[0];

        String currentFile = header.substring(0, 3);
        int numberOfOptions = Integer.parseInt(header.substring(3, 4));
        String option1 = header.substring(4, 7);
        String option2 = header.substring(7, 10);
        String option3 = header.substring(10, 13);
        String option4 = header.substring(13, 16);

        System.out.println(currentFile);

        switch (option) {
            case "1":
                fileName = option1;
                break;
            case "2":
                fileName = option2;
                break;
            case "3":
                fileName = option3;
                break;
        }

       /* if (option1.equals(option)) {
            fileName = option1;
        }

        if (option2.equals(option)) {
            fileName = option2;
        }

        if (option3.equals(option)) {
            fileName = option3;
        }

        if (numberOfOptions > 3) {
            if (option4.equals(option)) {
                fileName = option4;
            }
        }*/
        return fileName;
    }


    public void setServer(Server server) {
        this.server = server;
    }


    @Override
    public void fromServer(String[] message) {
        clientInfo = message;

        option = clientInfo[2];
        fileName = clientInfo[0];
        sendStory();

    }

    @Override
    public String toServer() {
        return FileManager.load(fileName);
    }
}
