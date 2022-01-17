package data_locality;

import java.util.List;
import java.util.Scanner;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

public class LocalComputeRemoteData {

	public static void main(String[] args) {
    	
		SparkSession spark = SparkSession.builder().appName("LocalComputeRemoteData").config("spark.master", "local[1]").getOrCreate();
		JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
        sc.setLogLevel("ERROR");
        System.out.println(spark.version());
        
        // Replace Key with your AWS account key (You can find this on IAM service)
        sc.hadoopConfiguration().set("fs.s3a.access.key", "key");
        
         // Replace Key with your AWS secret key (You can find this on IAM 
        sc.hadoopConfiguration().set("fs.s3a.secret.key", "value");
        sc.hadoopConfiguration().set("fs.s3a.endpoint", "s3.amazonaws.com");        
        		
        long start = System.currentTimeMillis();
        // small file: s3a://uga-data-engineering/poem.txt 753.0 B
        // larger file: s3a://uga-data-engineering/big.txt 6.2MB
        // even larger file: s3a://uga-data-engineering/enwik8 95.4 MB 
        // even larger file: s3a://uga-data-engineering/enwik9 953.7 MB
        
        JavaRDD<String> rddFromFile  = sc.textFile("s3a://uga-data-lake/enwik9");
        
        //JavaRDD<String> rddFromFile  = sc.textFile("C:\\Users\\hanisaf\\Downloads\\enwik9\\enwik9");
        long lines = rddFromFile.count();
        System.out.printf("The file has %d lines\n", lines);
        
        long end = System.currentTimeMillis();
        long runTime = end - start;
        System.out.printf("The computation took %d milliseconds\n", runTime);    
        
        //new Scanner(System.in).nextLine();

	}

}
