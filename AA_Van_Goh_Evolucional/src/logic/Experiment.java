package logic;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Experiment {

	public static void main(String[] args) {
		
		SimilitaryAlgorithm calculator = new Euclidian();
		//factory.getRandomPicture(32,32,100,"C:\\Users\\josue\\Dropbox\\SEMESTRE 2 2018\\Analisis de Algoritmos\\Progra1\\Pictures\\");
		try {
			BufferedImage image1 = ImageIO.read(new File("C:\\Users\\josue\\Desktop\\goal.jpg"));
			BufferedImage image2 = ImageIO.read(new File("C:\\Users\\josue\\Desktop\\negra.jpg"));
			System.out.println(calculator.calculate(image1, image2));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR");
		}
		/*
		File directory;
		for(int i = 1;i<101;i++) {
			directory = new File("C:\\Users\\josue\\Documents\\PRUEBAS_VAN_GOGH\\Gen"+i);
			directory.mkdir();
		}
		*/
	}

}
