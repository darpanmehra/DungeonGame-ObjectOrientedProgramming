import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import dungeon.GameState;
import dungeon.directions.Direction;
import dungeon.location.ILocation;
import dungeon.location.Location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the wrapping of the game.
 */
public class GameTestWrapping {

  private Random random;
  private GameState model;
  private int interconnectivity;

  @Before
  public void setUp() throws Exception {
    random = new Random();
    random.setSeed(50);
    interconnectivity = 0;
    model = new GameState(10, 10, interconnectivity,
            "wrapping", 20, random);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetup() {

    model = new GameState(-10, -10, -1,
            "wrapping", 20, null);
  }

  @Test
  public void getPlayer() {
    assertEquals("Player", model.getPlayer().getName());
  }

  @Test
  public void isGameOver() {
    ILocation end = model.getPlayerEndLocation();
    assertFalse(model.isGameOver());

    model.getPlayer().setCurrentLocation(end);
    assertTrue(model.isGameOver());
  }

  @Test
  public void getAvailableDirectionsFromPlayerPosition() {
    assertEquals("[SOUTH, EAST, WEST]",
            model.getAvailableDirectionsFromPlayerPosition().toString());
    assertEquals(3, model.getAvailableDirectionsFromPlayerPosition().size());
  }

  @Test
  public void getPlayerStartLocation() {
    assertEquals("(2, 4)", model.getPlayerStartLocation().toString());
  }

  @Test
  public void getPlayerEndLocation() {
    assertEquals("(4, 7)", model.getPlayerEndLocation().toString());
  }

  @Test
  public void movePlayer() {
    //Print dungeon to console
    ILocation[][] dungeon = model.getDungeon();
    StringBuilder dungeonBuilder = new StringBuilder();
    for (int i = 0; i < dungeon.length; i++) {
      for (int j = 0; j < dungeon[i].length; j++) {
        dungeonBuilder.append(dungeon[i][j].printLocationInfo());
        dungeonBuilder.append("\n");
      }
    }
    System.out.println(dungeonBuilder);
    assertEquals("(2, 4)", model.getPlayer().getCurrentLocation().toString());
    ILocation change = new Location(2, 0, random);

    //non-wrapping move
    model.movePlayer(Direction.EAST);
    assertEquals("(2, 5)", model.getPlayer().getCurrentLocation().toString());

    //wrapping move top - down
    model.movePlayer(Direction.NORTH); // goes to (1, 5)
    model.movePlayer(Direction.NORTH); // goes to (0, 5)
    model.movePlayer(Direction.EAST); // goes to (0, 6)
    model.movePlayer(Direction.NORTH); //Should wrap to (9, 6)
    assertEquals("(9, 6)", model.getPlayer().getCurrentLocation().toString());

    // Wrapping move right - left
    model.movePlayer(Direction.EAST); // goes to (9, 7)
    model.movePlayer(Direction.EAST); // goes to (9, 8)
    model.movePlayer(Direction.NORTH); //goes to (8, 8)
    model.movePlayer(Direction.EAST); // goes to (8, 9)
    model.movePlayer(Direction.EAST); // wrapping move - goes to (8, 0)
    assertEquals("(8, 0)", model.getPlayer().getCurrentLocation().toString());
  }

  @Test
  public void testInterconnectivity() {
    ILocation[][] dungeonCopy = model.getDungeon();
    int totalValidPaths = 0;
    for (int i = 0; i < dungeonCopy.length; i++) {
      for (int j = 0; j < dungeonCopy[i].length; j++) {
        totalValidPaths = totalValidPaths + dungeonCopy[i][j].getNeighbours().size();
      }
    }
    // Paths are bidirectional to totalValidPaths/ 2 gives the path count in the dungeon
    assertEquals(totalValidPaths / 2,
            interconnectivity + (dungeonCopy.length * dungeonCopy[0].length - 1));
  }

  @Test
  public void testInterconnectivityGreaterThanZero() {
    model = new GameState(10, 10, 8,
            "wrapping", 20, random);

    ILocation[][] dungeon = model.getDungeon();
    int totalValidPaths = 0;
    for (int i = 0; i < dungeon.length; i++) {
      for (int j = 0; j < dungeon[i].length; j++) {
        totalValidPaths = totalValidPaths + dungeon[i][j].getNeighbours().size();
      }
    }

    assertEquals(totalValidPaths / 2, 8 + dungeon.length * dungeon[0].length - 1);
  }

  @Test
  public void TestTreasureInRightLocation() {
    ILocation[][] dungeon = model.getDungeon();
    boolean correct = true;
    for (int i = 0; i < dungeon.length; i++) {
      for (int j = 0; j < dungeon[i].length; j++) {
        if (!dungeon[i][j].isCave() && dungeon[i][j].getTreasure() != null) {
          correct = false;
        }
      }
    }
    assertTrue(correct);
  }

  @Test
  public void TestTreasurePercentage() {
    ILocation[][] dungeon = model.getDungeon();
    double caves = 0.0;
    double cavesThatHaveTreasure = 0.0;
    for (int i = 0; i < dungeon.length; i++) {
      for (int j = 0; j < dungeon[i].length; j++) {
        if (dungeon[i][j].isCave()) {
          caves = caves + 1;
          if (dungeon[i][j].getTreasure() != null) {
            cavesThatHaveTreasure = cavesThatHaveTreasure + 1;
          }
        }
      }
    }
    double ans = cavesThatHaveTreasure / caves;
    assertEquals(Math.floor(ans * 100.00), 20.00, 0.001);
  }

  @Test
  public void printPlayerTravelStatus() {
    assertEquals("Player has traveled to the following locations: [(2, 4) ].\n" +
            "Treasures: {}", model.printPlayerTravelStatus());
  }

  @Test
  public void playerPickingTreasure() {
    model.movePlayer(Direction.EAST);
    model.movePlayer(Direction.EAST);
    model.movePlayer(Direction.SOUTH);
    model.movePlayer(Direction.SOUTH);
    model.movePlayer(Direction.WEST);
    model.movePlayer(Direction.WEST);
    model.movePlayer(Direction.SOUTH);
    model.movePlayer(Direction.EAST);
    model.movePlayer(Direction.EAST);
    assertEquals("Player has traveled to the following locations: [(2, 4) (2, 5) (2, 6) " +
            "(3, 6) (4, 6) (4, 5) (4, 4) (5, 4) (5, 5) (5, 6) ].\n" +
            "Treasures: {DIAMONDS=14, RUBIES=9, SAPPHIRES=15}", model.printPlayerTravelStatus());
  }

  @Test
  public void treasureNullAfterPick() {
    model.movePlayer(Direction.EAST);
    model.movePlayer(Direction.EAST);
    model.movePlayer(Direction.SOUTH); // goes to (3, 6) and collects treasure
    assertEquals("Player has traveled to the following locations: [(2, 4) (2, 5) " +
            "(2, 6) (3, 6) ].\n" +
            "Treasures: {DIAMONDS=7, RUBIES=2, SAPPHIRES=9}", model.printPlayerTravelStatus());

    model.movePlayer(Direction.WEST); // goes to (3, 5)
    model.movePlayer(Direction.EAST); // goes to (3, 6) - comes back to same location where treasure

    assertEquals("Player has traveled to the following locations: [(2, 4) (2, 5) " +
            "(2, 6) (3, 6) (3, 5) (3, 6) ].\n" +
            "Treasures: {DIAMONDS=7, RUBIES=2, SAPPHIRES=9}", model.printPlayerTravelStatus());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMove() {
    model.movePlayer(Direction.NORTH); // Can't move to NORTH as it's a wall
  }

  @Test
  public void dungeonCreationCorrectness() {
    ILocation[][] dungeonCopy = model.getDungeon();
    int totalValidPaths = 0;
    for (int i = 0; i < dungeonCopy.length; i++) {
      for (int j = 0; j < dungeonCopy[i].length; j++) {
        totalValidPaths = totalValidPaths + dungeonCopy[i][j].getNeighbours().size();
      }
    }
    assertEquals((totalValidPaths / 2), 0 + dungeonCopy.length * dungeonCopy[0].length - 1);
  }

  @Test
  public void dungeonCreationWithHighInterConnectivity() {
    model = new GameState(10, 10, 4,
            "wrapping", 20, random);

    ILocation[][] dungeonCopy = model.getDungeon();
    int totalValidPaths = 0;
    for (int i = 0; i < dungeonCopy.length; i++) {
      for (int j = 0; j < dungeonCopy[i].length; j++) {
        totalValidPaths = totalValidPaths + dungeonCopy[i][j].getNeighbours().size();
      }
    }
    assertEquals((totalValidPaths / 2), 4 + dungeonCopy.length * dungeonCopy[0].length - 1);
  }

  @Test
  public void dungeonCreationWithVeryHighInterconnectivity() {
    model = new GameState(6, 6, 1000000,
            "wrapping", 20, random);
    ILocation[][] dungeonCopy = model.getDungeon();
    int totalValidPaths = 0;
    for (int i = 0; i < dungeonCopy.length; i++) {
      for (int j = 0; j < dungeonCopy[i].length; j++) {
        totalValidPaths = totalValidPaths + dungeonCopy[i][j].getNeighbours().size();
      }
    }

    //All paths open from each location, so for 36 locations we have 36 * 4 = 144 paths
    assertEquals((totalValidPaths), (4 * dungeonCopy.length * dungeonCopy[0].length));
  }
}