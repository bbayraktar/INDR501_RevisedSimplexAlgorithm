public class Step4 
{
	public static double[][] CalculateNewBinv(int m, int place, double[][] y, double[][] Binv)	// Calculating NewBinv=E*BinvOld
	{
		double[][] E = new double[m][m];
		double[][] BinvNew=new double[m][m];
		for(int i=0;i<m;i++)			//Constructing E matrix
		{
			if(i==place)
			{
				for(int j=0;j<m;j++)
				{
					if(j==place)
					{
						E[j][i]=(1/y[place][0]);
					}
					else
					{
						E[j][i]=-(y[j][0]/y[place][0]);
					}
				}
			}
			else
			{
				for(int j=0;j<m;j++)
				{
					if(i==j)
					{
						E[j][i]=1;
					}
					else
					{
						E[j][i]=0;
					}
				}
			}
		}
		
		for(int i=0;i<m;i++)	//NewBinv=E*OldBinv
		{
			for(int j=0;j<m;j++)
			{
				for(int k=0;k<m;k++)
				{
					BinvNew[i][j]=BinvNew[i][j]+(E[i][k]*Binv[k][j]);
				}
			}
		}
		
		System.out.println();
		System.out.println("NEW B INVERSE:");
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				System.out.print(String.format("%.2f", BinvNew[i][j]) + "\t");
			}
			System.out.println();
		}
		return BinvNew;
	}
	
	public static void NewVariables(int n, int m, int[] basic, int[] nonbasic)
	{

		System.out.println();System.out.println("NEW BASIC VARIABLES: ");
		for(int i=0;i<m;i++){System.out.print(basic[i]+ "\t");}
		
		System.out.println();System.out.println("NEW NON-BASIC VARIABLES");
		for(int i=0;i<n-m;i++){System.out.print(nonbasic[i]+"\t");}
		System.out.println();System.out.println();
	}
	
	//From this part Updating Process Occurs
	
	public static double[][] NewCB(int m, int[] basic, double[] c)
	{
		double[][] cb=new double[m][2];
		for(int i=0;i<m;i++)
		{
			cb[i][0]=c[basic[i]];
			cb[i][1]=basic[i];
		}
		return cb;
	}
	
	public static double[][] NewCN(int n, int m, int[] nonbasic, double[] c)
	{
		double[][] cn=new double[n-m][2];
		for(int i=0;i<n-m;i++)
		{
			cn[i][0]=c[nonbasic[i]];
			cn[i][1]=nonbasic[i];
		}
		return cn;
	}
	
	public static double[][] NewB(int m,double[][] A, int[] basic)
	{
		double[][] B=new double[m][m];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				B[i][j]=A[i][basic[j]];
			}
		}
		return B;
	}
	
	public static double[][] NewN(int n, int m, double[][] A, int[] nonbasic)
	{
		double[][] N=new double[m][n-m];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n-m;j++)
			{
				N[i][j]=A[i][nonbasic[j]];
			}
		}
		return N;
	}
}
