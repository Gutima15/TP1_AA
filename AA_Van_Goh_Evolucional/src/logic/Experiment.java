package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Experiment {

	public static void main(String[] args) {
		Factory factory = new Factory();
		//factory.getRandomPicture(32,32,100,"C:\\Users\\josue\\Dropbox\\SEMESTRE 2 2018\\Analisis de Algoritmos\\Progra1\\Pictures\\");
		BufferedImage image1 = factory.abrirImagen();
		BufferedImage image2 = factory.abrirImagen();
		Functions functions = new Functions();
		System.out.println(functions.euclidianFunction(image1, image2));
	}

}
