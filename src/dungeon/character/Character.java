package dungeon.character;

import dungeon.location.ILocation;
import dungeon.treasure.ITreasure;

public interface Character {

  String getName();

  void addTreasure(ITreasure treasure);

  String getTreasures();

  ILocation getCurrentLocation();

  void setCurrentLocation(ILocation location);

  String printTravelStatus();

}
