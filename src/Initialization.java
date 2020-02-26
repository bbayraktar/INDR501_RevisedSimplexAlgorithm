public class Initialization  {
	
	public static void Initialize(double[][] A,double[] c, double[] b, int n,int m, int maxi)
	{
		int[] basic = new int[m];			// basic is the matrix of basic variables' indices
		int[] nonbasic = new int[n-m];		// non-basic is the matrix of basic variables' indices
		double[][] B = new double[m][m];	// B is the matrix of basic variables' constraint coefficients
		double[][] Binv=new double[m][m];	// Binv is the inverse matrix of B
		double[][] N = new double[m][n-m];	// N is the matrix of non-basic variables' constraint coefficients
		double[][] cb=new double[m][2];		// cb is the matrix of basic variables' obj func coefficients
		double[][] cn=new double[n-m][2];	// cn is the matrix of non-basic variables' obj func coefficients
		
		basic		=	FindingBasis(A,n,m);
		nonbasic	=	FindingNonBasic(basic,n,m);
		B			=	B(A,basic,n,m);
		N			=	N(A,nonbasic,n,m);
		Binv		=	Binv(B,m);
		cb			=	CB(basic,c,m);
		cn			=	CN(nonbasic,c,n,m);
		
		RevisedSimplex.RSimplex(A,b,c,n,m,basic,nonbasic,B,N,Binv,cb,cn,maxi);	// The Revised Simplex Method starts with this.
	}
	
	public static int[] FindingBasis(double[][] A,int n,int m)	// This method finds the basis of a problem
	{															// in standard form according to constraint coefficients.
		int counter=0;											// Simply the cons. coefficients which forms an identity matrix
		int counter2=0;											// are the coefficients of basic variables.
		int[] basis=new int[m];
		for(int i=0 ; i<m ; i++)
		{
			for(int j=0 ; j<n ; j++)
			{
				if(A[i][j]==1)
				{
					for(int k=0;k<m;k++)
					{
						if(A[k][j]==0)
						{
							counter2++;
						}
						if(k==m-1 && counter2==m-1)
						{
						basis[counter]=j;
						counter=counter+1;
						}
					}
					counter2=0;
				}
			}
		}
		
		return basis;
	}
	
	public static int[] FindingNonBasic(int[] basic,int n,int m)		// The variables that are not in basis are
	{																	// Non-Basic Variables.
		int[] nonbasic = new int[n-m];
		int counter=0;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				if(i==basic[j])
				{
					break;
				}
				else if(j==m-1)
				{
					nonbasic[counter]=i;
					counter++;
				}
			}
		}
		
		return nonbasic;
	}
	
	public static double[][] B(double[][] A,int[] basic, int n, int m)		// The constraint coefficients of basic variables
	{																		// forms B matrix.
		double[][] Bm = new double[m][m];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				Bm[i][j]=A[i][basic[j]];
			}
		}
		
		return Bm;
	}
	
	public static double[][] N(double[][] A,int[] nonbasic, int n, int m)	// The constraint coefficients of non-basic variables
	{																		// forms N matrix.
		double[][] Na = new double[m][n-m];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n-m;j++)
			{
				Na[i][j]=A[i][nonbasic[j]];
			}
		}
		return Na;
	}
	
	public static double[][] Binv(double[][] B,int m)		// In initialization, because B is an identity matrix,
	{														// Binv is also an identity matrix.
		double[][] Binv=new double[m][m];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				Binv[i][j]=B[i][j];
			}
		}
		return Binv;
	}
	
	public static double[][] CB(int[] basic, double[] c, int m)		// The obj func coefficients of basic variables
	{																// forms cb matrix.
		double[][] cb=new double[m][2];
		for(int i=0;i<m;i++)
		{
			cb[i][0]=c[basic[i]];
			cb[i][1]=basic[i];
		}
		return cb;
	}
	public static double[][] CN(int[] nonbasic, double[] c, int n, int m)	// The obj func coefficients of non-basic variables
	{																		// forms cn matrix.
		double[][] cn=new double[n-m][2];
		for(int i=0;i<n-m;i++)
		{
			cn[i][0]=c[nonbasic[i]];
			cn[i][1]=nonbasic[i];
		}
		return cn;
	}
}
