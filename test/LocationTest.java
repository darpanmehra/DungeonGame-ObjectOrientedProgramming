import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import dungeon.location.ILocation;
import dungeon.location.Location;
import dungeon.treasure.TreasureType;

import static org.junit.Assert.assertEquals;

public class LocationTest {
  public ILocation location;

  @Before
  public void setUp() throws Exception {
    location = new Location("a");
  }

  @Test
  public void testName(){
    assertEquals("1", location.toString());
  }

  @Test
  public void removeTreasure() {
    //HashMap<TreasureType, Integer> treasure = location.removeTreasure();
    assertEquals("{}", location.toString());
  }
  

}