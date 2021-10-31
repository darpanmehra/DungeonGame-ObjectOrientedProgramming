package dungeon;

import java.util.Random;

import dungeon.character.Character;
import dungeon.character.Player;
import dungeon.location.ILocation;

public class GameState implements IGameState {

  private Grid dungeon;
  private Character player;

  public GameState(int dungeonHeight, int dungeonWidth, int interConnectivity, String dungeonType, int treasurePercentage, Random random) {
    if (dungeonHeight < 10 || dungeonHeight > 100) {
      throw new IllegalArgumentException("Dungeon width must be between 10 and 100");
    }
    if (dungeonWidth < 10 || dungeonWidth > 100) {
      throw new IllegalArgumentException("Dungeon width must be between 10 and 100");
    }
    if (interConnectivity < 0) {
      throw new IllegalArgumentException("Interconnectivity must be between 0 and dungeon size");
    }
    if (!(dungeonType.equalsIgnoreCase("wrapping") || dungeonType.equalsIgnoreCase("nonwrapping"))) {
      throw new IllegalArgumentException("Dungeon type must be wrapping or nonwrapping");
    }
    if (treasurePercentage < 0 || treasurePercentage > 100) {
      throw new IllegalArgumentException("Treasure percentage must be between 0 and 100");
    }
    if (random == null) {
      throw new IllegalArgumentException("Random must be specified");
    }

    dungeon = new Grid(dungeonHeight, dungeonWidth, interConnectivity, dungeonType, treasurePercentage, random);

    //Create Player and assign start location
    player = new Player("Player1");
    player.setCurrentLocation(dungeon.getPlayerStartLocation());
  }

  @Override
  public boolean isGameOver(){
    if (player.getCurrentLocation().equals(dungeon.getPlayerEndLocation())) {
      return true;
    }
    return false;
  }

  @Override
  public Character getPlayer() {
    return player;
  }

  @Override
  public ILocation getPlayerStartLocation() {
    return dungeon.getPlayerStartLocation();
  }

  @Override
  public ILocation getPlayerEndLocation() {
    return dungeon.getPlayerEndLocation();
  }

}