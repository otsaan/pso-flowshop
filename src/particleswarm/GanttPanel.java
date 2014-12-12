package particleswarm;
import java.awt.*;
import javax.swing.*;

public class GanttPanel extends JPanel {
        
        private double minTime;
        private int nbMach;
        private int nbTach;
	final int pushX = 40; 
	final int pushY = 45; 
	String[] order;
        double[] machineTime; 
        double[][] matr; 
	
	public GanttPanel (double tempsMin, int nbMach, int nbTach, double mat[][], String ordre) {
		setBackground(Color.white);
                minTime = tempsMin;
                this.nbMach = nbMach;
                this.nbTach = nbTach;
                
                matr = new double[nbTach][nbMach];
                String[] order = ordre.split(" ");
                
                
                int[] ints = new int[order.length];
                for (int i=0; i < order.length; i++) {
                    ints[i] = Integer.parseInt(order[i]);
                }
                     
                for(int i = 0 ; i < nbTach ; i++){
                    int l = ints[i]-1;
                    for(int j = 0; j < nbMach; j++){
                        System.out.println(mat[l][j]);
                        matr[i][j] = mat[l][j];
                    }
                }
	}
	
	private void jobRect(Graphics g, int i, int j, double wx, double h, double start) {
		double length = matr[j][i];
		g.fillRect(pushX + (int)Math.round(start * wx), 
				   pushY + (int)(i * h), 
				   (int)Math.round(length * wx), 
				   (int)h - 4);
		machineTime[i] = start + length;
	}
	
        @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		
		int w = getWidth() - pushX - 10;
		int h = getHeight() - pushY - 10;
		
		int machineCount = nbMach;
		int jobCount = nbTach;
		
		double boxWidthX = w / (minTime + minTime * 0.1);
		double boxHeight = h / machineCount;

		g.setColor(new Color(0, 0, 0));
		g.drawString("Nombre de machines: " + nbMach + "   Nombre de tâches: " + nbTach + ".    Temps: " + Double.toString(minTime), pushX, 15);
		
		//Cadre
		g.setColor(new Color(0, 0, 255));
		g.drawRect(pushX, pushY, w, h);

		g.setColor(new Color(0, 0, 0));
		for (int i = 0; i < machineCount; i++) 
			g.drawString("M" + (i+1), 5, pushY + (int)Math.round(h * (i+0.5) / machineCount));
			
		for (int i = 0; i < jobCount; i++) 
			g.drawString("T" + (i+1), pushX + i * 25, pushY-5);
			

		machineTime = new double[machineCount];
		
		g.setColor(chooseColor(0, jobCount));
		jobRect(g, 0, 0, boxWidthX, boxHeight, 0);
		for (int i = 1; i < machineCount ; i++) {
			jobRect(g, i, 0, boxWidthX, boxHeight, machineTime[i-1]);
		}
		for (int j = 1; j < jobCount; j++) {
			g.setColor(chooseColor(j, jobCount));
			jobRect(g, 0, j, boxWidthX, boxHeight, machineTime[0]);
			for (int i = 1; i < machineCount; i++) {
				jobRect(g, i, j, boxWidthX, boxHeight, Math.max(machineTime[i-1], machineTime[i]));
			}
		}
		
	}	
	
	private Color chooseColor(int i, int max) {
		int palleteLength = 1530;
		int at = (i * 275) % palleteLength;
		if (at < 256) 
			return new Color(0, 0, at);
		if (at < 2*255+1) 
			return new Color(0, at-255, 255);
		if (at < 3*255+1) 
			return new Color(0, 255, 3*255-at);
		if (at < 4*255+1) 
			return new Color(at-3*255, 255, 0);
		if (at < 5*255+1) 
			return new Color(255, 4*255+255-at, 0);
		if (at < 6*255+1) 
			return new Color(255, 0, at-5*255);
		return new Color(0, 0, 0);
	}
}
