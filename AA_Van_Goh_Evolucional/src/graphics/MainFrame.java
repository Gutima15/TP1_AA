package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Euclidian;
import logic.Experiment;
import logic.Factory;
import logic.SimilitaryAlgorithm;
import logic.Simulation;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

public class MainFrame extends JFrame {

	private int width;
	private int height;
	private boolean imageSelected;
	public Toolkit myScreen;
	private JScrollPane graphicWindow;
	private JScrollPane imagesWindow;
	private BackPanel contentPane;
	private Graphic graphic;
	private PanelPictures panelPictures;
	private JTextField gensField;
	private JTextField popField;
	private Image play;
	private JLabel labelPercentage;
	private ButtonGroup group;
	private JRadioButton buttonF1;
	private JRadioButton buttonF2;
	private JRadioButton buttonF3;
	private JButton buttonSelect;
	private JButton playButton;
	private PanelPicture panelGoalPicture;
	private PanelPicture panelCurrentPicture;
	private Factory factory;
	private JCheckBox checkBox;
	
	private BufferedImage goalImage;
	private int generations;
	private int population;
	
	private SimilitaryAlgorithm calculator;

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
	
	public MainFrame() 
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultValues();
		searchImages();
		factory = new Factory();
		imageSelected = false;
		contentPane = new BackPanel();
		contentPane.setPreferredSize(new Dimension(width,height));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		graphic = new Graphic(400,numbers);
		graphic.setBackground(new Color(9,32,85));
		
		graphicWindow = new JScrollPane();
		graphicWindow.setBounds(689, 250, 600, 420);
		graphicWindow.setViewportView(graphic);
		contentPane.add(graphicWindow);
		
		panelPictures = new PanelPictures(530,new ArrayList<BufferedImage>(),new ArrayList<String>());
		panelPictures.setBackground(new Color(9,32,85));
		
		imagesWindow = new JScrollPane();
		imagesWindow.setBounds(50, 250, 550, 420);
		imagesWindow.setViewportView(panelPictures);
		contentPane.add(imagesWindow);
		
		panelGoalPicture = new PanelPicture(null);
		panelGoalPicture.setBounds(50, 50, 128, 128);
		contentPane.add(panelGoalPicture);
		
		panelCurrentPicture = new PanelPicture(null);
		panelCurrentPicture.setBounds(472, 50, 128, 128);
		contentPane.add(panelCurrentPicture);
		
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
		
		labelPercentage = new JLabel("0%");
		labelPercentage.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelPercentage.setForeground(Color.GREEN);
		labelPercentage.setBounds(263, 94, 125, 31);
		contentPane.add(labelPercentage);
		
