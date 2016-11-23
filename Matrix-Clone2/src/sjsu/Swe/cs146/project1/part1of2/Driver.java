package sjsu.Swe.cs146.project1.part1of2;

public class Driver {

	public static void main(String[] args) {
		SquareMatrix A = new SquareMatrix(4);
		SquareMatrix B = new SquareMatrix(4);
		SquareMatrix R = null;
		
		
		SquareMatrix C = new SquareMatrix(16);
		SquareMatrix D = new SquareMatrix(16);
		SquareMatrix E = null;
		
		
		SquareMatrix F = new SquareMatrix(512);
		SquareMatrix G = new SquareMatrix(512);
		SquareMatrix H = null;
		
		
		SquareMatrix I = new SquareMatrix(1024);
		SquareMatrix J = new SquareMatrix(1024);
		SquareMatrix K = null;
		
		A.doLoad();
		B.doLoad();
		C.doLoad();
		D.doLoad();
		F.doLoad();
		G.doLoad();
		I.doLoad();
		J.doLoad();
		
		
		R = A.productRegular(B);
		
		E=C.productRegular(D);
		long SimpleTime= System.currentTimeMillis();
		H=F.productRegular(G);
		long SimpleStopTime =System.currentTimeMillis();
		K=I.productRegular(J);
		
		R = A.productStrassen(B);
		
		E= C.productStrassen(D);
		long StressTime= System.currentTimeMillis();
		H = F.productStrassen(G);
		long StressStopTime = System.currentTimeMillis();
		K=I.productStrassen(J);
		
		try {
			R.doPrint();
			System.out.println();
			E.doPrint();
			System.out.println();
			H.doPrint();
			System.out.println();
		    K.doPrint();
		    System.out.println();
		    
			System.out.println("This is Simple Excution time: " + (SimpleStopTime-SimpleTime)+" millisecond");		      
		    System.out.println("This is Stress Excution time: " + (StressStopTime-StressTime)+" millisecond");

		} catch (Exception e) {
			System.out.println("Crapped out!!!\n" + e.toString());
			
		}
	}

}
