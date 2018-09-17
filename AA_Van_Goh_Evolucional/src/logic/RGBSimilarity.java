package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class RGBSimilarity extends SimilitaryAlgorithm{
    
	public Double calculate(BufferedImage goalImage,BufferedImage image) {
		Graphics p = image.getGraphics();
		Graphics q = goalImage.getGraphics();
		double sum = 0.0;
		double width = image.getWidth();
		double height = image.getHeight();	
		for(int i = 0;i<width;i++) {
			for(int j = 0;j<height;j++) {
                Color colorP = new Color(image.getRGB(i, j));
			    Color colorQ = new Color(goalImage.getRGB(i, j));
                            
			    double RP = colorP.getRed();
			    double BP = colorP.getBlue();
			    double GP = colorP.getGreen();
                            
			    double RQ = colorQ.getRed();
			    double BQ = colorQ.getBlue();
			    double GQ = colorQ.getGreen();
                            
			    double promRed= (((RP+RQ)/510)*100);
                            //double valueRed= Math.abs(Double.valueOf(df.format(promRed)));
			    double promGreen= (((GP+GQ)/510)*100);
                            //double valueGreen= Math.abs(Double.valueOf(df.format(promGreen)));
                double promBlue= (((BP+BQ)/510)*100);
                //double valueBlue= Math.abs(Double.valueOf(df.format(promBlue)));
                sum=sum+((promRed+promGreen+promBlue)/3);
                //System.out.println("fila: "+i+" Columna: "+j);                            
                //System.out.println("goal_R: "+RQ+" goal_G: "+GQ+" goal_B: "+BQ+"_"+" R: "+RP+" G: "+GP+" B: "+BP);
                //System.out.println(sum);
                        }	
		}
		DecimalFormat df = new DecimalFormat("#.##");
        double value= Math.abs(Double.valueOf(df.format(sum/(width*height))));               
        return value;
	}
}


















