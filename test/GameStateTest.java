import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import dungeon.GameState;
import dungeon.IGameState;

import static org.junit.Assert.*;

public class GameStateTest {

  private IGameState model;

  @Before
  public void setUp() throws Exception {
    Random rand = new Random();
    //rand.setSeed(10);
    model = new GameState(10, 10, 0,
            "nonwrapping", 0, rand);
  }

  @Test
  public void testPlayerStartLocation(){
    System.out.print("Testing player start location..." + model.getPlayerStartLocation());
  }

  @Test
  public void testPLayerEndLocation(){
    System.out.print("Testing player end location..." + model.getPlayerEndLocation());
  }

}