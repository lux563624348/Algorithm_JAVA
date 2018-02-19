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
import Particle_Class.*;

public class Divide_And_Conquer {
	
	public static int randomWithRange(int min, int max){
		int range=Math.abs(max-min)+1;
		return (int)(Math.random()*range)+min;
		}
		
	public static double minFunction(double n1, double n2){
		double min;
		if (n1>n2){min=n2;}
		else{min=n1;}
		return min;
		}
	
	public static void main (String[] args) {
		System.out.println("Start of Divide_And_Conquer");
		//System.out.println("Min value is: "+minFunction(1.1, 2.2));
		int Num_Particles=5;
		Particles[] cluster = new Particles[Num_Particles];
		for (int x=0; x<Num_Particles; x++){
			cluster[x] = new Particles(randomWithRange(0,100));
			cluster[x].Show_Status();
			//cluster[x].Save_Data("first10");
			}
		System.out.println("Particles:");
		}
}

