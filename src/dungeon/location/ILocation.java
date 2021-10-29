package dungeon.location;

import dungeon.directions.Direction;

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
}
