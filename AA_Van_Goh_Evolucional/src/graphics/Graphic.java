package graphics;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Graphic extends JPanel {

	private int x;
	private int y;
	private ArrayList<Integer> percentages;
	private int samples;
	
	public Graphic(int y,ArrayList<Integer> results) {
        super();
        this.x = results.size() + 80;
        this.y = y;
        percentages = results;
        samples = results.size();
        setPreferredSize(new Dimension(x,y));
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.GREEN);
		g2.drawLine(40, 40, 40, y-40);    // y = 400,x = 1000+ Eje y: y-40
		g2.drawLine(39, 40, 39, y-40);
		g2.drawLine(40, y-40, x-40, y-40); //Horizontal
		g2.drawLine(40, y-39, x-40, y-39); //Horizontal
		g2.setFont(new Font("Arial",Font.BOLD,18));
		g2.drawString("Processed Samples: " + samples, 140, 30);
		for(int i = 0;i<11;i++) {
			if(i == 0) {
				g2.drawString(i+"", 24, (30*Math.abs(i-10) +66));
			}else if(i == 10) {
				g2.drawString(i*10+"", 4, (30*Math.abs(i-10) +66));
			}
			else {
				g2.drawString(i*10+"", 14, (30*Math.abs(i-10) +66));	
			}
			g2.drawLine(36, (y-40)-(i*30), 40, y-40-(i*30));
			g2.drawLine(36, (y-39)-(i*30), 40, (y-39)-(i*30));
		}
		int pX = 40;
		int pY = 0;
		for(Integer num: percentages) {
			if(pY==0) {
				pY = num;
			}
			g2.drawLine(pX, Math.abs(3*pY-300)+60, pX+1, Math.abs(3*num-300)+60);
			g2.drawLine(pX, Math.abs(3*pY-300)+60, pX+1, Math.abs(3*num-300)+60);
			pX++;
			pY = num;
		}
		g2.setPaint(Color.RED);
		g2.drawLine(40+percentages.size(), 40, 40+percentages.size(), y-40);
	}
	

}
