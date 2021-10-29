package dungeon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import dungeon.location.ILocation;
import dungeon.location.Location;

public class Grid {
  private ILocation[][] maze;
  private Set<SortedSet<ILocation>> potentialPaths;
  private List<List<ILocation>> kSet;
  private Random random;
  private List<SortedSet> noHome;
  private List<ILocation> caveList;
  private final double treasurePercentage;

  public Grid(int numRow, int numCol, int interConnectivity, String dungeonType, double treasurePercentage, Random random) {
    this.random = random;
    this.treasurePercentage = treasurePercentage;

    noHome = new ArrayList<>();
    kSet = new ArrayList<>();
    maze = new ILocation[numRow][numCol];
    potentialPaths = new HashSet<>();
    initializeLocation();
    krus();
    updatePotentialPaths();
//    updatePotentialPathsWrapping();
    algorithm();
    addInterconnectivity(0);


    //Get Cave Locations
    caveList = new ArrayList<>();
    caveList = getCaves();
    assignTreasures();
  }


  private void initializeLocation() {
    char loc = 'a';
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        String name = "";
        name += loc;
        maze[i][j] = new Location(name, i, j);
        loc++;
      }
    }
  }

  private void updatePotentialPaths() {
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
//        if (((i - 1) >= 0)) {
//          SortedSet<ILocation> path = new TreeSet<>();
//          path.add(maze[i][j]);
//          path.add(maze[i - 1][j]);
//          potentialPaths.add(path);
//        }
        if (((i + 1) < maze.length)) {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[i + 1][j]);
          potentialPaths.add(path);
        }
        if (((j + 1) < maze[i].length)) {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[i][j + 1]);
          potentialPaths.add(path);
        }
