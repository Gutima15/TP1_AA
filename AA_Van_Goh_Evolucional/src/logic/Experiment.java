package logic;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Experiment {

	public static void main(String[] args) {
		Beginner beginner = new Beginner();
		beginner.start();
	}

}

class Beginner extends Thread{
	
	public void run() {
		
		for(int i = 0; i < 30;i++) {
			System.out.println(i);
			
			try{
				Thread.sleep(300);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("End");
		
	}
}