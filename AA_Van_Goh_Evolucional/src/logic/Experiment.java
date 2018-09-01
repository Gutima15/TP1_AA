package logic;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Experiment {

	public static void main(String[] args) {
		Factory factory = new Factory();
		//factory.getRandomPicture(32,32,100,"C:\\Users\\josue\\Dropbox\\SEMESTRE 2 2018\\Analisis de Algoritmos\\Progra1\\Pictures\\");
		try {
			BufferedImage image1 = ImageIO.read(new File("C:\\Users\\josue\\Desktop\\negra2.jpg"));
			BufferedImage image2 = ImageIO.read(new File("C:\\Users\\josue\\Desktop\\blanca2.jpg"));
			Functions functions = new Functions();
			System.out.println(functions.euclidianFunction(image1, image2));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR");
		}
	}

}
