package dungeon.location;

import java.util.Map;

import dungeon.directions.Direction;
import dungeon.treasure.Treasure;

public interface ILocation extends Comparable<ILocation> {

  String getName();

  void joinLocationToNorthDirection(ILocation loc);

  void joinLocationToSouthDirection(ILocation loc);

  void joinLocationToEastDirection(ILocation loc);

  void joinLocationToWestDirection(ILocation loc);

  boolean isCave();

  void setTreasure();

  String printPipe();

  String print();

  int getRowCoordinate();

  int getColCoordinate();

  Map<Direction, ILocation> getNeighbours();

  Treasure getTreasure();
}
