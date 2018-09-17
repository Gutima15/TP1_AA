package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import logic.MyImage;

public class PanelPictures extends JPanel {

	private ArrayList<MyImage> images;
	private ArrayList<String> names;
	private int x;
	private int y;
	
	public PanelPictures(int x, ArrayList<MyImage> candidates,ArrayList<String> names) {
        this.x = x;
        this.y = (1+(candidates.size()/3))*180;
        images = candidates;
        this.names = names;
        setPreferredSize(new Dimension(x,y));
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.GREEN);
		BufferedImage currentImage;
		String currentName;
		int yDraw = 20;
		g.setFont(new Font("Tahoma", Font.BOLD, 18));
		for(int n = 0;n < images.size();n++) {
			currentImage = images.get(n).getImage();
		    currentName = names.get(n);
		    int m = Math.abs(n-images.size());  
		    if(n%3 == 0) {
		    	g.drawImage(currentImage, 20, yDraw, 128, 128, null);
		    	g.drawString("gen"+m+"_"+currentName, 20, yDraw +150);
		    }
		    if(n%3 == 1) {
		    	g.drawImage(currentImage, 180, yDraw, 128, 128, null);
		    	g.drawString("gen"+m+"_"+currentName, 180, yDraw +150);
		    }
		    if(n%3 == 2) {
		    	g.drawImage(currentImage, 350, yDraw, 128, 128, null);
		    	g.drawString("gen"+m+"_"+currentName, 350, yDraw +150);
		    	yDraw = yDraw+180;
		    }
		}
	}

}
