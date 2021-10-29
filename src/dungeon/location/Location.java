package dungeon.location;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import dungeon.directions.Direction;
import dungeon.treasure.Treasure;
import random.RandomMock;


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

  @Override
  public String getName() {
    return this.locationName;
  }

  @Override
  public boolean isCave() {
    if (neighbours.size() == 2){
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
  public int compareTo(ILocation o) {
    return this.toString().compareTo(o.toString());
  }

  @Override
  public String toString() {
    return String.format("%s %s", this.locationName, this.neighbours.size());
//
//    return String.format("I am location %s \n this is my north %s \n this is my south %s " +
//                    "\n this is my east %s \n this is my west %s \n",
//            this.locationName,
//            this.neighbours.get(Direction.NORTH) != null ? this.neighbours.get(Direction.NORTH).getName() : "NULL",
//            this.neighbours.get(Direction.SOUTH) != null ? this.neighbours.get(Direction.SOUTH).getName() : "NULL",
//            this.neighbours.get(Direction.EAST) != null ? this.neighbours.get(Direction.EAST).getName() : "NULL",
//            this.neighbours.get(Direction.WEST) != null ? this.neighbours.get(Direction.WEST).getName() : "NULL");
//    return String.format("%s",
//            this.locationName);
  }

public String printNameAndSize(){
    return String.format("%s %s", this.locationName, this.neighbours.size());
}

public void setTreasure() {
    if (isCave()) {
      treasure = new Treasure(new Random());
    }
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
}
