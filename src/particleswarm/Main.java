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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//     PSO_JSHOP   jShop = new PSO_JSHOP(3,3);
//     jShop.scan();
//     
//     jShop.afficher(jShop.getInitialJobs(), 3, 3);
//     System.out.println("*******************");
//      for(int i=0;i<3;i++)
//        {
//            for(int j=0;j<3;j++)
//            {
//                System.out.print(jShop.orders[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println("ii"+jShop.C2(jShop.getInitialJobs(),jShop.orders,3,3));
       
        Scanner sc = new Scanner(System.in);
        System.out.println(" entrez n*m");
        PSO_JSHOP jShop= new PSO_JSHOP(sc.nextInt(),sc.nextInt());
        
            jShop.scan();

            jShop.initialise();
            jShop.findPermutaion();
            jShop.initialiseBestP();
            jShop.findPersonaleBest();
            jShop.findGloabalBest();

            System.out.print(" entrez le nombre d'iterations :");
            int max=sc.nextInt();
            for(int i=0;i<max;i++)
            {
            jShop.generateXV();
            jShop.findPermutaion();
            jShop.completionTime();
            jShop.findPersonaleBest();
            jShop.findGloabalBest();  
            }
            System.out.println("meilleur temps : "+jShop.getFg());
            System.out.println("Solution : ");

            for(int i=0;i<jShop.getN();i++)
                System.out.print(jShop.getG()[i]+" ");


        }

    
    
}
