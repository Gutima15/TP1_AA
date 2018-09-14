package graphics;

import javax.swing.JFrame;

import java.awt.Dimension;

import javax.swing.*;

public class Test extends JFrame{
    
	private JPanel miPanel;
	JScrollPane scrollPane;
	
	public Test() {
	    setTitle("CoDejaVu : JScrollPane");
	    setSize(420,250);
	    setBounds(0,0,800,500);
	    setLayout(null);
	    iniciarComponentes();
	}
	private void iniciarComponentes() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5,10,380,150);
		miPanel = new JPanel();
		miPanel.setLayout(null);
		miPanel.setPreferredSize(new Dimension(750,450));
		scrollPane.setViewportView(miPanel);
		add(scrollPane);
	}
	
}














