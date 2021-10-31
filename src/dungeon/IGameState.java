package dungeon;

import dungeon.character.Character;
import dungeon.location.ILocation;

public interface IGameState {

  Character getPlayer();

  boolean isGameOver();

  ILocation getPlayerStartLocation();

  ILocation getPlayerEndLocation();


}
