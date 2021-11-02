import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import dungeon.GameState;
import dungeon.character.Character;
import dungeon.directions.Direction;
import dungeon.location.ILocation;

import static org.junit.Assert.assertEquals;

/**
 * A test class for the GameState interface.
 */
public class GameTest {

  private Random random;
  private GameState model;

  @Before
  public void setUp() throws Exception {
    random = new Random();
    random.setSeed(50);
    model = new GameState(10, 10, 0,
            "nonwrapping", 20, random);
  }

  @Test
  public void getPlayer() {
    assertEquals("Player", model.getPlayer().getName());
  }

  @Test
  public void isGameOver() {
    ILocation start = model.getPlayerStartLocation();
    ILocation end = model.getPlayerEndLocation();
    assertEquals(false, model.isGameOver());

    Character player = model.getPlayer();
    player.setCurrentLocation(end);
    assertEquals(true, model.isGameOver());
  }

  @Test
  public void getAvailableDirectionsFromPlayerPosition() {
    assertEquals("[NORTH, WEST]", model.getAvailableDirectionsFromPlayerPosition().toString());
    assertEquals(2, model.getAvailableDirectionsFromPlayerPosition().size());
  }

  @Test
  public void getPlayerStartLocation() {
    assertEquals("(1, 1)", model.getPlayerStartLocation().toString());
  }

  @Test
  public void getPlayerEndLocation() {
    assertEquals("(6, 5)", model.getPlayerEndLocation().toString());
  }

  @Test
  public void movePlayer() {
    Character player = model.getPlayer();
    assertEquals("(6, 9)", player.getCurrentLocation().toString());
    assertEquals("[NORTH]", model.getAvailableDirectionsFromPlayerPosition().toString());
    model.movePlayer(Direction.NORTH);


    assertEquals("(5, 9)", model.getPlayer().getCurrentLocation().toString());

  }

  @Test
  public void printPlayerTravelStatus() {
    assertEquals("(6, 9)", model.getPlayer().getCurrentLocation().toString());
    assertEquals("[NORTH]", model.getAvailableDirectionsFromPlayerPosition().toString());
    model.movePlayer(Direction.NORTH);
    assertEquals("Player has traveled to the following locations: [(6, 9) (5, 9) ].\n" +
            "Treasures: {}", model.printPlayerTravelStatus());
  }
}