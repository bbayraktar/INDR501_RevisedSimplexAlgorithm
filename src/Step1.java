public class Step1 
{
	public static double[] CalculateW(double[][] cb, double[][] Binv, int m)	//The method for calculating W=Cb*Binv
	{														
		double[] w= new double[m];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				w[j]=w[j]+(cb[i][0]*Binv[i][j]);
			}
		}
		return w;
	}
	public static double CalculateZ(double[] w, double[] b, int m)	//The method for calculating z=Cb*Binv*b
	{ 
		double z = 0;
		for(int i=0;i<m;i++)
		{
			z=z+(w[i]*b[i]);
		}

		return z;
	}
	public static double[] CalculateBBar(double[] b, double[][] Binv, int m)	//The method for calculating Bbar=Binv*b
	{ 
		double[] bbar = new double[m];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				bbar[i]=bbar[i]+(Binv[i][j]*b[j]);
			}
		}
		return bbar;
	}
	public static void ProvideTable(double[][] cb, double[] w, double z, double[][] Binv, double[] bbar, 
									int m, int[] basic, int maxi)	//Constructing Revised Simplex Table
	{
		System.out.println();
		System.out.println("First Table of the Iteration: ");
		for(int i=0;i<m;i++)
		{
			System.out.print((int)cb[i][1]+"\t");
		}
		
		System.out.println("  rhs");
		System.out.println("-----------------------------------------------");
		
		for(int i=0;i<m;i++)
		{
			if(maxi==0)
			System.out.print(String.format("%.2f", (-1)*w[i])+"\t");
			else
			System.out.print(String.format("%.2f", w[i])+"\t");
		}
		
		if(maxi==0)
			System.out.println("z = "+String.format("%.2f", (-1)*z));
		else
			System.out.println("z = "+String.format("%.2f", z));
		
		System.out.println("-----------------------------------------------");
		
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				System.out.print(String.format("%.2f", Binv[i][j]) + "\t");
			}
			System.out.println(basic[i]+" = "+String.format("%.2f", bbar[i]));
		}
	}
}