//        if (((j - 1) >= 0)) {
//          SortedSet<ILocation> path = new TreeSet<>();
//          path.add(maze[i][j]);
//          path.add(maze[i][j - 1]);
//          potentialPaths.add(path);
//        }
      }
    }
  }


  private void updatePotentialPathsWrapping() {
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        if (((i - 1) >= 0)) {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[i - 1][j]);
          potentialPaths.add(path);
        } else {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[maze.length - 1][j]);
          potentialPaths.add(path);
        }
        if (((i + 1) < maze.length)) {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[i + 1][j]);
          potentialPaths.add(path);
        } else {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[0][j]);
          potentialPaths.add(path);
        }
        if (((j + 1) < maze[i].length)) {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[i][j + 1]);
          potentialPaths.add(path);
        } else {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[i][0]);
          potentialPaths.add(path);

        }
        if (((j - 1) >= 0)) {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[i][j - 1]);
          potentialPaths.add(path);
        } else {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[i][maze[i].length - 1]);
          potentialPaths.add(path);
        }
      }
    }
  }


  //put every location in a set;
  private void krus() {
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        List<ILocation> location = new ArrayList<>();
        location.add(maze[i][j]);
        kSet.add(location);

      }
    }
  }

  private void algorithm() {
    List<SortedSet<ILocation>> list = new ArrayList<>();
    list.addAll(potentialPaths);

    while (list.size() > 0) {

      ILocation firstLocation = null;
      ILocation secondLocation = null;
      int firstLocationIndex = -1;
      int secondLocationIndex = -1;
      int count = 0;
      var pathIndex = random.nextInt(list.size());
      var sortedLocationSet = list.get(pathIndex);
      for (ILocation value : sortedLocationSet) {
        if (count == 0) {
          firstLocation = value; //n
          count++;
        }
        secondLocation = value; //o
      }
      System.out.println("firstLocation: " + firstLocation.getName() + " secondLocation: " + secondLocation.getName());
      list.remove(pathIndex);
      for (int i = 0; i < kSet.size(); i++) {
        if (kSet.get(i).contains(firstLocation)) {
          firstLocationIndex = i; // index of list n is in
        }
        if (kSet.get(i).contains(secondLocation)) {
          secondLocationIndex = i; //index of list o is in
        }

      }

      if (firstLocationIndex != secondLocationIndex) {
        joinLocation(firstLocation, secondLocation);
        kSet.get(firstLocationIndex).addAll(kSet.get(secondLocationIndex));
        kSet.remove(secondLocationIndex);
      } else {
        System.out.println("Added to noHome");
        noHome.add(sortedLocationSet);
      }
//      System.out.println("Path: "+ sortedLocationSet);
//      System.out.println(kSet);
//      System.out.println("No home " + noHome);
//      System.out.println("No home length" + noHome.size());
    }
  }

  public void joinLocation(ILocation firstLocation, ILocation secondLocation) {

    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        if (firstLocation.equals(maze[i][j])) {
          if (((i + 1) <= maze.length - 1)) {
            if (maze[i + 1][j].equals(secondLocation)) {
              firstLocation.joinLocationToSouthDirection(secondLocation);
              secondLocation.joinLocationToNorthDirection(firstLocation);
            }
          } else if ((i + 1) > maze.length - 1) {
            if (maze[0][j].equals(secondLocation)) {
              firstLocation.joinLocationToSouthDirection(secondLocation);
              secondLocation.joinLocationToNorthDirection(firstLocation);
            }
          }
          if (((i - 1) >= 0)) {
            if (maze[i - 1][j].equals(secondLocation)) {
              firstLocation.joinLocationToNorthDirection(secondLocation);
              secondLocation.joinLocationToSouthDirection(firstLocation);
            }
          } else if ((i - 1) < 0) {
            if (maze[maze.length - 1][j].equals(secondLocation)) {
              firstLocation.joinLocationToNorthDirection(secondLocation);
              secondLocation.joinLocationToSouthDirection(firstLocation);
            }
          }
          if (((j + 1) <= maze[i].length - 1)) {
            if (maze[i][j + 1].equals(secondLocation)) {
              firstLocation.joinLocationToEastDirection(secondLocation);
              secondLocation.joinLocationToWestDirection(firstLocation);
            }
          } else if ((j + 1) > maze[i].length - 1) {
            if (maze[i][0].equals(secondLocation)) {
              firstLocation.joinLocationToEastDirection(secondLocation);
              secondLocation.joinLocationToWestDirection(firstLocation);
            }
          }
          if (((j - 1) >= 0)) {
            if (maze[i][j - 1].equals(secondLocation)) {
              firstLocation.joinLocationToWestDirection(secondLocation);
              secondLocation.joinLocationToEastDirection(firstLocation);
            }

          } else if ((j - 1) < 0) {
            if (maze[i][maze[i].length - 1].equals(secondLocation)) {
              firstLocation.joinLocationToWestDirection(secondLocation);
              secondLocation.joinLocationToEastDirection(firstLocation);
            }
          }
        }
      }
    }
  }

  public void addInterconnectivity(int num) {
    testConnection();

    System.out.println("\n \n \n \n \n \n");
    ILocation firstLocation = null;
    ILocation secondLocation = null;
    SortedSet<ILocation> sortedLocationSet = null;
    for (int i = 0; i < num; i++) {
      sortedLocationSet = noHome.get(i);
      int count = 0;
      for (ILocation value : sortedLocationSet) {
        if (count == 0) {
          firstLocation = value;
          count++;
        }
        secondLocation = value;
      }
      joinLocation(firstLocation, secondLocation);
    }
  }


  public void testConnection() {
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        System.out.println(maze[i][j]);
      }
    }
  }

  public void printGrid() {
    String finalString = "";
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length
              ; j++) {
        System.out.printf("%s", maze[i][j].print());
      }
      System.out.println();
      for (int j = 0; j < maze[i].length; j++) {
        System.out.printf("%s", maze[i][j].printPipe());
      }
      System.out.println();

    }
  }


  //Getters
  public List<List<ILocation>> getKrus() {
    return kSet;
  }

  public Set<SortedSet<ILocation>> getPotentialPath() {
    return potentialPaths;
  }

  public List<ILocation> getCaves() {
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        ILocation loc = maze[i][j];
        if (loc.isCave()) {
          caveList.add(loc);
        }
      }
    }
    return caveList;
  }

  public void assignTreasures() {
    int totalCaves = caveList.size();
    double numberOfTreasuresCaves = Math.ceil(totalCaves * (treasurePercentage / 100));
    for (double i= 0.0; i < numberOfTreasuresCaves; i++) {
      int randomIndex = random.nextInt(caveList.size());
      caveList.get(randomIndex).setTreasure();
      caveList.remove(randomIndex);
    }
  }

}
