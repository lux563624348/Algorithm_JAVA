/*
 * K_Server.java
 * 
 * Copyright 2018 Xiang <Xiang@LAPTOP-Q0TSHFKK>
 * 
 * Fast Response k-Server Problem
A series of client machines [1, 2, … n] are located along a linear network.
The i-th client generates amount of traffic that is given by w[i]. 
You want to place k servers along the linear network that minimizes the 
total amount of traffic carried by the network. Total traffic is given 
by sum of each client’s individual traffic, multiplied by the distance 
(the number of hops) from the server. Provide a polynomial time algorithm
to identify the optimal locations for k servers.
 */
import java.io.*;
import java.util.Arrays;


public class K_Server{ //Dynamic Programming Problem {
	
	public static void main (String[] args) {
		//
		int Num_Servers=10;
		int Num_Nodes=5;
		
		double Total_traffic=0;
		// OUTPUT the generating data.
		String Out_NAME="Servers"+Num_Servers;
		System.out.println("Generating" + Num_Servers + " Servers");
		
		// Define an array of objects
		Servers[] cluster = new Servers[Num_Servers];
		
		// Display and Saving generated data
		for (int i=0; i<Num_Servers; i++){
			//cluster[i].Show_Status();
			cluster[i] = new Servers(randomWithRange(0,100));
			cluster[i].Save_Data(Out_NAME);
			}
		//
		
		//double[] tem_cor=new double[Num_Particles];
		
		/*for (int i=0; i<Num_Particles; i++){
			tem_cor[i]=cluster[i].x[0];
		}
		Arrays.sort(tem_cor);
		*/
		
		
		for (int i=0; i<Num_Servers; i++){
			//cluster[i] = new Servers(tem_cor[i]);
			cluster[i].Show_Status();
			//cluster[i].Save_Data(Out_NAME);
		}
	}

	static class Nodes{
		public int coordinates;
		public double Sum_Traffic;
		
		
		Nodes(){
		//System.out.println("Default Constructor?");
		}
		Nodes(int... coordinates){
		this.coordinates=coordinates;
		}
		
		for (int i=0; i<Num_Servers; i++){
			Sum_Traffic += coordinates-
			}
		
		
		public void Show_Status(){
		switch (coordinates.length){
			case 1 : System.out.println("Nodes Location is: coordinates: "+x[0]);break;
			default : System.out.println("Err: Wrong Coordinates");break;}
		}
		
		
		
		}
	
	static class Servers{
		public int[] coordinates;
		public double[] x;
		//public double velocity=0;
	Servers(){
		//System.out.println("Default Constructor?");
		}
	Servers(double... x){
		this.x=x;}
		
	//Methods
	
	public void Show_Status(){
		switch (x.length){
			case 1 : System.out.println("Server Traffic: x: "+x[0]);break;
			//case 2 : System.out.println("Particle Coordinates: x: "+x[0]+" y: "+x[1]);break;
			//case 3 : System.out.println("Particle Coordinates: x: "+x[0]+" y: "+x[1]+ " z: "+x[2]);break;
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

