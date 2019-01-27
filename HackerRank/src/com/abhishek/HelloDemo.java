package com.abhishek;

public class HelloDemo extends StaticDemo {
	
	public static void demo(){
		
	}
	
	public static void main(String args []){
		
		System.out.println("Hello there");
		int t =2;
		int a [] = {3,15};
		for (int j = 0; j < t; j++) {
            int num = a[j];
            for (int k = 1; k <= num; k++) {
                if (k % 15 == 0)
                    System.out.println("FizzBuzz");
                else if (k % 3 == 0)
                    System.out.println("Fizz");
                else if (k % 5 == 0)
                    System.out.println("Buzz");
                else
                    System.out.println(k);
            }   
        }
	}

}
