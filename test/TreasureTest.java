import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import dungeon.treasure.ITreasure;
import dungeon.treasure.Treasure;
import dungeon.treasure.TreasureType;

import static org.junit.Assert.assertEquals;

public class TreasureTest {

  private ITreasure treasure;
  private Random random;

  @Before
  public void setUp() throws Exception {
    random = new Random();
    random.setSeed(10);
    treasure = new Treasure(random);
  }

  @Test
  public void getTreasure() {
    Map<TreasureType, Integer> expected = new HashMap<>();
    expected.put(TreasureType.RUBIES, 4);
    expected.put(TreasureType.SAPPHIRES, 4);
    expected.put(TreasureType.DIAMONDS, 1);

    assertEquals(expected, treasure.getTreasure());
  }

  @Test
  public void testToString() {
    String expected = "{DIAMONDS=1, RUBIES=4, SAPPHIRES=4}";
    assertEquals(expected, treasure.toString());
  }
}