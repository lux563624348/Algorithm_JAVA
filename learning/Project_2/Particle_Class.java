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
import java.util.Arrays;


public class Particle_Class{ //implements Comparable<Particle_Class>{
	
	public static void main (String[] args) {
		int Num_Particles=200000;
		String Out_NAME="Particles"+Num_Particles;
		System.out.println("Generating" + Num_Particles + " Particles");
		Particles[] cluster = new Particles[Num_Particles];
		for (int i=0; i<Num_Particles; i++){
			cluster[i] = new Particles(randomWithRange(0,100));
			cluster[i].Save_Data(Out_NAME+"Raw");
			}
		//
		double[] tem_cor=new double[Num_Particles];
		
		for (int i=0; i<Num_Particles; i++){
			tem_cor[i]=cluster[i].x[0];
		}
		Arrays.sort(tem_cor);
		
		for (int i=0; i<Num_Particles; i++){
			cluster[i] = new Particles(tem_cor[i]);
			//cluster[i].Show_Status();
			cluster[i].Save_Data(Out_NAME);
		}
	}



	static class Particles {
		public double[] x;
		//public double velocity=0;
	Particles(){
		//System.out.println("Default Constructor?");
		}
	Particles(double... x){
		this.x=x;}
		
	//Methods
	
	public void Show_Status(){
		switch (x.length){
			case 1 : System.out.println("Particle Coordinates: x: "+x[0]);break;
			case 2 : System.out.println("Particle Coordinates: x: "+x[0]+" y: "+x[1]);break;
			case 3 : System.out.println("Particle Coordinates: x: "+x[0]+" y: "+x[1]+ " z: "+x[2]);break;
			default : System.out.println("Err: Wrong Coordinates");break;}
		//System.out.println("Particle Velocity is: "+velocity);
		}

	public void Save_Data(String args){
		try {
			String OUTPUT_NAME=args+".txt";
			FileWriter file = new FileWriter(OUTPUT_NAME, true);
			//if ( file.exists() ){file.write("Coordinates: \n");}  ## Find a way to print header.
			file.write(java.util.Arrays.toString(x).replace("[","").replace(",","").replace("]","")+"\n" );
			file.close();
		} catch(IOException e){
			System.out.print("Exception");
			}
		}
	}
	// END of Methods
	
	// Functions
	public static double randomWithRange( double min, double max){
		double range=Math.abs(max-min)+1;
		return (double)(Math.random()*range)+min;
		}
		
	public static double minFunction(double n1, double n2){
		double min;
		if (n1>n2){min=n2;}
		else{min=n1;}
		return min;
		}
}

