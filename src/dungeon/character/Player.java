package dungeon.character;

import java.util.Map;
import java.util.TreeMap;

import dungeon.treasure.TreasureType;

public class Player implements Character {

  private String name;
  private Map<TreasureType, Integer> treasures;

  public Player(String name){
      this.name = name;
      this.treasures = new TreeMap<TreasureType, Integer>();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Map<TreasureType, Integer> getTreasures() {
    return this.treasures;
  }

}
