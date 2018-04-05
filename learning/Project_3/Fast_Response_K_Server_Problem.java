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



public class Fast_Response_K_Server_Problem{ //Dynamic Programming Problem {
	
	public static void main (String[] args) {
		//
		int Num_Clients=10;
		int Num_Servers=Num_Clients/10;
		int Counting_Num=0;
		// Define a 2d array, M[1][1] represents Total traffic, M[1][2] is the location of server 1.
		// M[2][2] is the location of server 2, and M[2][1] is the total traffic.
		int[][] Total_Traffic_Location=new int[Num_Servers][Num_Servers];
		// OUTPUT the generating data.
		String Out_NAME="Clients_"+Num_Clients;
		System.out.println("Generating " + Num_Clients + " Clients with traffic as following");
		
		// Define an array of objects
		int[] W_traffic = new int[Num_Clients];
		
		

		// Display and Saving generated data
		for (int i=0; i<Num_Clients; i++){
			//cluster[i].Show_Status();
			W_traffic[i] = randomWithRange(1,1000);
			System.out.println("Generating "+(i+1)+"-th server's traffic is " +W_traffic[i]);
			Counting_Num++;
			}
		//
		System.out.println("*************************************************************");
		int[][] Tem_Total_Traffic=new int[Num_Clients][2];
		int[][] Tem_Total_Traffic_sorted=new int[Num_Clients][2];
		int Comparing_Traffic=0;
		
		for (int i=0; i<Num_Clients;i++){
			Tem_Total_Traffic[i]=Get_Server_Traffic(Num_Clients,i+1, W_traffic);
			Counting_Num+=Num_Clients;
			//System.out.println("Total_Traffic_For_Server_Locating_At " + Tem_Total_Traffic[i][1] + " is " + Tem_Total_Traffic[i][0]);
			}
		//Arrays.sort(Tem_Total_Traffic);
		Arrays.sort(Tem_Total_Traffic, (a, b) -> Double.compare(a[0], b[0]));
		
		/* Display Sorted Traffic of server
		for (int i=0; i<Num_Clients;i++){
			System.out.println(Arrays.toString(Tem_Total_Traffic[i]));
		}
		*/
		int Total_Traffic_Final=0;
		for (int i=0; i<Num_Servers;i++){
			Total_Traffic_Final+=Tem_Total_Traffic[i][0];
			Counting_Num++;
			System.out.println("The "+ (i+1) + "-th server locates at "+ Tem_Total_Traffic[i][1]);
			//System.out.println(Arrays.toString(Tem_Total_Traffic[i]));
		}
		System.out.println("Minimum Total Traffic of " + Num_Clients + " Client Machines and " + Num_Servers+ " Servers is "+Total_Traffic_Final);
		System.out.println("Minimum Total Traffic Problem Costs "+ Counting_Num+ " Steps");
	}


	public static int[] Get_Server_Traffic(int Num_Clients, int Location_Server, int[] clusters_traffic){
		int[] Sum_Traffic=new int[2];
		
		for (int i=0; i<Num_Clients; i++){
			Sum_Traffic[0] += Math.abs( Location_Server - i)*clusters_traffic[i];
		}
		Sum_Traffic[1]=Location_Server;
		//System.out.println("Sum Traffic of server locating in Location_Server is: " + Sum_Traffic[1]);
		return Sum_Traffic;
		}

	// END of Methods
	
	// Functions
	
	
	public static double randomWithRange( double min, double max){
		double range=Math.abs(max-min)+1;
		return (double)(Math.random()*range)+min;
		}
		
	public static int randomWithRange( int min, int max){
		int range=Math.abs(max-min)+1;
		return (int)(Math.random()*range)+min;
		}
		
		
	public static double minFunction(double n1, double n2){
		double min;
		if (n1>n2){min=n2;}
		else{min=n1;}
		return min;
		}
}

