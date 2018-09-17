package logic;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class Canberra extends SimilitaryAlgorithm{
    
	public Double calculate(BufferedImage goalImage,BufferedImage image) {
		Graphics p = image.getGraphics();
		Graphics q = goalImage.getGraphics();
		double sum = 0.0;
		double width = image.getWidth();
		double height = image.getHeight();	
		for(int i = 0;i<image.getWidth();i++) {
			for(int j = 0;j<image.getHeight();j++) {
				Color colorP = new Color(image.getRGB(i, j));
				Color colorQ = new Color(goalImage.getRGB(i, j));
			    int RP = colorP.getRed();
			    int BP = colorP.getBlue();
				int GP = colorP.getGreen();
				int RQ = colorQ.getRed();
			    int BQ = colorQ.getBlue();
				int GQ = colorQ.getGreen();
				double promP = (RP + BP + GP)/3;
				double promQ = (RQ + BQ + GQ)/3;
	            double abs =Math.abs(promP-promQ);
	            double down = promP + promQ;
	            if(down == 0) {
	            	down++;
	            }
	            sum = (sum+(abs/down));
			}	
		}
		DecimalFormat df = new DecimalFormat("#.##");   
	    double value= Math.abs(Double.valueOf((df.format((sum/(width*height)*100)-100))));
		return value;
            
	}
}
















