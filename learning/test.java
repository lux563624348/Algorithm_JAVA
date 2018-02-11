/*
 * test.java
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
import java.util.Date;
import java.text.*;
public class test {

	
	public static void printArray(int[] array) {
	for (int i = 0; i < array.length; i++) {
		System.out.print(array[i] + "\n");
		}
	}

	public static int[] Reverse_Arrary(int[] list) {
	int[] result = new int[list.length];
	for (int i = 0, j = result.length - 1; i < list.length; i++, j--) {
		result[j] = list[i];
	}
	return result;
	}

	public static void Test_Srtring(String ts){
		String Test_String=ts;
		System.out.println("Lenght is:"+Test_String.length());
		Test_String=Test_String+"FG!";
		System.out.println("Lenght is:"+Test_String.length());
		}
	
	public static void print_MM_DD_YYYY_date(){
		System.out.println("DATE is:"+new Date());
		SimpleDateFormat ft=new SimpleDateFormat ("E MM.dd.yyyy");
		System.out.println("Current Date: " + ft.format(new Date()));
		}

	public static void main (String[] args) {
		double [] Numbers={1e1,1e2,1e3,1e4};
		int [] Test_Array={1, 2, 3, 4, 5, 6};
		
		for(double num:Numbers){
			System.out.println("Numbers are:"+num);
			}
		Test_Srtring("ABCDE!");
		printArray(Test_Array);
		print_MM_DD_YYYY_date();
	}
}