		playButton = new JButton(new ImageIcon(play));
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                selectCalculator();
				boolean beginner = validateInputs();
				if(beginner == true && imageSelected == true) {
					runSimulation();
				}
				else {
					JOptionPane.showMessageDialog(null,"Datos de entrada invalidos","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		playButton.setBackground(Color.GREEN);
		playButton.setBounds(689, 50, 81, 75);
		contentPane.add(playButton);
		
		group = new ButtonGroup();
		
		buttonF1 = new JRadioButton("EUCLIDIAN");
		buttonF1.setForeground(Color.GREEN);
		buttonF1.setFont(new Font("Tahoma", Font.BOLD, 20));
		buttonF1.setBounds(1084, 102, 142, 23);
		buttonF1.setBackground(new Color(50,50,50));
		buttonF1.setSelected(true);
		contentPane.add(buttonF1);
		
		buttonF2 = new JRadioButton("ALGORITHM 2");
		buttonF2.setForeground(Color.GREEN);
		buttonF2.setFont(new Font("Tahoma", Font.BOLD, 20));
		buttonF2.setBounds(1084, 150, 205, 23);
		buttonF2.setBackground(new Color(50,50,50));
		contentPane.add(buttonF2);
		
		buttonF3 = new JRadioButton("ALGORITHM 3");
		buttonF3.setForeground(Color.GREEN);
		buttonF3.setFont(new Font("Tahoma", Font.BOLD, 20));
		buttonF3.setBounds(1084, 195, 205, 23);
		buttonF3.setBackground(new Color(50,50,50));
		contentPane.add(buttonF3);
		
		group.add(buttonF1);
		group.add(buttonF2);
		group.add(buttonF3);
		
		JLabel lblNewLabel_3 = new JLabel("GENERATIONS");
		lblNewLabel_3.setForeground(Color.GREEN);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(689, 170, 158, 32);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("POPULATION");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setForeground(Color.GREEN);
		lblNewLabel_4.setBounds(689, 197, 142, 42);
		lblNewLabel_4.setBackground(Color.BLACK);
		contentPane.add(lblNewLabel_4);
		
		
		gensField = new JTextField();
		gensField.setFont(new Font("Tahoma", Font.BOLD, 20));
		gensField.setBounds(858, 172, 81, 29);
		contentPane.add(gensField);
		
		popField = new JTextField();
		popField.setBounds(858, 208, 81, 29);
		popField.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(popField);
		
		buttonSelect = new JButton("SELECT");
		buttonSelect.setForeground(Color.GREEN);
		buttonSelect.setBackground(new Color(50,50,50));
		buttonSelect.setFont(new Font("Tahoma", Font.BOLD, 20));
		buttonSelect.setBounds(53, 16, 125, 23);
		
		buttonSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goalImage = factory.openImage();
				panelGoalPicture = new PanelPicture(goalImage);
				contentPane.add(panelGoalPicture);
				panelGoalPicture.setBounds(50, 50, 128, 128);
				imageSelected = true;
				
			}
		});
		
		contentPane.add(buttonSelect);
		
		checkBox = new JCheckBox("USE GRAY SCALE");
		checkBox.setForeground(Color.CYAN);
		checkBox.setBackground(new Color(50,50,50));
		checkBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		checkBox.setBounds(1084, 50, 205, 23);
		contentPane.add(checkBox);
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
	
	public void searchImages() 
	{
		try {
			play = ImageIO.read(new File("play.png"));
		}
		catch(IOException e) {
			System.out.println("Imagen no encontrada");
		}
	}
	
	private boolean validateInputs() 
	{
		String gens = gensField.getText();
		String pop = popField.getText();
		if(isNumeric(gens) == true && isNumeric(pop) == true) {
			int g = Integer.parseInt(gens);
			int p = Integer.parseInt(pop);
			if(g > 0 && p > 0) {
				generations = Integer.parseInt(gens);
				population = Integer.parseInt(pop);
				return true;	
			}
			else {
				return false;
			}
		}
		else {
		    return false;	
		}
	}
	
	private void selectCalculator() 
	{
		if(buttonF1.isSelected() == true){
		    calculator = new Euclidian();	
		}
		if(buttonF1.isSelected() == true){
		    calculator = new Euclidian();	
		}
		if(buttonF1.isSelected() == true){
		    calculator = new Euclidian();	
		}
	}
	
	public boolean isNumeric(String chain) 
	{

        boolean result;

        try {
            Integer.parseInt(chain);
            result = true;
        } catch (NumberFormatException e) {
            result = false;
        }

        return result;
    }
	
	public void runSimulation() {
		int useGray = 0;
		if(checkBox.isSelected() == true) {
			useGray = 1;
		}
		Simulation simulation = new Simulation(this,calculator,goalImage,generations,population,useGray);
		simulation.start();
	}
	public BufferedImage searchImage(String address) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(address));
		}
		catch(IOException e) {
			System.out.println("Imagen no encontrada");
		}
		return image;
	}
	public void updateCurrentImage(BufferedImage currentImage) {
		panelCurrentPicture = new PanelPicture(currentImage);
		contentPane.add(panelCurrentPicture);
		panelCurrentPicture.setBounds(472, 50, 128, 128);
	}
	public void updatePercentage(String value) {
		labelPercentage.setText(value);
	}
	public void updateGraphic(ArrayList<Integer> numbers) {
		graphic = new Graphic(400,numbers);
		graphic.setBackground(new Color(9,32,85));
		graphicWindow.setViewportView(graphic);
	}
	public void updatePanelPictures(ArrayList<BufferedImage> images,ArrayList<String> names) {
		panelPictures = new PanelPictures(530,images,names);
		panelPictures.setBackground(new Color(9,32,85));
		imagesWindow.setViewportView(panelPictures);
	}
}





























