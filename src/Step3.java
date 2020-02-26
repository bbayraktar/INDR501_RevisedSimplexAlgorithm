public class Step3 
{
	public static double[][] CalculateY(int m, double[][] Binv, double[][] A, int enter)	// Y matrix of the entering variable
	{																						// Binv*A[i]
		double[][] y=new double[m][2];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				y[i][0]=y[i][0]+(Binv[i][j]*A[j][enter]);
			}
			y[i][1]=enter;
		}
		return y;
	}
	
	public static void ProvideTable2(double[][] cb, double[] w, double z, double[][] Binv, double[] bbar, int m, 
									 int enter, double[][] y, double[][] zncn, int nplace, int[] basic,int maxi)
	{											//Constructing the Second Table of Iteration with En tering Variable's Y values
		System.out.println("Second Table of the Iteration: ");
		for(int i=0;i<m;i++)
		{
			System.out.print((int)cb[i][1]+"\t");
		}
		
		System.out.print(" rhs" +"\t\t");		
		System.out.println(enter);
		System.out.println("---------------------------------------------------------");
		
		for(int i=0;i<m;i++)
		{
			if(maxi==0)
			System.out.print(String.format("%.2f", (-1)*w[i])+"\t");
			else
			System.out.print(String.format("%.2f", w[i])+"\t");
		}
		if(maxi==0)
			System.out.print("z = "+String.format("%.2f", (-1)*z)+"\t");
		else
			System.out.print("z = "+String.format("%.2f", z)+"\t");
		
		System.out.println(String.format("%.2f", zncn[nplace][0]));
		System.out.println("---------------------------------------------------------");
		
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				System.out.print(String.format("%.2f", Binv[i][j]) + "\t");
			}
			System.out.print(basic[i]+" = "+String.format("%.2f", bbar[i])+"\t");
			System.out.println(String.format("%.2f", y[i][0]));
		}
	}	
	
	public static int[] MinRatio(int m, double[][] y, double[] bbar, int[] basic)	// Taking Minimum Ratio Test According to
	{																				// Bbar/y values
		int[] returns2 = new int[3];
		double[][] minrat = new double[m][2];
		int bigM=999999999;
		double min=bigM;
		int leave=-1;
		int place=-1;
		int unb=0;
		
		for(int i=0;i<m;i++)
		{
			if(y[i][0]>0)
			{
				if(bbar[i]==0) {System.out.println("There is degeneracy!");}	// If there is a 0 in bbar, there is degeneracy
				minrat[i][0]=bbar[i]/y[i][0];
				minrat[i][1]=1;
			}
			else if(y[i][0]<=0) //UNBOUNDED - If we cannot take minimum ratio test, the problem is unbounded
			{
				minrat[i][0]=0;
				minrat[i][1]=0;
			}
		}
		
		bigM=999999999;
		min=bigM;
		leave=-1;
		place=-1;
		for(int i=0;i<m;i++)
		{
			if(minrat[i][0]<min && minrat[i][1]==1)	// Finding the minimum ratio and leaving variable
			{
				min=minrat[i][0];
				leave=basic[i];
				place=i;
			}
			if(i==m-1 && min==bigM)
			{
				System.out.println();
				System.out.println("The problem is UNBOUNDED");
				System.out.println();
				unb=1;
				break;
			}
		}
		returns2[0]=leave;
		returns2[1]=place;
		returns2[2]=unb;
		if(unb==0)
		{
			System.out.println();
			System.out.println("LEAVING VARIABLE:  "+leave);
		}
		return returns2;
	}
	
}
