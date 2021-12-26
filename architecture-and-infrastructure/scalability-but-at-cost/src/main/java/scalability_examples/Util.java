package scalability_examples;

import java.util.*;

public class Util {
	public static List<Double> randomNumbers(int size) {
		Random rand = new Random(2022);
    	List<Double> numbers = new ArrayList<Double>(size);    	
    	for(int i = 0; i < size; i++)
    		numbers.add(rand.nextDouble());
    	return numbers;
		
	}
}
