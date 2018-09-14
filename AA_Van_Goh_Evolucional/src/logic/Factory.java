package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Factory {
	
	public void getRandomPictures(int width,int height,int amount,String path,int useGray) {
		for(int j = 0;j<amount;j++) {
			File fichero = new File(path+"image"+j+".jpg");
			String formato = "jpg";
			BufferedImage imagen = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = imagen.getGraphics();
			for(int i = 0;i<width;i++) {
				for(int k = 0;k<height;k++) {
					if(useGray == 1) {
						int c4 = (int)(Math.random() * 255);
						g.setColor(new Color(c4,c4,c4));
					}
					else {
						int c1 = (int)(Math.random() * 255);
						int c2 = (int)(Math.random() * 255);
						int c3 = (int)(Math.random() * 255);
						g.setColor(new Color(c1,c2,c3));	
					}
					g.fillRect(i, k, 1, 1);
				}	
			}
			try {
				ImageIO.write(imagen, formato, fichero);
			} catch (IOException e) {
				System.out.println("Error de escritura");
			}	
		}
		
	}
	
	public BufferedImage crossImages8x8(BufferedImage image1,BufferedImage image2) {
		BufferedImage result = new BufferedImage(32, 32,BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		for(int i = 0;i<32;i++) {
			for(int k = 0;k<32;k++) {
				
				if((i<=3)||(i>=8 && i<=11)||(i>=16 && i<=19)||(i>=24 && i<=27)) {
					if((k<=3)||(k>=8 && k<=11)||(k>=16 && k<=19)||(k>=24 && k<=27)) {
						g.setColor(new Color(image1.getRGB(i, k)));
					}
					else {
						g.setColor(new Color(image2.getRGB(i, k)));
					}
					g.fillRect(i, k, 1, 1);
				}
				
				if((i>=4 && i<=7)||(i>=12 && i<=15)||(i>=20 && i<=23)||(i>=28)) {
					if((k>=4 && k<=7)||(k>=12 && k<=15)||(k>=20 && k<=23)||(k>=28)) {
						g.setColor(new Color(image1.getRGB(i, k)));
					}
					else {
						g.setColor(new Color(image2.getRGB(i, k)));
					}
					g.fillRect(i, k, 1, 1);
				}
			}	
		}
		return result;
	}
	public BufferedImage crossGrayImages8x8(BufferedImage image1,BufferedImage image2) {
		BufferedImage result = new BufferedImage(32, 32,BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		for(int i = 0;i<32;i++) {
			for(int k = 0;k<32;k++) {
				
				if((i<=3)||(i>=8 && i<=11)||(i>=16 && i<=19)||(i>=24 && i<=27)) {
					if((k<=3)||(k>=8 && k<=11)||(k>=16 && k<=19)||(k>=24 && k<=27)) {
						g.setColor(new Color(image1.getRGB(i, k)));
					}
					else {
						g.setColor(new Color(image2.getRGB(i, k)));
					}
					g.fillRect(i, k, 1, 1);
				}
				
				if((i>=4 && i<=7)||(i>=12 && i<=15)||(i>=20 && i<=23)||(i>=28)) {
					if((k>=4 && k<=7)||(k>=12 && k<=15)||(k>=20 && k<=23)||(k>=28)) {
						g.setColor(new Color(image1.getRGB(i, k)));
					}
					else {
						g.setColor(new Color(image2.getRGB(i, k)));
					}
					g.fillRect(i, k, 1, 1);
				}
			}	
		}
		return result;
	}
	public BufferedImage crossImages32x32(BufferedImage image1,BufferedImage image2) {
		BufferedImage result = new BufferedImage(32, 32,BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		for(int i = 0;i<32;i++) {
			for(int k = 0;k<32;k++) {
				if(i%2 == 0) {
					if(k%2 == 0) {
						g.setColor(new Color(image1.getRGB(i, k)));
					}
					else {
						g.setColor(new Color(image2.getRGB(i, k)));
					}
					g.fillRect(i, k, 1, 1);
				}
				if(i%2 == 1) {
					if(k%2 == 1) {
						g.setColor(new Color(image1.getRGB(i, k)));
					}
					else {
						g.setColor(new Color(image2.getRGB(i, k)));
					}
					g.fillRect(i, k, 1, 1);
				}
			}	
		}
		return result;
	}
	public BufferedImage crossGrayImages32x32(BufferedImage image1,BufferedImage image2) {
		BufferedImage result = new BufferedImage(32, 32,BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		for(int i = 0;i<32;i++) {
			for(int k = 0;k<32;k++) {
				if(i%2 == 0) {
					if(k%2 == 0) {
						g.setColor(new Color(image1.getRGB(i, k)));
					}
					else {
						g.setColor(new Color(image2.getRGB(i, k)));
					}
					g.fillRect(i, k, 1, 1);
				}
				if(i%2 == 1) {
					if(k%2 == 1) {
						g.setColor(new Color(image1.getRGB(i, k)));
					}
					else {
						g.setColor(new Color(image2.getRGB(i, k)));
					}
					g.fillRect(i, k, 1, 1);
				}
			}	
		}
		return result;
	}
	public BufferedImage crossGrayImages(BufferedImage image1, BufferedImage image2,BufferedImage goalImage) {
		BufferedImage result = new BufferedImage(32, 32,BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		for(int i = 0;i<32;i++) {
			for(int k = 0;k<32;k++) {
				Color color1 = new Color(image1.getRGB(i, k));
				Color color2 = new Color(image2.getRGB(i, k));
				Color colorG = new Color(goalImage.getRGB(i, k));
				int dif1 = Math.abs(color1.getRGB()-colorG.getRGB());
				int dif2 = Math.abs(color2.getRGB()-colorG.getRGB());
				if(dif1<=dif2) {
					g.setColor(new Color(image1.getRGB(i,k)));
				}
				else {
					g.setColor(new Color(image2.getRGB(i,k)));
				}
				g.fillRect(i, k, 1, 1);
			}
		}
		return result;
	}
	/*
	public BufferedImage crossImages(BufferedImage image1, BufferedImage image2,int useGray) {
		int r1 = (int)(Math.random() * 2);
		int r2 = (int)(Math.random() * 2);
		if(useGray == 1) {
			if(r1 == 0) {
				if(r2 == 0) {
					return crossGrayImages8x8(image1,image2);		
				}
				if(r2 == 1) {
					return crossGrayImages32x32(image1,image2);
				}
			}
			if(r1 == 1) {
				if(r2 == 0) {
					return crossGrayImages8x8(image2,image1);		
				}
				if(r2 == 1) {
					return crossGrayImages32x32(image2,image1);
				}
			}
		}
		else {
			if(r1 == 0) {
				if(r2 == 0) {
					return crossImages8x8(image1,image2);		
				}
				if(r2 == 1) {
					return crossImages32x32(image1,image2);
				}
			}
			if(r1 == 1) {
				if(r2 == 0) {
					return crossImages8x8(image2,image1);		
				}
				if(r2 == 1) {
					return crossImages32x32(image2,image1);
				}
			}
		}
		return null;
	}
	*/
	public BufferedImage crossImages(BufferedImage image1, BufferedImage image2,BufferedImage goalImage) {
		BufferedImage result = new BufferedImage(32, 32,BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		for(int i = 0;i<32;i++) {
			for(int k = 0;k<32;k++) {
				Color color1 = new Color(image1.getRGB(i, k));
				Color color2 = new Color(image2.getRGB(i, k));
				Color colorG = new Color(goalImage.getRGB(i, k));
				int R1 = color1.getRed();
				int R2 = color2.getRed();
				int Rg = colorG.getRed();
				int B1 = color1.getBlue();
				int B2 = color2.getBlue();
				int Bg = colorG.getBlue();
				int G1 = color1.getGreen();
				int G2 = color2.getGreen();
				int Gg = colorG.getGreen();
				int RF = R2;
				int GF = G2;
				int BF = B2;
				if(Math.abs(R1-Rg) < Math.abs(R2-Rg)) {
					RF = R1;
				}
                if(Math.abs(B1-Bg) < Math.abs(B2-Bg)) {
                	BF = B2;
				}
                if(Math.abs(G1-Gg) < Math.abs(G2-Gg)) {
                	GF = G1;
				}
				g.setColor(new Color(RF,GF,BF));
				g.fillRect(i, k, 1, 1);
			}
		}
		return result;
	}
    public BufferedImage grayScale(BufferedImage image){
        int mediaPixel,colorSRGB;
        Color colorAux;
        for( int i = 0; i < image.getWidth(); i++ ){
            for( int j = 0; j < image.getHeight(); j++ ){
                colorAux=new Color(image.getRGB(i, j));
                mediaPixel=(int)((colorAux.getRed()+colorAux.getGreen()+colorAux.getBlue())/3);
                colorSRGB=(mediaPixel << 16) | (mediaPixel << 8) | mediaPixel;
                image.setRGB(i, j,colorSRGB);
            }
        }
        
        return image;
    }
    public BufferedImage openImage(){
    	BufferedImage jpg = null;
        JFileChooser selector=new JFileChooser();
        selector.setDialogTitle("Seleccione una imagen");
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & PNG & BMP", "jpg", "png", "bmp");
        selector.setFileFilter(filtroImagen);
        int flag=selector.showOpenDialog(null);
        if(flag==JFileChooser.APPROVE_OPTION){
            try {
                File imagenSeleccionada=selector.getSelectedFile();
                jpg = ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            }
                  
        }
        return jpg;
    }
    public BufferedImage searchImage(String address) 
	{
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(address));
		}
		catch(IOException e) {
			System.out.println("Imagen no encontrada");
		}
		return image;
	}
}


















