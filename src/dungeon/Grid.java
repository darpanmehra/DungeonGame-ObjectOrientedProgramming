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

  public Grid(int numRow, int numCol, Random random) {
    this.random = random;
    noHome = new ArrayList<>();
    kSet = new ArrayList<>();
    maze = new ILocation[numRow][numCol];
    potentialPaths = new HashSet<>();
    initializeLocation();
    krus();
    updatePotentialPaths();
    algorithm();
  }


  private void initializeLocation() {
    char loc = 'a';
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        String name = "";
        name += loc;
        maze[i][j] = new Location(name);
        loc++;
      }
    }
  }

  private void updatePotentialPaths() {
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        if (((i - 1) >= 0)) {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[i - 1][j]);
          potentialPaths.add(path);
        }
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
        if (((j - 1) >= 0)) {
          SortedSet<ILocation> path = new TreeSet<>();
          path.add(maze[i][j]);
          path.add(maze[i][j - 1]);
          potentialPaths.add(path);
        }
      }
    }
  }


  //put every location in a set;
  private void krus() {
    for (int i = 0; i < maze.length ; i++) {
      for (int j = 0; j < maze[i].length ; j++) {
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
        kSet.get(firstLocationIndex).addAll(kSet.get(secondLocationIndex));
        kSet.remove(secondLocationIndex);
      } else {
        noHome.add(sortedLocationSet);
      }
      System.out.println("Path: "+ sortedLocationSet);
      System.out.println(kSet);
      System.out.println("No home " + noHome);
      System.out.println("No home length" + noHome.size());
    }
  }


  public List<List<ILocation>> getKrus() {
    return kSet;
  }


   public Set<SortedSet<ILocation>> getPotentialPath() {
      return potentialPaths;
   }


}
