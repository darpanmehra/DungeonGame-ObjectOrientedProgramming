import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import dungeon.GameState;
import dungeon.character.Character;
import dungeon.location.ILocation;

import static org.junit.Assert.assertEquals;

public class GameStateTest {

  private Random random;
  private GameState model;

  @Before
  public void setUp() throws Exception {
    random = new Random();
    random.setSeed(50);
    model = new GameState(10, 10, 0, "nonwrapping", 20, random);
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

  }

  @Test
  public void getDungeon() {
  }

  @Test
  public void printPlayerTravelStatus() {
  }
}