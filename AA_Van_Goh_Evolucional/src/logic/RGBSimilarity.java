package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class RGBSimilarity extends SimilitaryAlgorithm{
    
	public Double calculate(BufferedImage goalImage,BufferedImage image) {
		double sum = 0.0;
		double width = image.getWidth();
		double height = image.getHeight();	
		for(int i = 0;i<width;i++) {
			for(int j = 0;j<height;j++) {
				if(i!=0 && i!=(width-1) && j!=0 && j!=(height-1)){
					Double N1 = getNeighboorsProm(goalImage,image,i-1,j-1);
					Double N2 = getNeighboorsProm(goalImage,image,i,j-1);
					Double N3 = getNeighboorsProm(goalImage,image,i+1,j-1);
					Double N4 = getNeighboorsProm(goalImage,image,i-1,j);
					Double N5 = getNeighboorsProm(goalImage,image,i,j);
					Double N6 = getNeighboorsProm(goalImage,image,i+1,j);
					Double N7 = getNeighboorsProm(goalImage,image,i-1,j+1);
					Double N8 = getNeighboorsProm(goalImage,image,i,j+1);
					Double N9 = getNeighboorsProm(goalImage,image,i+1,j+1);
					sum=sum+((N1+N2+N3+N4+N5+N6+N7+N8+N9)/9);
				}
				else {
					sum=sum+getNeighboorsProm(goalImage,image,i,j);	
				}
            }	
		}
		DecimalFormat df = new DecimalFormat("#.##");
        double value= Math.abs(Double.valueOf(df.format(sum/(width*height)-100)));               
        return value;
	}
	
	private Double getNeighboorsProm(BufferedImage goalImage,BufferedImage image, int i, int j) {
		Color colorP = new Color(image.getRGB(i, j));
	    Color colorQ = new Color(goalImage.getRGB(i, j));         
	    double RP = colorP.getRed();
	    double BP = colorP.getBlue();
	    double GP = colorP.getGreen();
	    double RQ = colorQ.getRed();
	    double BQ = colorQ.getBlue();
	    double GQ = colorQ.getGreen();
	    double promRed = Math.abs(RP-RQ);
        double promGreen = Math.abs(GP-GQ);
        double promBlue = Math.abs(BP-BQ);
		return (promRed+promGreen+promBlue)/3;
	}
}


















