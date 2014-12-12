
package particleswarm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.util.Random;
import java.util.Scanner;



/**
 *
 * @author Saad
 */

public class PDFGenerator {

     public void underRect(int[][]A, int[]a, int n, int m)throws IOException, DocumentException{ //n nombre de ligne et m nombre de colonne
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("flowshop.pdf"));
        PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream("flowshop.pdf"));
        document.open();
        Paragraph p = new Paragraph("Essaim Particule", new Font(FontFamily.HELVETICA,18));
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        Paragraph q = new Paragraph("Le résultat graphique aprés l'utilisation de l'algorithme Essaim Particule: ");
        q.setAlignment(Element.ALIGN_CENTER);
        document.add(q);
        
        PdfContentByte under = writer.getDirectContentUnder();
        int[] lastpos = new int [m];
        int[][] Color = new int [n][3];
        int[][] B = new int[n][m];
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j<3 ; j++){
                Color[i][j] = (int)(Math.random()*255);
            }
        
        for(int i = 0 ; i < n ; i++){
            int x = 150;
            under.setRGBColorFill(Color[i][0], Color[i][1], Color[i][2]);
            for(int j = 0 ; j < m ; j++){

                if(i == 0){

                    if(j==0)
                    {
                        under.rectangle(150, 650-j*15, A[i][j], 15);
                        under.fill();
                        lastpos[j] = 150+A[i][j];
                    }
                    else{
                        x+=A[i][j-1];
                        under.rectangle(x, 650-j*15, A[i][j], 15);
                        under.fill();
                        lastpos[j] = x + A[i][j];
                    }
                }
                else{
                    if( j == 0 ){
                        under.rectangle(lastpos[j], 650-j*15, A[i][j], 15);
                        under.fill();
                        lastpos[j]+=A[i][j];
                    }
                    else{
                        under.rectangle(Math.max(lastpos[j], lastpos[j-1]), 650-j*15, A[i][j], 15);
                        under.fill();
                        lastpos[j]=A[i][j]+Math.max(lastpos[j], lastpos[j-1]);
                    }                               
                }
            }
        }
        for(int i = 0 ; i < n ; i++){
            int k = a[i]-1;
            for(int j = 0; j < m; j++){
                    B[i][j] = A[k][j];
            }
        }
        
//        System.out.println("Aprés l'échange: ");        
//        for(int i = 0 ; i < n ; i++){
//            for(int j = 0; j < m; j++){
//                System.out.print(B[i][j]+"\t");
//            }
//            System.out.println();
//        }

        
        Paragraph p1 = new Paragraph("Aprés l'échange: ");
        p1.setSpacingBefore(130);
        document.add(p1);
        

        for(int i = 0 ; i < n ; i++){
            int x = 150;
            under.setRGBColorFill(Color[a[i]-1][0], Color[a[i]-1][1], Color[a[i]-1][2]);
            for(int j = 0 ; j < m ; j++){

                if(i == 0){

                    if(j==0)
                    {
                        under.rectangle(150, 550-j*15, B[i][j], 15);
                        under.fill();
                        lastpos[j] = 150+B[i][j];
                    }
                    else{
                        x+=B[i][j-1];
                        under.rectangle(x, 550-j*15, B[i][j], 15);
                        under.fill();
                        lastpos[j] = x + B[i][j];
                    }
                }
                else{
                    if( j == 0 ){
                        under.rectangle(lastpos[j], 550-j*15, B[i][j], 15);
                        under.fill();
                        lastpos[j]+=B[i][j];
                    }
                    else{
                        under.rectangle(Math.max(lastpos[j], lastpos[j-1]), 550-j*15, B[i][j], 15);
                        under.fill();
                        lastpos[j]=B[i][j]+Math.max(lastpos[j], lastpos[j-1]);
                    }                               
                }
            }
        }
        document.newPage();
        document.close();
     }
     
}
