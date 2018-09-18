package graphics;
import javax.imageio.ImageIO;
import javax.swing.*;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelPicture extends JPanel{

	private Image image;
	
	public PanelPicture() {
		setLayout(null);
		setPreferredSize(new Dimension(128,128));
		setBackground(Color.WHITE);
	}
	public PanelPicture(Image image) {
		setLayout(null);
		setPreferredSize(new Dimension(128,128));
		setBackground(Color.WHITE);
		this.image = image;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(image, 0, 0, 128, 128, 0, 0, 32, 32, this);
	}
}
