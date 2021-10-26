import org.junit.Before;
import org.junit.Test;

import dungeon.character.Character;
import dungeon.character.Player;

import static org.junit.Assert.assertEquals;

public class CharacterTest {

  public Character player;

  @Before
  public void setUp() throws Exception {
    player = new Player("Player 1");
  }

  @Test
  public void testPLayerCreation(){
    assertEquals("Player 1", player.getName());
    // assertEquals("Player 1", player.getTreasures());
  }

}