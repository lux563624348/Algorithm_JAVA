/*
 * Time_complexity.java
 * 
 * Copyright 2018 Xiang <Xiang@LAPTOP-Q0TSHFKK>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
import java.lang.Math.*;
import java.io.*;


public class Time_Complexity {
	
	public static void Algorithm_6(double N_steps){
		System.out.println("Algorithm_6");
		int j=2;
		double n=N_steps;
		double Sum=0;
		while (j<n){
			int k=j;
			while (k<n){
				Sum+=1;
				k+=Math.pow(n,1.0/3.0)*Math.log(n);
			}
			j*=2;
		}
		System.out.println("Input N is:"+n);
		System.out.println("Iteration Step is:"+Sum);
		double Estimate=Math.pow(n,2.0/3.0)/Math.log(2) - 2*Math.pow(n,2.0/3.0)/Math.log(n);//(Math.log(2)*Math.log(n));
		System.out.println("Theoritical Estimate is:"+Estimate);
	}
	
	public static void Algorithm_4(double N_steps){
		System.out.println("Algorithm_4");
		double n=N_steps;
		double Sum=0;
		
		for (int i=1; i < n; i++){
			double j=i;
			while (j<n) {
				double k=j;
				while (k<n){
					Sum+=1;
					k+=Math.log(Math.log(n));
					}
					j+= Math.log(j+10);
				}
			}
		System.out.println("Input N is:"+n);
		System.out.println("Iteration Step is:"+Sum);
		double Estimate=0.2*Math.pow(n,3.0)/Math.log(Math.log(n))/Math.log(n);//(Math.log(2)*Math.log(n));
		System.out.println("Theoritical Estimate is:"+Estimate);
	}
/*
	public static void Algorithm_1(double N_steps){
		int i=0;
		System.out.println("Algorithm_1");
		do {
			System.out.println("steps:"+i);
			i++;
			} while((i<N_steps));
	}
	
	public static void Algorithm_2(double N_steps){
		int [] numbers={0,1,2,3,4};
		System.out.println("Algorithm_Enhanced_For_Loop");
		for(int i : numbers){
			System.out.println("steps:"+i);
			} 
	}
	
	public static void Algorithm_switch(double N_steps){
		int [] numbers={0,1,2,3,4};
		System.out.println("Algorithm_Enhanced_For_Loop");
		for(int i : numbers){
			switch(i) {
				case 1:
				System.out.println("Your Score is 1");
				break;
				case 3:
				System.out.println("Your Score is 3");
				break;
				default:
				System.out.println("Your Score is null");
				}
				
			}
	} */
	
	public static void main (String[] args) {
		double [] numbers={1e1,1e2,1e3,1e4}; //,1e5,1e6,1e7,1e8,1e9};
		for (double n : numbers){
			Algorithm_4(n);
			}
		/*Algorithm_1(10);
		Algorithm_2(10);
		Algorithm_switch(10);*/
	}
}

