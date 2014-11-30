package edu.dcy.spark.local;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class WordCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SparkConf sparkConf = new SparkConf().setMaster("local").setAppName(
				"wordcount");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		// JavaRDD<String> lines=jsc.textFile("/home/spark/t").filter(s ->
		// s.contains("take"));
		JavaRDD<String> lines = sc.textFile("/home/spark/test.txt");

		// Map each line to multiple words
		JavaRDD<String> words = lines
				.flatMap(new FlatMapFunction<String, String>() {
					public Iterable<String> call(String line) {
						return Arrays.asList(line.split(" "));
					}
				});

		// Turn the words into (word, 1) pairs
		JavaPairRDD<String, Integer> ones = words
				.mapToPair(new PairFunction<String, String, Integer>() {
					public Tuple2<String, Integer> call(String w) {
						return new Tuple2<String, Integer>(w, 1);
					}
				});

		// Group up and add the pairs by key to produce counts
		JavaPairRDD<String, Integer> counts = ones
				.reduceByKey(new Function2<Integer, Integer, Integer>() {
					public Integer call(Integer i1, Integer i2) {
						return i1 + i2;
					}
				});

		counts.saveAsTextFile("/home/spark/t");

	}

}
