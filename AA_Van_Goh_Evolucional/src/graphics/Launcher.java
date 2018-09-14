package graphics;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import logic.Factory;

public class Launcher {

	public static void main(String[] args) {

		for(int i = 0; i < 30;i++) {
	        System.out.println(i%3);		
		}
		
	}
}
