package dungeon;

import dungeon.location.ILocation;

public interface IGameState {

  ILocation getPlayerStartLocation();

}
