package dungeon;

import java.util.Locale;

public class Driver {

  public static void main(String[] args){

    int dungeonHeight = Integer.parseInt(args[0]);
    int dungeonWidth = Integer.parseInt(args[1]);
    int interConnectivity = Integer.parseInt(args[2]);

    String dungeonType = (args[3]).toLowerCase();
    if (dungeonType != "wrapping" || dungeonType != "nonwrapping"){
      throw new IllegalArgumentException("Dungeon type is incorrect.");
    }
    String[] inputSplit = args[4].split("[%]");
    int treasurePercentage = Integer.parseInt(inputSplit[0]);
    if (treasurePercentage < 0){
      throw new IllegalArgumentException("Treasure percentage value cannot be less than 0.");
    }


  }
}
