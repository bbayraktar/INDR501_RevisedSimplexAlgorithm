// INDR 501 - Optimization
// Implemenatation of Revised Simplex Algorithm
// Osman Baturhan Bayraktar

public class RevisedSimplex_Main 
{
	public static void main(String[] args) 
	{
		// In order to begin to solving, the data must be entered here
		
		
		int maxi=1; // This variable holds if the problem is a maximization problem or a minimization problem
					// If maxi=1, this is a maximization problem
		
		// A is the constraint coefficient matrix of the problem
		// But the problem must be in standard form
		double[][] A = 	{
							{2,2,1,0},
							{1,4,0,1},
						};
		
		// b is the rhs values matrix of the constraints
		double[] b = {12,14};
		// c is the objective function coefficient of the variables
		double[] c = {1,5,0,0};
		
		int n=A[0].length; 	// number of variables
		int m=A.length; 	// number of constraints
		
		if (maxi==0) {for(int i=0;i<n;i++) {c[i]=c[i]*(-1);}}
		
		Initialization.Initialize(A,c,b,n,m,maxi);
		
	}
}

	
	
	

