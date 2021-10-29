package random;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Mock random class for the random number creation.
 */
public class RandomMock extends Random {

  int incrementer = 0;
  List<Integer> predefined;

  public RandomMock(Integer... values) {
    predefined = Arrays.asList(values);
  }


  @Override
  public int nextInt(int n) {
    //return n;
    int output = predefined.get(incrementer);
    incrementer++;
    if (incrementer >= predefined.size()) {
      incrementer = 0;
    }
    return output;
  }
}
