package logic;

import java.awt.Color;
import java.awt.Graphics;
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
	private ArrayList<MyImage> bestImages;
	private ArrayList<String> bestImagesNames;
	private MyImage goalImage;
	private int generations;
	private int population;
	private MainFrame mainFrame;
	private Factory factory;
	SimilitaryAlgorithm calculator;
	private int useGray;
	
	private MyImage bestImage;
	private MyImage worstImage;
	private MyImage currentImage;
	
	private ArrayList<MyImage> gen1;
	private ArrayList<MyImage> gen2;
	private ArrayList<MyImage> bestHalf;
	private ArrayList<MyImage> worstHalf;
	
	Double similarityBest;
	Double similarityWorst;
	Double result;
    String bestImageName;
    
    private File directory;
	
	public Simulation(MainFrame mainFrame,SimilitaryAlgorithm calculator, BufferedImage goalImage,int generations,int population,int useGray) {
		factory = new Factory();
		numbers = new ArrayList<Integer>();
		bestImages = new ArrayList<MyImage>();
		bestImagesNames = new ArrayList<String>();
		this.goalImage = new MyImage(goalImage,4);
		this.generations = generations;
		this.population = population;
		this.mainFrame = mainFrame;
		this.calculator = calculator;
		this.useGray = useGray;
		
		bestImage = null;
		worstImage = null;
		currentImage = null;
		
        gen1 = new ArrayList<MyImage>();
		gen2 = new ArrayList<MyImage>();
		bestHalf = new ArrayList<MyImage>();
		worstHalf = new ArrayList<MyImage>();
		
		result = 0.0;
		similarityBest = 0.0;
		similarityWorst = 100.0;
		bestImageName = "";
		
		directory = new File("C:\\Users\\josue\\Desktop\\Best Images");
	}
	public void run() {
		directory.mkdir();
		for(int k = 0; k < population; k++) {
		    gen1.add(new MyImage(factory.getRandomPicture(goalImage.getImage().getWidth(),goalImage.getImage().getHeight(),useGray),8));	
		}
		for(int i = 1; i < generations+1;i++) {
			for(int j = 0; j < population; j++) {
				currentImage = gen1.get(j);
				fillMatrix();
			    Double result = calculator.calculate(goalImage.getImage(), currentImage.getImage());
			    if(gen2.size() == 0) {
			    	similarityBest = result;
			    	bestImage = currentImage;
			    	bestImageName = "image"+j;
			    	similarityWorst = result;
			    	worstImage = currentImage;
			    	gen2.add(currentImage);
			    }
			    else if(result > similarityBest) {
			    	similarityBest = result;
			    	bestImage = currentImage;
			    	bestImageName = "image"+j;
			    	gen2.add(gen2.size()-1,currentImage);
			    }
			    else if(result < similarityWorst) {
			    	similarityWorst = result;
			    	worstImage = currentImage;
			    	gen2.add(0,currentImage);
			    }
			    else {
			    	if((result-similarityWorst)<(similarityBest-result)) {
			    		gen2.add(1,currentImage);
			    	}
			    	else {
			    		gen2.add(gen2.size()-1,currentImage);
			    	}
			    }
			}
		    for(int n = 0;n<(gen2.size()/2);n++) {
		    	worstHalf.add(gen2.get(n));
		    }
            for(int m = (gen2.size()/2);m<gen2.size();m++) {
            	bestHalf.add(gen2.get(m));
		    }
			bestImages.add(0,bestImage);
			bestImagesNames.add(0,bestImageName);
			mainFrame.updateCurrentImage(bestImage.getImage());
			mainFrame.updatePercentage(similarityBest+"%");
			mainFrame.updatePanelPictures(bestImages, bestImagesNames);
			numbers.add(similarityBest.intValue());
			mainFrame.updateGraphic(numbers);
			
			gen1 = new ArrayList<MyImage>();
			getNextGeneration();
			gen2 = new ArrayList<MyImage>();
			bestHalf = new ArrayList<MyImage>();
			worstHalf = new ArrayList<MyImage>();
			similarityBest = 0.0;
			similarityWorst = 100.0;
			bestImage = null;
			worstImage = null;
			currentImage = null;
			/*
			try{
				Thread.sleep(5000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			*/
		}
	}
	public void getNextGeneration() 
	{
		MyImage randomImage1 = null;
		MyImage randomImage2 = null;
		int r1 = 0;
		int r2 = 0;
		for(int j = 0;j<population/2;j++) {
			r1 = (int)(Math.random() * worstHalf.size());
			r2 = (int)(Math.random() * worstHalf.size());
			randomImage1 = worstHalf.get(r1);
			randomImage2 = worstHalf.get(r2);
			gen1.add(factory.crossWorstImages(randomImage1, randomImage2));
		}
		for(int i = population/2;i<(population-1);i++) {
			r1 = (int)(Math.random() * bestHalf.size());
			r2 = (int)(Math.random() * bestHalf.size());
			randomImage1 = bestHalf.get(r1);
			randomImage2 = bestHalf.get(r2);
			gen1.add(factory.crossBestImages(randomImage1, randomImage2));
		}
		gen1.add(new MyImage(factory.mutate1(worstImage.getImage()),8));
	}
	
	public void fillMatrix() {
		BufferedImage sectionCurrent = new BufferedImage((currentImage.getImage().getWidth())/8,(currentImage.getImage().getHeight())/8,BufferedImage.TYPE_INT_RGB);
		Graphics g = sectionCurrent.getGraphics();
		BufferedImage sectionGoal = new BufferedImage((goalImage.getImage().getWidth())/8,(goalImage.getImage().getHeight())/8,BufferedImage.TYPE_INT_RGB);
		Graphics g2 = sectionGoal.getGraphics();
		Double matrix[][] = new Double[8][8];
		int x;
		int y;
		int c = (currentImage.getImage().getWidth()/8)-1;
		//System.out.println("c: "+c);
		for(int i = 0; i<8;i++) {
			for(int j = 0; j<8;j++) {
				//System.out.println("SECTOR:  i: "+i+" j: "+j);
				x = ((i+1)*(currentImage.getImage().getWidth()/8)-1);
				y = ((j+1)*(currentImage.getImage().getWidth()/8)-1);
				//System.out.println("Medidas correspondientes x: "+x+" y: "+y);
				for(int m = x-c;m<=x;m++) {
					for(int n = y-c;n<=y;n++) {
						//System.out.print("Inicial  m: "+m+" n: "+n);
						g.setColor(new Color(currentImage.getImage().getRGB(m, n)));
						g.fillRect((m-(i*(c+1))), (n-(j*(c+1))), 1, 1);
						//System.out.print("    Meta  m: "+(m-(i*(c+1)))+" n: "+(n-(j*(c+1))));
						g2.setColor(new Color(goalImage.getImage().getRGB(m, n)));
						g2.fillRect(m-(i*(c+1)), n-(j*(c+1)), 1, 1);
						//System.out.println("");
					}
				}
				Double result = calculator.calculate(sectionGoal, sectionCurrent);
				matrix[i][j] = result;
				//System.out.println("RESULTADO EN i="+i+" j="+j+"  "+result);
			}	
		}
		currentImage.setMatrix(matrix);
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


















