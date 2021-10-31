package dungeon.character;

import java.util.Map;

import dungeon.location.ILocation;
import dungeon.treasure.ITreasure;
import dungeon.treasure.Treasure;
import dungeon.treasure.TreasureType;

public interface Character {

  String getName();

  void setCurrentLocation(ILocation location);

  ILocation getCurrentLocation();

  void addTreasure(ITreasure treasure);

  String getTreasures();

}
