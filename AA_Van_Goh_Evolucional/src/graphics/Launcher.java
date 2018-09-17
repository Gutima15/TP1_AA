package graphics;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import logic.*;

public class Launcher {

	public static void main(String[] args) {
        Factory factory = new Factory();
		BufferedImage img1 = factory.searchImage("C:\\Users\\josue\\Desktop\\4x4_1.png");
		BufferedImage img2 = factory.searchImage("C:\\Users\\josue\\Desktop\\4x4_2.png");
		MyImage myimage1 = new MyImage(img1,4);
		MyImage myimage2 = new MyImage(img2,4);
		BufferedImage img3 = factory.cross8x8(myimage1,myimage2).getImage();
		File fichero = new File("C:\\Users\\josue\\Desktop\\result.png");
		try {
			ImageIO.write(img3, "png",fichero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//SimilitaryAlgorithm calculator = new Euclidian();
	    //System.out.println(calculator.calculate(img1, img2));
		
	}
}




















