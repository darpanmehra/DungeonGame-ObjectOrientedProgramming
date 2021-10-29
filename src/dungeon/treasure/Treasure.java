package dungeon.treasure;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Treasure implements ITreasure {
  private Map<TreasureType, Integer> treasury;
  private Random random;
  private final int MIN_TREASURE_QUANTITY = 1;
  private final int MAX_TREASURE_QUANTITY = 10;


  public Treasure(Random random) {
    this.random = random;
    this.treasury = new HashMap<>();
    initializeTreasure();
  }

  private void initializeTreasure() {
    treasury.put(TreasureType.RUBIES, random.nextInt(MAX_TREASURE_QUANTITY) + MIN_TREASURE_QUANTITY);
    treasury.put(TreasureType.DIAMONDS, random.nextInt(MAX_TREASURE_QUANTITY) + MIN_TREASURE_QUANTITY);
    treasury.put(TreasureType.SAPPHIRES, random.nextInt(MAX_TREASURE_QUANTITY) + MIN_TREASURE_QUANTITY);
  }

  @Override
  public int numberOfTreasure() {
    int count = 0;
    for (Integer value: treasury.values()) {
      count = count + value;
    }
    return count;
  }


  @Override
  public String toString() {
    return treasury.toString();
  }
}
