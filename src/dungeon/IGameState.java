package dungeon;

import java.util.List;

import dungeon.character.Character;
import dungeon.directions.Direction;
import dungeon.location.ILocation;

public interface IGameState {

  Character getPlayer();

  boolean isGameOver();

  List<Direction> getAvailableDirectionsFromPlayerPosition();

  ILocation getPlayerStartLocation();

  ILocation getPlayerEndLocation();

  ILocation movePlayer(Direction direction);

  ILocation[][] getDungeon();

  String printPlayerTravelStatus();
}
