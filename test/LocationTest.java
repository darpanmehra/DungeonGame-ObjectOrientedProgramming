import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import dungeon.location.ILocation;
import dungeon.location.Location;

import static org.junit.Assert.assertEquals;

public class LocationTest {
  public ILocation location;

  @Before
  public void setUp() throws Exception {
    location = new Location("a", 0, 0);
  }

  @Test
  public void testName()
  {
    ILocation location2 = new Location("b", 0, 1);
    location.setTreasure();
    location.joinLocationToEastDirection(location2);
    assertEquals("1", location.toString());
  }

  @Test
  public void removeTreasure() {
    //HashMap<TreasureType, Integer> treasure = location.removeTreasure();
    assertEquals("{}", location.toString());
  }
  

}