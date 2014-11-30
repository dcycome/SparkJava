package edu.dcy.spark.local;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.api.java.JavaSQLContext;

public class RDDTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  
	    SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JavaSparkPi");
	    
	    JavaSparkContext jsc = new JavaSparkContext(sparkConf);
	    
		JavaRDD<String> lines=jsc.textFile("/home/spark/test.txt").filter(s  -> s.contains("take"));
		
		long nums=lines.count();
		
		System.out.println("nums==="+nums);
		
		
	}

}
