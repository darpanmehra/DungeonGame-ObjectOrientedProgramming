package dungeon.character;

import java.util.Map;
import dungeon.treasure.TreasureType;

public interface Character {

  String getName();

  Map<TreasureType, Integer> getTreasures();

}
