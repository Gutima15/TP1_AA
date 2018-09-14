package logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import graphics.Graphic;
import graphics.MainFrame;
import graphics.PanelPicture;

public class Simulation extends Thread{

	private ArrayList<Integer> numbers;
	private ArrayList<BufferedImage> images;
	private ArrayList<String> imageNames;
	private BufferedImage goalImage;
	private int generations;
	private int population;
	private MainFrame mainFrame;
	private Factory factory;
	SimilitaryAlgorithm calculator;
	private int useGray;
	
	public Simulation(MainFrame mainFrame,SimilitaryAlgorithm calculator, BufferedImage goalImage,int generations,int population,int useGray) {
		factory = new Factory();
		numbers = new ArrayList<Integer>();
		images = new ArrayList<BufferedImage>();
		imageNames = new ArrayList<String>();
		this.goalImage = goalImage;
		this.generations = generations;
		this.population = population;
		this.mainFrame = mainFrame;
		this.calculator = calculator;
		this.useGray = useGray;
	}
	public void run() {
		BufferedImage currentImage = null;
		BufferedImage actualImage = null;
		Double similarity = 0.0;
        String currentImageName = "";
		File directory = new File("C:\\Users\\josue\\Documents\\PRUEBAS_VAN_GOGH\\Gen0");
		directory.mkdir();
		factory.getRandomPictures(32, 32, population, "C:\\Users\\josue\\Documents\\PRUEBAS_VAN_GOGH\\Gen0\\",useGray);
		for(int i = 1; i < generations+1;i++) {
			for(int j = 0; j < population; j++) {
			    actualImage = searchImage("C:\\Users\\josue\\Documents\\PRUEBAS_VAN_GOGH\\Gen"+(i-1)+"\\image"+j+".jpg");
			    Double result = calculator.calculate(goalImage, actualImage);
			    if(result > similarity) {
			    	similarity = result;
			    	currentImage = actualImage;
			    	currentImageName = "image"+j;
			    }
			}
			images.add(0,currentImage);
			imageNames.add(currentImageName);
			mainFrame.updateCurrentImage(currentImage);
			mainFrame.updatePercentage(similarity+"%");
			mainFrame.updatePanelPictures(images, imageNames);
			numbers.add(similarity.intValue());
			mainFrame.updateGraphic(numbers);
			getNextGeneration(i);
			similarity = 0.0;
			currentImage = null;
			actualImage = null;
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void getNextGeneration(int num) {
		File directory = new File("C:\\Users\\josue\\Documents\\PRUEBAS_VAN_GOGH\\Gen"+num);
		directory.mkdir();
		BufferedImage randomImage1 = null;
		BufferedImage randomImage2 = null;
		BufferedImage resultImage = null;
		int r1 = 0;
		int r2 = 0;
		for(int j = 0;j<population;j++) {
			r1 = (int)(Math.random() * population);
			r2 = (int)(Math.random() * population);
			randomImage1 = searchImage("C:\\Users\\josue\\Documents\\PRUEBAS_VAN_GOGH\\Gen"+(num-1)+"\\image"+r1+".jpg");
			randomImage2 = searchImage("C:\\Users\\josue\\Documents\\PRUEBAS_VAN_GOGH\\Gen"+(num-1)+"\\image"+r2+".jpg");
			if(useGray == 1) {
				resultImage = factory.crossGrayImages(randomImage1, randomImage2, goalImage);
			}
			else {
				resultImage = factory.crossImages(randomImage1, randomImage2, goalImage);	
			}
			try {
				File fichero = new File("C:\\Users\\josue\\Documents\\PRUEBAS_VAN_GOGH\\Gen"+num+"\\image"+j+".jpg");
				ImageIO.write(resultImage, "jpg",fichero);
			} catch (IOException e) {
				System.out.println("Error de escritura");
			}	
			
		}
	}
	public BufferedImage searchImage(String address) 
	{
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(address));
		}
		catch(IOException e) {
			System.out.println("Imagen no encontrada");
		}
		return image;
	}
}
