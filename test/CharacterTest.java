import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import dungeon.character.Character;
import dungeon.character.Player;
import dungeon.treasure.ITreasure;
import dungeon.treasure.Treasure;

import static org.junit.Assert.assertEquals;

public class CharacterTest {

  public Character player;
  public Random rand;
  @Before
  public void setUp() throws Exception {
    player = new Player("Player 1");
    this.rand = new Random();
    rand.setSeed(10);
  }

  @Test
  public void testPLayerCreation(){
    assertEquals("Player 1", player.getName());
    // assertEquals("Player 1", player.getTreasures());
  }

  @Test
  public void testPlayerTreasure(){
    ITreasure treasure1 = new Treasure(rand);
    ITreasure treasure2 = new Treasure(rand);
    ITreasure treasure3 = new Treasure(rand);
    System.out.println(treasure1.getTreasure());
    System.out.println(treasure2.getTreasure());
    System.out.println(treasure3.getTreasure());
    player.addTreasure(treasure1);
    player.addTreasure(treasure2);
    player.addTreasure(treasure3);


    assertEquals("{}", player.getTreasures());
  }
}