public class RevisedSimplex {
	
	public static void RSimplex(double[][] A,double[] b, double[] c, int n, int m, int[] basic, int[] nonbasic, 
			 					double[][] B, double[][] N,double[][] Binv, double[][] cb, double[][] cn, int maxi) 
	{
		double[] w= new double[m];				// This is simply the w=cb*Binv matrix
		double z = 0;							// Objective Function Value
		double[] bbar = new double[m];			// RHS values in new basis
		double[][] zncn = new double[n-m][2];	// Zn-Cn Values of the iteration
		int[] returns  = new int[2];
		int[] returns2 = new int[3];
		int enter=-1;							// Entering Variable
		double[][] y=new double[m][2];			// y matrix of entering variable
		int leave=-1;							// Leaving Variable
		int place=-1;							// The index of Leaving Variable in the Basis Matrix that entering variable will be placed
		int nplace=-1;							// The index of Entering Variable in the Matrix that entering variable will be out from
		int unb=0;								// If the problem is unbounded this takes the value of 0
		
		int counter=0;							// Counter counts the iterations
		while(1==1)								// Revised Simplex steps start here - Further instructions are given in the Steps Classes
		{	
			System.out.println("----------------"+" BEGINNING OF ITERATION "+(counter+1)+" ------------------");
			// STEP-1
			w=Step1.CalculateW(cb, Binv, m);
			z=Step1.CalculateZ(w,b,m);
			bbar=Step1.CalculateBBar(b,Binv,m);
				Step1.ProvideTable(cb,w,z,Binv,bbar,m,basic,maxi);
			
			//STEP-2
			zncn=Step2.CalculateZncn(n, m, w, N, cn);
			returns=Step2.FindingEnter(n, m, zncn);
			enter=returns[0];
			nplace=returns[1];
			if(enter < 0) 
			{
				System.out.println("The optimal objective function value (z): " + String.format("%.2f", z));
				System.out.println("The values of basic variables:");
				for(int i=0; i<m;i++)
				{
					System.out.println("Variable "+basic[i]+": " +String.format("%.2f", bbar[i]) + "\t");
				}
				break;
			}
			
			// STEP-3
			y=Step3.CalculateY(m,Binv,A,enter);
				Step3.ProvideTable2(cb,w,z,Binv,bbar,m,enter,y,zncn,nplace,basic,maxi);
			returns2=Step3.MinRatio(m, y, bbar, basic);
			leave=returns2[0];
			place=returns2[1];
			unb=returns2[2];
			if(unb==1) {break;}
			
			// STEP-4
			Binv=Step4.CalculateNewBinv(m, place, y, Binv);
			basic[place]=enter;
			nonbasic[nplace]=leave;
				Step4.NewVariables(n, m, basic, nonbasic);
			cb=Step4.NewCB(m, basic, c);
			cn=Step4.NewCN(n, m, nonbasic, c);
			B=Step4.NewB(m, A, basic);
			N=Step4.NewN(n, m, A, nonbasic);		
			
			z=0;
			for(int i=0;i<m;i++)
			{
				bbar[i]=0;
				w[i]=0;
				y[i][0]=0;
				y[i][1]=0;
			}
			
			
			counter++;
			System.out.println("----------------"+" END OF ITERATION "+counter+" ------------------------");
			System.out.println();
		}
	}

}
