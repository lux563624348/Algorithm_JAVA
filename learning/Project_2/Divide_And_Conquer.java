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
import java.io.*;

public class Divide_And_Conquer {
	
	static class Particles {
		public double [] x={0,0,0};
		public double velocity=0;
	Particles(){
		//System.out.println("Default Constructor?");
		}
	Particles(double... x){
		//this(); // Invoking the default constructor, Not necessary.
		this.x=x;
		}
		
	//Methods
	public void Show_Status(){
		System.out.println("Particle Coordinates: x: "+x[0]+" y: "+x[1]+ " z: "+x[2]);
		System.out.println("Particle Velocity is: "+velocity) ;
		}
	
		/*
	public void Read_Data(){
		InputStream f=new FileInputStream("input.bed");
		
		}
	*/
	public void Save_Data(){
		try {
			FileWriter os = new FileWriter("output.txt");
			os.write("Coordinates: \n"); 
			os.write(java.util.Arrays.toString(x).replace("[","").replace(",","").replace("]",""));
			os.close();
		} catch(IOException e){
			System.out.print("Exception");
			}
		}
	
	
	}
	

		
	public static double minFunction(double n1, double n2){
		double min;
		if (n1>n2)
			min=n2;
		else
			min=n1;
		return min;
		}
	
	public static void main (String[] args) {
		System.out.println("Start of Divide_And_Conquer");
		//System.out.println("Min value is: "+minFunction(1.1, 2.2));
		Particles p1=new Particles(1.1, 2.2, 3.3);
		Particles p2=new Particles();
		
		p1.Show_Status();
		p2.Show_Status();
		p1.Save_Data();
		
	}
}

