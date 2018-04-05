/*
 * Divide_And_Conquer
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
//import java.util.Scanner;

public class Matrix_Strassen{
	public static int Counting_Num=0;
	public static void main (String[] args) {
		System.out.println("Start of Divide_And_Conquer");
		int[] Num_list={2,4,8,16,32,64,128,256,512,1024};
		for (int Num : Num_list){
		Counting_Num=0;
		int[][] Matrix_A=Ini_Matrix(Num);
		int[][] Matrix_B=Ini_Matrix(Num);
		int[][] Matrix_C=Ini_Matrix(Num);
		//Show_Matrix(Matrix_A);
		//Show_Matrix(Matrix_B);
		
		Matrix_C=Multiply_Matrix(Matrix_A, Matrix_B);
		//Show_Matrix(Matrix_C);
		
		System.out.println("End of Divide_And_Conquer, Matrix Size: "+ Num +" Times: " + Counting_Num);
		
		}
	}
		
		
	public static int[][] Ini_Matrix(int num_rank){
		int[][] Matrix_test=new int[num_rank][num_rank];
		for(int i=0;i<num_rank; i++){
			for(int j=0;j<num_rank; j++){
				Matrix_test[i][j]=randomWithRange(0,100);
			}
		}
		return Matrix_test;
	}
	
	public static void Show_Matrix(int[][] matrix){
		System.out.println("\nMatrices elements are: ");
		int Num_rank=matrix.length;
		for (int i=0; i<Num_rank; i++){
			for(int j=0; j<Num_rank; j++){
				System.out.print(matrix[i][j] +" ");
			}
			System.out.println();
		}
	}
	
	public static int[][] Multiply_Matrix(int[][] A, int[][] B){
		int n = A.length;
		int[][] R = new int[n][n];

		if (n == 1){
			R[0][0] = A[0][0] * B[0][0];
			Counting_Num++;}else
		{
			int[][] A11 = new int[n/2][n/2];
			int[][] A12 = new int[n/2][n/2];
			int[][] A21 = new int[n/2][n/2];
			int[][] A22 = new int[n/2][n/2];
			int[][] B11 = new int[n/2][n/2];
			int[][] B12 = new int[n/2][n/2];
			int[][] B21 = new int[n/2][n/2];
			int[][] B22 = new int[n/2][n/2];

			/** Dividing matrix A into 4 halves **/
			split(A, A11, 0 , 0);
			split(A, A12, 0 , n/2);
			split(A, A21, n/2, 0);
			split(A, A22, n/2, n/2);
			/** Dividing matrix B into 4 halves **/
			split(B, B11, 0 , 0);
			split(B, B12, 0 , n/2);
			split(B, B21, n/2, 0);
			split(B, B22, n/2, n/2);

			/** 
			  M1 = (A11 + A22)(B11 + B22)
			  M2 = (A21 + A22) B11
			  M3 = A11 (B12 - B22)
			  M4 = A22 (B21 - B11)
			  M5 = (A11 + A12) B22
			  M6 = (A21 - A11) (B11 + B12)
			  M7 = (A12 - A22) (B21 + B22)
			**/

			int [][] M1 = Multiply_Matrix(add(A11, A22), add(B11, B22));
			int [][] M2 = Multiply_Matrix(add(A21, A22), B11);
			int [][] M3 = Multiply_Matrix(A11, sub(B12, B22));
			int [][] M4 = Multiply_Matrix(A22, sub(B21, B11));
			int [][] M5 = Multiply_Matrix(add(A11, A12), B22);
			int [][] M6 = Multiply_Matrix(sub(A21, A11), add(B11, B12));
			int [][] M7 = Multiply_Matrix(sub(A12, A22), add(B21, B22));

			/**
			  C11 = M1 + M4 - M5 + M7
			  C12 = M3 + M5
			  C21 = M2 + M4
			  C22 = M1 - M2 + M3 + M6
			**/
			int [][] C11 = add(sub(add(M1, M4), M5), M7);
			int [][] C12 = add(M3, M5);
			int [][] C21 = add(M2, M4);
			int [][] C22 = add(sub(add(M1, M3), M2), M6);

			/** join 4 halves into one result matrix **/
			join(C11, R, 0 , 0);
			join(C12, R, 0 , n/2);
			join(C21, R, n/2, 0);
			join(C22, R, n/2, n/2);
		}
		/** return result **/    
		return R;
	}
	/** Funtion to sub two matrices **/
	public static int[][] sub(int[][] A, int[][] B)
	{
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				//Counting_Num++;
				C[i][j] = A[i][j] - B[i][j];
			}
		}
		return C;
	}
	/** Funtion to add two matrices **/
	public static int[][] add(int[][] A, int[][] B)
	{
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				C[i][j] = A[i][j] + B[i][j];
				//Counting_Num++;
				}
			}
		return C;
	}
	/** Funtion to split parent matrix into child matrices **/
	public static void split(int[][] P, int[][] C, int iB, int jB) 
	{
		for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++){
			for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++){
				C[i1][j1] = P[i2][j2];
				//Counting_Num++;
				}
			}
	}
	/** Funtion to join child matrices intp parent matrix **/
	public static void join(int[][] C, int[][] P, int iB, int jB) 
	{
		for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++){
			for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++){
				P[i2][j2] = C[i1][j1];
				//Counting_Num++;
			}
		}
	}
	
	public static int randomWithRange(int min, int max){
		int range=Math.abs(max-min)+1;
		return (int)(Math.random()*range)+min;
	}

}

