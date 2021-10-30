package dungeon;

import java.util.Random;

public class Driver {

  public static void main(String[] args){

    int dungeonHeight = Integer.parseInt(args[0]);
    int dungeonWidth = Integer.parseInt(args[1]);
    int interConnectivity = Integer.parseInt(args[2]);
    String dungeonType = (args[3]).toLowerCase();
    int treasurePercentage = Integer.parseInt(args[4]);

    //Mock Random method
    Random rand = new Random();
    //rand.setSeed(20);

    // Create a new dungeon- Initialize the model
    GameState model = new GameState(dungeonHeight, dungeonWidth, interConnectivity, dungeonType, treasurePercentage, rand);


  }
}
