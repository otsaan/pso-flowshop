/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package particleswarm;

import java.util.Scanner;

/**
 *
 * @author otm
 */
public class PSO_JSHOP {

    /*
    *coefficients donnés
    *
    */
    private double w0= 1.2;
    private double betha=0.975;
    private double c1=2;
    private double c2=2;
    private int n;//number of jobs
    private int m;//number of machines
    private int index;
    
    private double f1=0;//local best
    private double fg=0;//gloabbal best
   
    double[][] X;//randomly generated particles nx2n
    double[][] V;//vilocity matrice nx2n
    int[][] jobs;//jobs matrix that depends on X matrix nx2n
    double[][] initialJobs;//initial problem order of jobs nXn
    int[][] orders;//order of jobs
    double[][] P;//position of local bests
    double[] completionTimeVector;//completion time for each row in X
    
    double[] bestP;//best local indexes;
    double[] G;//postions of gloabal best

    public PSO_JSHOP(int  n,int m) {//jobs n*m
        this.n = n;
        this.m=m;
        initialJobs = new double[n][m];
        orders = new int[n][m];
        X= new double[2*n][n];
        V= new double[2*n][n];
        jobs =new int[2*n][n];
        P= new double[2*n][n];
        G= new double[n];
        bestP= new double[2*n];
        completionTimeVector= new double[2];
    }
    
    
    /**
     * setters and getters
     * 
     * 
     */
    
    public double getW0() {
        return w0;
    }

    public double getBetha() {
        return betha;
    }

    public double getC1() {
        return c1;
    }

