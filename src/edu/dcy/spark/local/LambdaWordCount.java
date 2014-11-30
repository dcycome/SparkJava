package edu.dcy.spark.local;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class LambdaWordCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  
	    SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("wordcount");
	    JavaSparkContext  sc = new JavaSparkContext(sparkConf);
//		JavaRDD<String> lines=jsc.textFile("/home/spark/t").filter(s  -> s.contains("take"));
	    JavaRDD<String> lines = sc.textFile("/home/spark/test.txt");
	    JavaRDD<String> words =lines.flatMap(line -> Arrays.asList(line.split(" ")));
	    JavaPairRDD<String, Integer> counts =
	        words.mapToPair(w -> new Tuple2<String, Integer>(w, 1))
	             .reduceByKey((x, y) -> x + y);
	    counts.saveAsTextFile("/home/spark/output");

		
	}

}
