package scalability_examples;

import java.util.*;

public final class SingleThreadedApp {

    public static void main(String[] args) {

    	int size = 100000000;
    	List<Double> numbers = Util.randomNumbers(size);

    	long start = System.currentTimeMillis();
    	int sum = 0;
    	for(var number:numbers)
    		sum += number;
    	double average = sum / size;
    	
        System.out.printf("The average is %f\n", average);
        long end = System.currentTimeMillis();
        long runTime = end - start;
        
        System.out.printf("The computation took %d milliseconds\n", runTime);
        
    }
}
