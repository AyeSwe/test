package sjsu.Swe.cs146.project1.part1of2;
import static org.junit.Assert.assertArrayEquals;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


/**
 * The main JUnit Test class to test regular multiplication 
 * and Strassen multiplication method.
 * 
 *
 */
public class MatrixTest extends TestCase{

	private SquareMatrix A, B; //input matrices
	private SquareMatrix productRegularResult, productStrassenResult; // Matrices for storing the results
	private int N; // size of the NXN matrix
	@Before
	public void setUp() throws Exception
	{
	   N = 4; // size of the matrix
	   double[][] array1 = new double[N][N];
	   double[][] array2 = new double[N][N];
	   
	   A = new SquareMatrix(array1);
	   B = new SquareMatrix(array2);
	   productRegularResult = new SquareMatrix(N);
	   productStrassenResult = new SquareMatrix(N);
	   	   
	} // setUp()

	
	/* compare result matrices of regular multiplication method and Strassen multiplication method:
	 */
	@Test
	public void testProductCompare() {
	    
	     
	     //run user defined random() method to generate the matrices
	     A.doLoad();
	     B.doLoad();
	      
	     // run productRegular()
	     productRegularResult = A.productRegular(B);
	   
	     // run productStrassen()
		 productStrassenResult = A.productStrassen(B);
		 
	     for (int i = 0; i < N; i++) {
	    	assertArrayEquals(productRegularResult.getMatrix()[i], productStrassenResult.getMatrix()[i], 0.0001 );
		}		
	}
	     
	/* multiplying a 2D array using the regular method:
	 */
	@Test
	public void testProductRegular() {
	    
	    //expected output
		double[][] expected = {{96.0,94.0,81.0,128.0},{144.0,117.0,112.0,162.0},{132.0,112.0,101.0,152.0},{112.0,86.0,87.0,130.0}};
	    
		// input 2D arrain
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] array2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 		
	    
		SquareMatrix m1 = new SquareMatrix(array1);
		SquareMatrix m2 = new SquareMatrix(array2);
	      
	    // run productRegular()
		productRegularResult = m1.productRegular(m2);
	     
	    for (int i = 0; i < N; i++) {
			assertArrayEquals(expected[i],productRegularResult.getMatrix()[i], 0.0); // data[][] is a data member for storing matrix values in class Matrix.
		}
	    
	}
	
	/* multiplying a 2D array using the Strassen method:
	 */
	@Test
	public void testProductStrassen() {
	    
	    //expected output
		double[][] expected = {{96.0,94.0,81.0,128.0},{144.0,117.0,112.0,162.0},{132.0,112.0,101.0,152.0},{112.0,86.0,87.0,130.0}};
	    
		// input 2D array
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] array2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 		
	    
		SquareMatrix m1 = new SquareMatrix(array1);
		SquareMatrix m2 = new SquareMatrix(array2);
	      
	    // run productRegular()
		productStrassenResult= m1.productStrassen(m2);
	     
	    for (int i = 0; i < N; i++) {
			assertArrayEquals(expected[i],productStrassenResult.getMatrix()[i], 0.0); // data[][] is a data member for storing matrix values in class Matrix.
		}
	    
	}
	
} // class MatrixTest