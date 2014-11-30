package edu.dcy.spark.local;

import java.util.List;

import com.google.common.collect.Lists;

public class LambdaDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
			System.out.println("sum is:"+nums.stream().filter(num -> num != null).
		    			distinct().mapToInt(num -> num * 2).
		                peek(System.out::println).skip(2).limit(4).sum());
		    
	}

}
