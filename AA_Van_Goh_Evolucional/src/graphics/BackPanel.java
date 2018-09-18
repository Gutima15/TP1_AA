package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackPanel extends JPanel{

	private Image image;
	
	public BackPanel() {
		super();
		searchImages();
	}
	public void searchImages() 
	{
		try {
			image = ImageIO.read(new File("background.jpg"));
		}
		catch(IOException e) {
			//System.out.println("Imagen no encontrada");
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 0, 0, null);
		g.fillRect(684, 173, 158, 26);
		g.fillRect(67, 681, 508, 29);
		g.fillRect(684, 205, 142, 26);
		g.fillRect(853, 681, 251, 36);
		g.fillRect(1084, 216, 113, 23);
		g.fillRect(1084, 50, 205, 51);
	}
}
