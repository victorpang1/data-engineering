package scalability_examples;

import java.util.List;
import java.util.Scanner;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

public class DistributedApp {
	public static void main(String[] args) {
    	int size = Util.SIZE;
    	List<Double> numbers = Util.randomNumbers(size);
    	
		var spark = SparkSession.builder().appName("DistributedApp").config("spark.master", "local[*]").getOrCreate();
        var sc = new JavaSparkContext(spark.sparkContext());
        sc.setLogLevel("ERROR");

        System.out.println(spark.version());		
        long start = System.currentTimeMillis();
        int cores = Runtime.getRuntime().availableProcessors();
        JavaRDD<Double> rdd = sc.parallelize(numbers, cores);
        double sum = rdd.reduce((x, y) -> x + y);
    	double average = sum / size;
        System.out.printf("The average is %f\n", average);
        long end = System.currentTimeMillis();
        long runTime = end - start;
        System.out.printf("The computation took %d milliseconds\n", runTime);    
        
        new Scanner(System.in).nextLine();
	}
}