    public double getC2() {
        return c2;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public double getF1() {
        return f1;
    }

    public double getFg() {
        return fg;
    }

    public void setW0(double w0) {
        this.w0 = w0;
    }

    public void setBetha(double betha) {
        this.betha = betha;
    }

    public void setC1(double c1) {
        this.c1 = c1;
    }

    public void setC2(double c2) {
        this.c2 = c2;
    }

    public void setF1(double f1) {
        this.f1 = f1;
    }

    public void setFg(double fg) {
        this.fg = fg;
    }

    public double[][] getX() {
        return X;
    }

    public double[][] getV() {
        return V;
    }

    public int[][] getJobs() {
        return jobs;
    }

    public double[][] getInitialJobs() {
        return initialJobs;
    }

    public void setInitialJobs(double[][] initialJobs) {
        this.initialJobs = initialJobs;
    }
        
    public double[][] getP() {
        return P;
    }

    public double[] getG() {
        return G;
    }

    public double[] getBestP() {
        return bestP;
    }
    
    
    /**
     * Scan the initial matrix
     * 
     */
    public void scan()
    {
        Scanner  sc = new Scanner(System.in);
        
       for(int i=0;i<this.n;i++)
       {
           for(int j=0;j<this.m;j++)
           {
              initialJobs[i][j]=sc.nextInt();
           }
       }
       
       for(int i=0;i<this.n;i++)
       {
           for(int j=0;j<this.m;j++)
           {
              orders[i][j]=sc.nextInt();
           }
       }
    }
    
    public void initialise()
    {
        
        for(int i=0;i<this.n*2;i++)
        {
            for(int j=0;j<this.n;j++)
            {
             X[i][j]=-1+2*Math.random();
             V[i][j]=-1+2*Math.random();
             P[i][j]=X[i][j];
            }
        }
        
        
    }
    
    
    public void generateXV()
    {
        
        for(int i=0;i<this.n*2;i++)
        {
            double temp;
            for(int j=0;j<this.n;j++)
            {
             temp=X[i][j];
             X[i][j]+= V[i][j];//update x for current use
             V[i][j]=w0*V[i][j]+c1*Math.random()*(P[i][j]-temp )+c2*Math.random()*(G[j]-temp);// update V for the next iteration use
            }
        }
        w0 *= betha;
        
    }
    //find permutaion of each job in jobs matrix
    public void findPermutaion()
    {
        for(int k=0;k<2*n;k++)
        {
            double[] rowX=new double[n];
            int[] index= new int[n];
            //fill in vectors
            for(int i=0;i<n;i++)
            {
               rowX[i]=X[k][i];
               index[i]=i+1;
            }
            /********************************/
            //insertion sort
            int j;
            double key1;
            int key2;
            for(int i=1 ;i<n;i++)
            {
                key1=rowX[i];
                key2=index[i];
                j=i-1;
                while(j>-1 && key1<rowX[j])
                {
                    rowX[j+1]=rowX[j];
                    index[j+1]=index[j];
                    j--;
                }
                rowX[j+1]=key1;
                index[j+1]=key2;
            }
            /******************************/
            for(int i=0;i<n;i++)
            {          
              jobs[k][i]=index[i];
               
            }
        }
    }
    
    
    
    private double C(double[][] A,int[][] O, int l,int c)
    {
        double[] maxs = new double[l];
        //initialisation
        for (int i = 0; i < l; i++) {
            maxs[i] = 0;
        }
        for (int j = 0; j < l; j++) {
            for (int i = 0; i < c; i++) {
                
                if(i==0)
                {
                 maxs[O[j][i]-1] = maxs[O[j][i]-1] + A[j][O[j][i]-1];
                }else
                {
                  maxs[O[j][i]-1] = Math.max( maxs[O[j][i]-1], maxs[O[j][i-1]-1])+A[j][O[j][i]-1];
                }
                    
            }
        }
       double cTime = 0;
       for (int i = 0; i < l; i++) {
           if(maxs[i] > cTime)
               cTime = maxs[i];
        }
       return cTime;
    }
     public double C2(double[][] A,int[][] O, int l,int c)
    {
        double[] maxs = new double[l];
        //initialisation
        for (int i = 0; i < l; i++) {
            maxs[i] = 0;
        }
        for (int j = 0; j < l; j++) {
            for (int i = 0; i < c; i++) {
                
                if(i==0)
                {
                 maxs[O[j][i]-1] = maxs[O[j][i]-1] + A[j][O[j][i]-1];
                }else
                {
                  maxs[O[j][i]-1] = Math.max( maxs[O[j][i]-1], maxs[O[j][i-1]-1])+A[j][O[j][i]-1];
                }
                    
            }
        }
//        for (int i = 0; i < l; i++) {
//            System.out.println(maxs[i]+" "); 
//        }
        double cTime=0;
        for (int i = 0; i < l; i++) {
           if(maxs[i] > cTime)
               cTime = maxs[i];
        }
       return cTime;
    }
    void completionTime()
    {
      // compute completion time for each row
      //compare it to bestP and insert the row into P if the condition is meeted and the value into best p
        double[][] A= new double[n][m];
        int[][] O= new int[n][m];
        double c=0;
        for(int i=0;i<2*n;i++)
        {
           for(int j=0;j<n;j++)
           {
               for(int k=0;k<m;k++)
               {
                   //System.out.println(jobs[i][j]);
                   A[j][k]=initialJobs[jobs[i][j]-1][k];
                   O[j][k]= orders[jobs[i][j]-1][k];
               }
               
           }
          //afficher(A, n, m);
          
          //System.out.println();
          c=C(A,O, n, m);
         // System.out.println();
         // System.out.print(c+" ");
          if(c<bestP[i])
          {
              //copying X to P
              bestP[i]=c;
              for(int j=0;j<n;j++)
              {
                  P[i][j]=X[i][j];
              }
          }
           
        }
        
    }
    
       void initialiseBestP()
    {
      // compute completion time for each row
      //compare it to bestP and insert the row into P if the condition is meeted and the value into best p
        double[][] A= new double[n][m];
        int[][] O= new int[n][m];
        double c=0;
        for(int i=0;i<2*n;i++)
        {
           for(int j=0;j<n;j++)
           {
               for(int k=0;k<m;k++)
               {
                   //System.out.println(jobs[i][j]);
                   A[j][k]=initialJobs[jobs[i][j]-1][k];
                   O[j][k]= orders[jobs[i][j]-1][k];              
               }
               
           }
          //afficher(A, n, m);
          
          //System.out.println();
          c=C(A,O,n, m);
         // System.out.println();
         // System.out.print(c+" ");
          
              //copying X to P
              bestP[i]=c;
              for(int j=0;j<n;j++)
              {
                  P[i][j]=X[i][j];
              }

           
        }
        
    }
    void findPersonaleBest()
    {
        //move to bestP from  completionTime the best
        int index=0;
        
        for(int i=0;i<2*n;i++)
        {
            if(bestP[i]<bestP[index])
            {
                index=i;
                this.index=index;
            }     
        }
       f1=bestP[index]; 
    }
    
    void findGloabalBest()
    {
       if(fg>f1 || fg==0) 
       {
           fg=f1;
           for(int i=0;i<n;i++)
           {
               G[i]=jobs[index][i];
           }
       } 
    }
    
    
    
    public void  afficher(double[][] mat,int l,int c)
    {
        for(int i=0;i<l;i++)
        {
            for(int j=0;j<c;j++)
            {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
    }
}
