package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class Functions {

	public double euclidianFunction(BufferedImage goalImage,BufferedImage image) {
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
				sum = sum + ((Math.pow(promP-promQ, 2))/(width*height));
			    System.out.println("B:"+BP+" R:"+RP+" G:"+GP+"   B:"+BQ+" R:"+RQ+" G:"+GQ +" PromP: "+promP+" PromQ: "+promQ);
			    
			}	
		}
		System.out.println("x: "+width +" y: "+height);
		System.out.println("");
		double square = Math.sqrt(sum);
		double result = (((square*100)/255)-100);
		
		DecimalFormat df = new DecimalFormat("#.##");   
		
		return Math.abs(Double.valueOf(df.format(result)));
		//return square;
	}
}
