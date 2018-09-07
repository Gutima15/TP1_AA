package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private int width;
	private int height;
	public Toolkit myScreen;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultValues();
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70,70,70));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(50, 50, 128, 128);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(472, 50, 128, 128);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 250, 550, 420);
		contentPane.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setBackground(Color.BLACK);
		
		JLabel lblNewLabel = new JLabel("EVOLUTION PROGRESS");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(858, 681, 251, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("GOAL IMAGE");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(50, 200, 142, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IMAGE");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(503, 204, 81, 29);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(697, 50, 600, 620);
		contentPane.add(scrollPane_1);
		
		JPanel panel_3 = new Graphic(1000,620,new ArrayList<Integer>());
		panel_3.setBackground(Color.BLACK);
		scrollPane_1.setViewportView(panel_3);
		
		
		JLabel lblAllBestCandidates = new JLabel("ALL BEST CANDIDATES FROM EACH GENERATION");
		lblAllBestCandidates.setForeground(Color.GREEN);
		lblAllBestCandidates.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAllBestCandidates.setBounds(71, 681, 513, 29);
		contentPane.add(lblAllBestCandidates);
		
		JLabel lblSimilitaryRate = new JLabel("SIMILITARY RATE");
		lblSimilitaryRate.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSimilitaryRate.setForeground(Color.CYAN);
		lblSimilitaryRate.setBounds(231, 136, 194, 42);
		contentPane.add(lblSimilitaryRate);
		
		JLabel label = new JLabel("0%");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setForeground(Color.GREEN);
		label.setBounds(303, 94, 67, 31);
		contentPane.add(label);
	}

	private void setDefaultValues() 
	{
		myScreen = Toolkit.getDefaultToolkit();
    	Dimension screenSize = myScreen.getScreenSize();
    	width = screenSize.width;
    	height = screenSize.height;
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
    	getContentPane().setLayout(null);
    	setIconImage(myScreen.getImage("icon.png"));
    	setTitle("Van Gogh Evolution");
		setBounds(0,0,width,height);
	}
}
