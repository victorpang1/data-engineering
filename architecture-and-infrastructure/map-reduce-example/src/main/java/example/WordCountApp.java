package example;

import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

import java.util.Scanner;

public final class WordCountApp {

	/**
	 * count the frequency of each word in a text file
	 */
	public static void main(String[] args) {
		var spark = SparkSession.builder().appName("First Spark Application!").config("spark.master", "local[*]")
				.getOrCreate();
		var sc = new JavaSparkContext(spark.sparkContext());
		sc.setLogLevel("ERROR");

		String inputFile, outputFile;
		Scanner scan = new Scanner(System.in);
		if (args.length != 2) {			
			System.out.println("Enter input file and output file names");
			inputFile = scan.nextLine().trim();
			outputFile = scan.nextLine().trim();		
		} else {
			inputFile = args[0];
			outputFile = args[1];
		}

		JavaRDD<String> input = sc.textFile(inputFile);

		var count = input.count();
		System.out.printf("The file contains %d lines\n", count);

		// complete the code to count the number of words in the file
		// and write the output to the chosen file
		// consider the space character to be the word delimiter

		FlatMapFunction<String, String> lineSpliter = line -> Arrays.asList(line.split(" ")).iterator();
		// Split up into words.
		JavaRDD<String> words = input.flatMap(lineSpliter);
		
		// transform each word to a tuple
		PairFunction<String, String, Integer> tupleMaker = word -> new Tuple2(word, 1);
		JavaPairRDD<String, Integer> tuples = words.mapToPair(tupleMaker);
		
		// Calculate the sum
		Function2<Integer, Integer, Integer> sumFunction = (x, y) -> x + y;
		JavaPairRDD<String, Integer> counts = tuples.reduceByKey(sumFunction);
		
		System.out.println(counts.toDebugString());
		// Save the word count back out to a text file, causing evaluation.
		counts.saveAsTextFile(outputFile);

		System.out.println("Press enter key to exit");
		scan.nextLine();
		spark.close();
	}
}
