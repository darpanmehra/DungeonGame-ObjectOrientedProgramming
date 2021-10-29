package dungeon.location;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import dungeon.directions.Direction;


public class Location implements ILocation {

  private final String locationName;
  private final int[] coordinates = new int[2];
  private HashMap<Direction, ILocation> neighbours;

  public Location(String loc, int rowCoordinate, int colCoordinate) {
    this.locationName = loc;
    this.coordinates[0] = rowCoordinate;
    this.coordinates[1] = colCoordinate;
    this.neighbours = new HashMap<>();
  }

  @Override
  public String getName() {
    return this.locationName;
  }

  @Override
  public void joinLocationToNorthDirection(ILocation loc) {
    neighbours.put(Direction.NORTH, loc);
  }

  @Override
  public void joinLocationToSouthDirection(ILocation loc) {
    neighbours.put(Direction.SOUTH, loc);
  }

  @Override
  public void joinLocationToEastDirection(ILocation loc) {
    neighbours.put(Direction.EAST, loc);
  }

  @Override
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
