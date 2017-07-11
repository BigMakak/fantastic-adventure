package org.academiadecodigo.bootcamp;

/**
 * Created by codecadet on 11/07/2017.
 */
public class RandomGenerator {

    public static int randomGenerator(int min,int max) {
       return min + (int)(Math.random() * max);
    }
}
