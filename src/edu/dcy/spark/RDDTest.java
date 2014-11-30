package edu.dcy.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.api.java.JavaSQLContext;

public class RDDTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  
	    SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JavaSparkPi");
	    
	    JavaSparkContext jsc = new JavaSparkContext(sparkConf);
	    
//		JavaRDD<String> lines=jsc.textFile("/home/spark/t").filter(s  -> s.contains("take"));
	    JavaRDD<String> lines=jsc.textFile("hdfs://spark-desktop:9000/data/wordcount/core-site.xml").filter(s  -> s.contains("b"));
		
		long nums=lines.count();
		
		System.out.println("nums==="+nums);
		
		
	}

}
