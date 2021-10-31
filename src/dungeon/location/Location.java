package dungeon.location;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import dungeon.directions.Direction;
import dungeon.treasure.Treasure;


public class Location implements ILocation {

  private final String locationName;
  private final int[] coordinates = new int[2];
  private HashMap<Direction, ILocation> neighbours;
  private Treasure treasure;

  public Location(String loc, int rowCoordinate, int colCoordinate) {
    this.locationName = loc;
    this.coordinates[0] = rowCoordinate;
    this.coordinates[1] = colCoordinate;
    this.neighbours = new HashMap<>();
    this.treasure = null;
  }

  public Location(ILocation loc) {
    this.locationName = loc.getName();
    this.coordinates[0] = loc.getRowCoordinate();
    this.coordinates[1] = loc.getColCoordinate();
    this.neighbours = new HashMap<>(loc.getNeighbours());
    this.treasure = loc.getTreasure();
  }

  @Override
  public String getName() {
    return this.coordinates[0] + ", " + this.coordinates[1];
  }

  @Override
  public int getRowCoordinate() {
    return coordinates[0];
  }

  @Override
  public int getColCoordinate() {
    return coordinates[1];
  }

  @Override
  public Map<Direction, ILocation> getNeighbours() {
    return neighbours;
  }

  @Override
  public boolean isCave() {
    if (neighbours.size() == 2) {
      return false;
    }
    return true;
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
  public void setTreasure() {
    if (isCave()) {
      treasure = new Treasure(new Random());
    }
  }

  @Override
  public Treasure getTreasure() {
    return treasure;
  }

  @Override
  public String getLocationCoordinateString(){
    return String.format("(%d, %d)", this.coordinates[0], this.coordinates[1]);
  }

  @Override
  public int compareTo(ILocation o) {
    return this.toString().compareTo(o.toString());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj.getClass() != this.getClass()) {
      return false;
    }
    final Location other = (Location) obj;
    if ((this.coordinates[0] == other.coordinates[0]) && (this.coordinates[1] == other.coordinates[1])) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 53 * hash + Arrays.hashCode(this.coordinates);
    return hash;
  }

  @Override
  public String toString() {
    return String.format("(%d, %d)", this.coordinates[0], this.coordinates[1]);

//    return String.format("I am location (%s, %s) \n this is my north %s \n this is my south %s " +
//                    "\n this is my east %s \n this is my west %s \n",
//            this.coordinates[0], this.coordinates[1],
//            this.neighbours.get(Direction.NORTH) != null ? this.neighbours.get(Direction.NORTH).getName() : "NULL",
//            this.neighbours.get(Direction.SOUTH) != null ? this.neighbours.get(Direction.SOUTH).getName() : "NULL",
//            this.neighbours.get(Direction.EAST) != null ? this.neighbours.get(Direction.EAST).getName() : "NULL",
//            this.neighbours.get(Direction.WEST) != null ? this.neighbours.get(Direction.WEST).getName() : "NULL");
//    return String.format("%s",
//            this.locationName);
  }


  //Extras
  public String printNameAndSize() {
    return String.format("%s %s", this.locationName, this.neighbours.size());
  }

  @Override
  public String print() {

    return String.format("%s",
            this.locationName);
//            this.neighbours.get(Direction.EAST) != null ? "-" : " ");
//            this.neighbours.get(Direction.SOUTH) != null ? this.locationName + "\n|" : "");
  }

  public String printPipe() {
    return String.format("%s",
            this.neighbours.get(Direction.SOUTH) != null ? "|" : " ");
  }

}
