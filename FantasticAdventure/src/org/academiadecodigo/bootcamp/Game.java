package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.enemy.EnemyFactory;

/**
 * Created by codecadet on 12/07/2017.
 */
public class Game {


    private Player player;
    private EnemyFactory enemyFactory;
    private GameEngine gameEngine;

    public void init() {
        enemyFactory = new EnemyFactory();
        gameEngine = new GameEngine();
        player = new Player("Jorge", 10, 10, 10, 10, 10, 10, 10);
    }


    public String sendStory(Story storyChapter) {
        switch (storyChapter) {
            case CHAPTER1:
                return FileManager.load("1");
            case CHAPTER2:
                return FileManager.load("2");
            case CHAPTER3:
                return FileManager.load("3");
            case CHAPTER4:
                return FileManager.load("4");
        }
        return null;
    }
}
