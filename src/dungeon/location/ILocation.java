package dungeon.location;

import java.util.Map;

import dungeon.directions.Direction;
import dungeon.treasure.ITreasure;
import dungeon.treasure.Treasure;

public interface ILocation extends Comparable<ILocation> {

  String getName();

  int getRowCoordinate();

  int getColCoordinate();

  void joinLocationToNorthDirection(ILocation loc);

  void joinLocationToSouthDirection(ILocation loc);

  void joinLocationToEastDirection(ILocation loc);

  void joinLocationToWestDirection(ILocation loc);

  boolean isCave();

  void setTreasureEmpty();

  void setTreasure();

  ITreasure getOriginalTreasure();

  ITreasure getTreasure();

  Map<Direction, ILocation> getNeighbours();

  String printLocationInfo();

}
