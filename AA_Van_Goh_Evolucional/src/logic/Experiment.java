package logic;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Experiment {

	public static void main(String[] args) {
		
		ArrayList<Integer> vector = new ArrayList<Integer>();
		ArrayList<Integer> vectorMin = new ArrayList<Integer>();
		ArrayList<Integer> vectorMax = new ArrayList<Integer>();

		vector.add(9);
		vector.add(12);
		vector.add(43);
		vector.add(93);
		vector.add(vector.size()-1,7);
		//vector.add(vector.size()-1,33);
		//vector.remove(0);
		//vector.remove(vector.size()-1);
        //vector.add((vector.size()/2),44);
		
		for(int i = 0; i < vector.size()/2;i++) {
			vectorMin.add(vector.get(i));
		}
        for(int i = vector.size()/2; i < vector.size();i++) {
			vectorMax.add(vector.get(i));
		}
		for(int j = 0;j< vector.size();j++ ) {
			System.out.print(vector.get(j)+" ");
		}
		/*
		System.out.println("");
        for(int j = 0;j< vectorMin.size();j++ ) {
			System.out.print(vectorMin.get(j)+" ");
		}
		System.out.println("");
        for(int j = 0;j< vectorMax.size();j++ ) {
			System.out.print(vectorMax.get(j)+" ");
        }
        */
        //System.out.print(vector.get(0));
        
	}

}
