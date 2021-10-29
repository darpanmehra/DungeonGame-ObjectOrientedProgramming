import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import dungeon.Grid;
import random.RandomMock;

import static org.junit.Assert.assertEquals;

public class GridTest {

  private Grid grid;

  @Before
  public void setUp(){
    //Random random = new RandomMock(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
    //Random random = new RandomMock(0);
    Random random = new Random();
    grid = new Grid(4, 6, random);
  }

  @Test
  public void testPotentialPath(){
    //grid.
    System.out.println(grid.getPotentialPath());

    System.out.println(grid.getPotentialPath().size());
//
//    System.out.println(grid.getKrus());
//
//    System.out.println(grid.getKrus().size());
  }

  @Test
  public void testConnection(){
    //grid.
    grid.testConnection();
  }

  @Test
  public void testPrintGrid(){
    //grid.testConnection();
    grid.printGrid();
  }

}
