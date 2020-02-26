public class Step2 
{
	public static double[][] CalculateZncn(int n, int m, double[] w, double[][] N, double[][] cn)	//Zn-Cn Values are calculated
	{
		double[][] zncn = new double[n-m][2];	
		double[] wN = new double[n-m];
		for(int i=0;i<n-m;i++)
		{
			for(int j=0;j<m;j++)
			{
				wN[i]=wN[i]+(w[j]*N[j][i]);
			}
		}
		
		for(int i=0;i<n-m;i++)
		{
				zncn[i][0]=wN[i]-cn[i][0];
				zncn[i][1]=cn[i][1];
		}
		
		return zncn;
	}
	
	public static int[] FindingEnter(int n, int m, double[][] zncn)	//Deciding entering variable of the iteration
	{
		int[] returns = new int[2];
		double max=0;
		int nplace=-1;
		int enter=-1;

		System.out.println();
		System.out.println("Zn-Cn Values:");
		for(int i=0;i<n-m;i++)
		{
			System.out.println((int)zncn[i][1] + ": " + String.format("%.2f", zncn[i][0]));
			if(zncn[i][0]<0 && zncn[i][0]<max)
			{
				max=zncn[i][0];
				enter=(int)zncn[i][1];
				nplace=i;
			}
		} // this for loop finds entering variable according to their Zn-Cn values

		System.out.println();
		if(enter >= 0) 
		{
			System.out.println("ENTERING VARIABLE: "+enter);
		}
		else
		{
			System.out.println("This solution is OPTIMAL !!!");
		}

		System.out.println();
		returns[0]=enter;
		returns[1]=nplace;
		return returns;
	}

}
