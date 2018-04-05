/*
 * Divide_And.java
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
import java.util.Scanner;

public class Matrix_Strassen{
	
	public static int randomWithRange(int min, int max){
		int range=Math.abs(max-min)+1;
		return (int)(Math.random()*range)+min;
		}
		
	
	public static void main (String[] args) {
		System.out.println("Start of Divide_And_Conquer");
		//System.out.println("Min value is: "+minFunction(1.1, 2.2));
		int Num=3;
		double[][] Matrix_test = new double[Num][Num];
		
		for(int i=0;i<Num;i++){
			for(int j=0;j<Num;j++){
				Matrix_test[i][j]=randomWithRange(0,100);
				}
			}
		
		
		System.out.println("Matrix:" + java.util.Arrays.toString(Matrix_test));
		}
}

