

public class MatrixOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] mat1 = {{2,-4},{5,0},{1,-3}};
		double[][] mat2 = {{-1,0},{-2,1},{3,-3}};
		
		System.out.println("Addition");
		printmatrix(Add(mat1,mat2));
		
		System.out.println("Scalar Multiplication");
		printmatrix(ScalarMultiplication(mat1,4));
		
		double[][] mat3 = {{2,3},{-5,6},{9,-7}};
		double[][] mat4 = {{1,-2,0},{3,4,-5}};
		System.out.println("Matrix Multiplication");
		printmatrix(Multiplication(mat3,mat4));
		
		System.out.println("Matrix Transpose");
		printmatrix(Transpose(mat3));
	}
	
	public static double[][] Transpose(double[][] A){
		
		double[][] resultMat = new double[A[0].length][A.length];
		
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<A[i].length; j++) {
				resultMat[j][i] = A[i][j];
			}
		}
		
		return resultMat;
	}
	
	public static double[][] Add(double[][] A, double[][] B) {
		
		double[][] resultMat = new double[A.length][A[0].length];
		
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<A[i].length; j++) {
				resultMat[i][j] = A[i][j] + B[i][j];
			}
		}
		
		return resultMat;
	}

	
	public static double[][] ScalarMultiplication(double[][] A, double scalar) {
		
		double[][] resultMat = new double[A.length][A[0].length];
		
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<A[i].length; j++) {
				resultMat[i][j] = scalar*A[i][j];
			}
		}
		
		return resultMat;
	}

	
	public static double[][] Multiplication(double[][] A, double[][] B) {
		
		double[][] resultMat = new double[A.length][B[0].length];
		
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<B[0].length; j++) {
				double sum = 0;
				for(int k=0; k<A[i].length; k++) {
					sum += A[i][k]*B[k][j];
				}
				resultMat[i][j] = sum;
			}
		}
		
		return resultMat;
	}
	
	public static void printmatrix(double[][] mat) {
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat[i].length; j++) {
				System.out.print(mat[i][j]);
				if(j<mat[i].length-1) {
					System.out.print(", ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
