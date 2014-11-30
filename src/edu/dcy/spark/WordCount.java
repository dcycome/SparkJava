package edu.dcy.spark;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class WordCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  
	    SparkConf sparkConf = new SparkConf().setMaster("spark://spark-desktop:7077").setAppName("JavaSparkPi");
	    JavaSparkContext  sc = new JavaSparkContext(sparkConf);
//		JavaRDD<String> lines=jsc.textFile("/home/spark/t").filter(s  -> s.contains("take"));
	    JavaRDD<String> lines = sc.textFile("hdfs://spark-desktop:9000/data/wordcount/core-site.xml");
	    JavaRDD<String> words =lines.flatMap(line -> Arrays.asList(line.split(" ")));
	    JavaPairRDD<String, Integer> counts =
	        words.mapToPair(w -> new Tuple2<String, Integer>(w, 1))
	             .reduceByKey((x, y) -> x + y);
	    counts.saveAsTextFile("hdfs://spark-desktop:9000/output/result2.txt");
		long nums=lines.count();
		System.out.println("nums==="+nums);
		
	}

}
