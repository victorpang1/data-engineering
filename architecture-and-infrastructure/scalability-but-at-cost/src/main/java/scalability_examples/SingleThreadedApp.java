package scalability_examples;

import java.util.*;

public final class SingleThreadedApp {

    public static void main(String[] args) {
    	int size = Util.SIZE;
    	List<Double> numbers = Util.randomNumbers(size);

    	long start = System.currentTimeMillis();
    	double sum = 0.0;
    	for(var number:numbers)
    		sum += number;
    	double average = sum / size;
    	
        System.out.printf("The average is %f\n", average);
        long end = System.currentTimeMillis();
        long runTime = end - start;
        
        System.out.printf("The computation took %d milliseconds\n", runTime);      
    }
}
