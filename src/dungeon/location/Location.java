package dungeon.location;

import java.util.HashMap;

import dungeon.directions.Direction;

public class Location implements ILocation {

  private String locationName;
  private HashMap<Direction, ILocation> neighbours;


  public Location(String loc) {
    this.locationName = loc;
    this.neighbours = new HashMap<>();
  }

  public void joinLocationToNorthDirection(ILocation loc) {
    neighbours.put(Direction.NORTH, loc);
  }

  public void joinLocationToSouthDirection(ILocation loc) {
    neighbours.put(Direction.SOUTH, loc);
  }

  public void joinLocationToEastDirection(ILocation loc) {
    neighbours.put(Direction.EAST, loc);
  }

  public void joinLocationToWestDirection(ILocation loc) {
    neighbours.put(Direction.WEST, loc);
  }


  @Override
  public int compareTo(ILocation o) {
    return this.toString().compareTo(o.toString());
  }

  @Override
  public String toString() {
    return String.valueOf(locationName);
  }
}
