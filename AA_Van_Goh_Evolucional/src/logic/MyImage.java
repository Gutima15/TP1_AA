package logic;

import java.awt.image.BufferedImage;

public class MyImage {

	private BufferedImage image;
	private Double [][] matrix;
	
	public MyImage(BufferedImage image,int order) {
		this.image = image;
		matrix = new Double[order][order];
		for(int i = 0;i<order;i++) {
			for(int j = 0;j<order;j++) {
				matrix[i][j] = 0.0;
			}	
		}
	}
	public BufferedImage getImage() {
		return image;
	}
	public Double[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(Double[][] matrix) {
		this.matrix = matrix;
	}
}
