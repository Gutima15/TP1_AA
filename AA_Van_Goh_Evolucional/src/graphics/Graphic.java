package graphics;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Graphic extends JPanel {

	private int x;
	private int y;
	
	public Graphic(int x,int y,ArrayList<Integer> results) {
        super();
        this.x = x;
        this.y = y;
        setBounds(0,0,x,y);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.GREEN);
		g2.drawLine(10, y-10, 10, 10);
		//g2.drawLine(9, y-10, 9, 10);
		//g2.drawLine(8, y-10, 8, 10);
		g2.drawLine(10, y-30, 1000, y-30);
	}

}
