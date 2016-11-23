package sjsu.Swe.cs146.project1.part1of2;

import java.util.Random;

class SquareMatrix {
	private double [][] matrix = null;
	private java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
	private int mySize = 0;
	
	public SquareMatrix (int n) {
		this.matrix = new double [n][n];
		this.mySize = n;
	}
	
	public SquareMatrix(double[][] array) {
		this.matrix = array;
		this.mySize = array.length;
	}
	
	public double[][] getMatrix() {
		return matrix;
	}
	
	public void doLoad() {		
		Random number = new Random();
        for (int row = 0; row < this.mySize; row++) {
        	for (int coloum = 0; coloum < this.matrix[row].length; coloum++) {
        		this.matrix[row][coloum] = (10+ (100-10) * number.nextDouble());
        	} 
        }
	}
	
	public void doPrint(){
		// Need work to work with 2^n size
		
		for (int i = 0; i <this.mySize; i++)
        {
            for (int j = 0; j < this.mySize; j++)
                System.out.print(df.format(this.matrix[i][j]) +"\t \t");
            System.out.println();
        }
        
	}
	
	public SquareMatrix productRegular (SquareMatrix B) {
		SquareMatrix C = new SquareMatrix(this.mySize);
		
		double sum = 0;
        
        for(int row = 0; row < this.mySize; row++)
        {
            for (int col = 0; col < this.mySize; col++)
            {
                sum=0;
                for(int k=0; k < this.mySize; k++)
                {
                    sum += this.matrix[row][k] * B.matrix[k][col];
   
                }
                C.matrix[row][col] = sum;
            }
        }
		return C;
	}
	
	public SquareMatrix productStrassen (SquareMatrix B) {
		SquareMatrix C = new SquareMatrix(this.mySize);
		C.matrix = StressMultiply(this.matrix, B.matrix);
		return C;
	}
	
    /** Function to multiply matrices **/
    private double[][] StressMultiply(double[][] A, double[][] B)
    {   
    	
        int n = A.length;
        double[][] R = new double[n][n];
        /** base case **/
        if (n == 1)
            R[0][0] = A[0][0] * B[0][0];
        else
        {
            double[][] A11 = new double[n/2][n/2];
            double[][] A12 = new double[n/2][n/2];
            double[][] A21 = new double[n/2][n/2];
            double[][] A22 = new double[n/2][n/2];
            double[][] B11 = new double[n/2][n/2];
            double[][] B12 = new double[n/2][n/2];
            double[][] B21 = new double[n/2][n/2];
            double[][] B22 = new double[n/2][n/2];
 
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
 
            double [][] M1 = StressMultiply(add(A11, A22), add(B11, B22));
            double [][] M2 = StressMultiply(add(A21, A22), B11);
            double [][] M3 = StressMultiply(A11, sub(B12, B22));
            double [][] M4 = StressMultiply(A22, sub(B21, B11));
            double [][] M5 = StressMultiply(add(A11, A12), B22);
            double [][] M6 = StressMultiply(sub(A21, A11), add(B11, B12));
            double [][] M7 = StressMultiply(sub(A12, A22), add(B21, B22));
 
            /**
              C11 = M1 + M4 - M5 + M7
              C12 = M3 + M5
              C21 = M2 + M4
              C22 = M1 - M2 + M3 + M6
            **/
            double [][] C11 = add(sub(add(M1, M4), M5), M7);
            double [][] C12 = add(M3, M5);
            double [][] C21 = add(M2, M4);
            double [][] C22 = add(sub(add(M1, M3), M2), M6);
 
            /** join 4 halves into one result matrix **/
            join(C11, R, 0 , 0);
            join(C12, R, 0 , n/2);
            join(C21, R, n/2, 0);
            join(C22, R, n/2, n/2);
        }
      
        /** return result **/    
         return R;
    }
    /** Function to sub two matrices **/
    private double[][] sub(double[][] A, double[][] B)
    {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }
    /** Function to add two matrices **/
    private double[][] add(double[][] A, double[][] B)
    {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }
    /** Function to split parent matrix into child matrices **/
    private void split(double[][] P, double[][] C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }
    /** Function to join child matrices into parent matrix **/
    private void join(double[][] C, double[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }    
}

