///*
//* To change this license header, choose License Headers in Project Properties.
//* To change this template file, choose Tools | Templates
//* and open the template in the editor.
//*/
//
//package particleswarm;
//
//import java.util.Scanner;
//
///**
// *
// * @author otm
// */
//public class ParticleSwarm {
//    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println(" entrez n*m");
//        PSO pso= new PSO(sc.nextInt(),sc.nextInt());
//        
//        pso.scan();
//        
//        pso.initialise();
//        pso.findPermutaion();
//        pso.initialiseBestP();
//        pso.findPersonaleBest();
//        pso.findGloabalBest();
//        
//        System.out.print(" entrez le nombre d'iterations :");
//        int max=sc.nextInt();
//        for(int i=0;i<max;i++)
//        {
//        pso.generateXV();
//        pso.findPermutaion();
//        pso.completionTime();
//        pso.findPersonaleBest();
//        pso.findGloabalBest();  
//        }
//        System.out.println("meilleur temps : "+pso.getFg());
//        System.out.println("Solution : ");
//        
//        for(int i=0;i<pso.getN();i++)
//            System.out.print(pso.getG()[i]+" ");
//        
//        
//    }
//    
//    
//    
//    static void afficher(double[][] mat,int l,int c)
//    {
//        for(int i=0;i<l;i++)
//        {
//            for(int j=0;j<c;j++)
//            {
//                System.out.print(mat[i][j]+" ");
//            }
//            System.out.println();
//        }
//    }
//    static void afficherN(int[][] mat,int l,int c)
//    {
//        for(int i=0;i<l;i++)
//        {
//            for(int j=0;j<c;j++)
//            {
//                System.out.print(mat[i][j]+" ");
//            }
//            System.out.println();
//        }
//    }
//    
//}
