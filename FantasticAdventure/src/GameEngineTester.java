/**
 * Created by codecadet on 11/07/17.
 */
public class GameEngineTester {


    public static void main(String[] args) {

        GameEngine gameEngine = new GameEngine();

        for (int i = 0; i < 20; i++) {

            System.out.println(RandomGenerator.randomGenerator(1,10));
        }
    }
}
