package dungeon.character;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dungeon.location.ILocation;
import dungeon.location.Location;
import dungeon.treasure.ITreasure;
import dungeon.treasure.Treasure;
import dungeon.treasure.TreasureType;

public class Player implements Character {

  private String name;
  private Map<TreasureType, Integer> treasures;
  private ILocation currentLocation;
  private List<ILocation> locationVisited;

  public Player(String name){
      this.name = name;
      this.treasures = new TreeMap<>();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void addTreasure(ITreasure treasure) {
    Map<TreasureType, Integer> treasures = treasure.getTreasure();
    for (TreasureType treasureType : treasures.keySet()) {
      if (this.treasures.containsKey(treasureType)) {
        this.treasures.put(treasureType, this.treasures.get(treasureType) + treasures.get(treasureType));
      } else {
        this.treasures.put(treasureType, treasures.get(treasureType));
      }
    }
  }

  @Override
  public String getTreasures() {
    return this.treasures.toString();
  }

  @Override
  public ILocation getCurrentLocation() {
    return this.currentLocation;
  }

  @Override
  public void setCurrentLocation(ILocation location) {
    locationVisited.add(location);
    this.currentLocation = location;
  }

  @Override
  public String toString() {
    return this.name + " is at " + this.currentLocation.getLocationCoordinateString();
  }
}
