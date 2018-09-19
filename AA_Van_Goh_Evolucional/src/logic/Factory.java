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
	
	public BufferedImage getRandomPicture(int width,int height,int useGray) 
	{
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
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
		return image;
	}
	
	public MyImage cross8x8(MyImage myImage1,MyImage myImage2) {
		BufferedImage image1 = myImage1.getImage();
		BufferedImage image2 = myImage2.getImage();
		BufferedImage result = new BufferedImage(image1.getWidth(),image1.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		for(int i = 0;i<image1.getWidth();i++) {
			for(int k = 0;k<image1.getHeight();k++) {
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
		return new MyImage(result,4);
	}
	public MyImage cross32x32(MyImage myImage1,MyImage myImage2) {
		BufferedImage image1 = myImage1.getImage();
		BufferedImage image2 = myImage2.getImage();
		BufferedImage result = new BufferedImage(image1.getWidth(),image1.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		for(int i = 0;i<image1.getWidth();i++) {
			for(int k = 0;k<image1.getHeight();k++) {
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
		return new MyImage(result,4);
	}

	public MyImage crossBestImages(MyImage image1, MyImage image2, int sectors) 
	{
		int R = (int)(Math.random() * 2);
		int R2 = (int)(Math.random() * 5);
		if(R2 == 0) {
			if(R == 0) {
				return cross32x32(image2,image1);
			}
	        if(R == 1) {
	            return cross32x32(image1,image2);
			}	
		}
		else if(R2 == 1) {
			if(R == 0) {
				return cross8x8(image2,image1);
			}
	        if(R == 1) {
	            return cross8x8(image1,image2);
			}
		}
		else {
			if(R == 0) {
				return crossSectorized(image2,image1,sectors);
			}
	        if(R == 1) {
	            return crossSectorized(image1,image2,sectors);
			}	
		}
        return null;
	}
	public MyImage crossWorstImages(MyImage image1, MyImage image2) 
	{
		int R = (int)(Math.random() * 3);
		int R2 = (int)(Math.random() * 2);
		if(R==0) {
		    if(R2 == 0) {
		    	return cross2x2Random(image1,image2);	
		    }
            if(R2 == 1) {
            	return cross2x2Random(image2,image1);	
		    }
		}
        if(R==1) {
            if(R2 == 0) {
            	return cross4x4Random(image1,image2);
		    }
            if(R2 == 1) {
            	return cross4x4Random(image2,image1);
		    }
		}
        if(R==2) {
            if(R2 == 0) {
            	return cross32x32(image1,image2);
		    }
            if(R2 == 1) {
            	return cross32x32(image2,image1);
		    }
		}
        return null;
	}
	
	public MyImage cross4x4Random(MyImage myImage1, MyImage myImage2) 
	{
		BufferedImage image1 = myImage1.getImage();
		BufferedImage image2 = myImage2.getImage();
		BufferedImage result = new BufferedImage(image1.getWidth(),image1.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		int order = image1.getWidth()/4;
		int p = (int)(Math.random() * 3);
		//int p = 2;
		for(int i = 0; i < image1.getWidth();i++) {
			for(int j = 0; j < image1.getHeight();j++) {
				//g.setColor(new Color(image1.getRGB(i, j)));
				if((i<=7)&&(j<=7)) {//SECTOR 1------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image2.getRGB(i+order, j)));
					}
					if(p==1) {
						g.setColor(new Color(image2.getRGB(i+(3*order), j)));
					}
					if(p==2) {
						g.setColor(new Color(image1.getRGB(i+(2*order), j)));
					}
				}
				if((j<=7)&&((i>=8)&&(i<=15))) {//SECTOR 2------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image1.getRGB(i-order, j)));
					}
					if(p==1) {
						g.setColor(new Color(image1.getRGB(i+order, j)));
					}
					if(p==2) {
						g.setColor(new Color(image2.getRGB(i+(2*order), j)));
					}
				}
				if((j<=7)&&((i>=16)&&(i<=23))) {//SECTOR 3------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image2.getRGB(i+order, j)));
					}
					if(p==1) {
						g.setColor(new Color(image1.getRGB(i-(2*order), j)));
					}
					if(p==2) {
						g.setColor(new Color(image2.getRGB(i-order, j)));
					}
				}
				if((j<=7)&&((i>=24)&&(i<=31))) {//SECTOR 4------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image1.getRGB(i-order, j)));
					}
					if(p==1) {
						g.setColor(new Color(image2.getRGB(i-(2*order), j)));
					}
					if(p==2) {
						g.setColor(new Color(image1.getRGB(i-(3*order), j)));
					}
				}
				if(((j>=8)&&(j<=15))&&(i<=7)) {//SECTOR 5------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image2.getRGB(i+order, j+order)));
					}
					if(p==1) {
						g.setColor(new Color(image2.getRGB(i+(3*order), j+order)));
					}
					if(p==2) {
						g.setColor(new Color(image1.getRGB(i+(2*order), j+order)));
					}
				}
				if(((j>=8)&&(j<=15))&&((i>=8)&&(i<=15))) {//SECTOR 6------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image1.getRGB(i-order, j+order)));
					}
					if(p==1) {
						g.setColor(new Color(image1.getRGB(i+order, j+order)));
					}
					if(p==2) {
						g.setColor(new Color(image2.getRGB(i+(2*order), j+order)));
					}					
				}
				if(((j>=8)&&(j<=15))&&((i>=16)&&(i<=23))) {//SECTOR 7------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image2.getRGB(i+order, j+order)));
					}
					if(p==1) {
						g.setColor(new Color(image1.getRGB(i-(2*order), j+order)));
					}
					if(p==2) {
						g.setColor(new Color(image2.getRGB(i-order, j+order)));
					}
				}
				if(((j>=8)&&(j<=15))&&((i>=24)&&(i<=31))) {//SECTOR 8------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image1.getRGB(i-order, j+order)));
					}
					if(p==1) {
						g.setColor(new Color(image2.getRGB(i-(2*order), j+order)));
					}
					if(p==2) {
						g.setColor(new Color(image1.getRGB(i-(3*order), j+order)));
					}
				}
				if(((j>=16)&&(j<=23))&&(i<=7)) {//SECTOR 9------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image1.getRGB(i+order, j-order)));
					}
					if(p==1) {
						g.setColor(new Color(image1.getRGB(i+(3*order), j-order)));
					}
					if(p==2) {
						g.setColor(new Color(image2.getRGB(i+(2*order), j-order)));
					}
				}
				if(((j>=16)&&(j<=23))&&((i>=8)&&(i<=15))) {//SECTOR 10------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image2.getRGB(i-order, j-order)));
					}				
					if(p==1) {
						g.setColor(new Color(image2.getRGB(i+order, j-order)));
					}
					if(p==2) {
						g.setColor(new Color(image1.getRGB(i+(2*order), j-order)));
					}
				}
				if(((j>=16)&&(j<=23))&&((i>=16)&&(i<=23))) {//SECTOR 11------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image1.getRGB(i+order, j-order)));
					}
					if(p==1) {
						g.setColor(new Color(image2.getRGB(i-(2*order), j-order)));
					}
					if(p==2) {
						g.setColor(new Color(image1.getRGB(i-order, j-order)));
					}
				}
				if(((j>=16)&&(j<=23))&&((i>=24)&&(i<=31))) {//SECTOR 12------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image2.getRGB(i-order, j-order)));
					}
					if(p==1) {
						g.setColor(new Color(image1.getRGB(i-(2*order), j-order)));
					}
					if(p==2) {
						g.setColor(new Color(image2.getRGB(i-(3*order), j-order)));
					}
				}
				if(((j>=24)&&(j<=31))&&(i<=7)) {//SECTOR 13------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image1.getRGB(i+order, j)));
					}
					if(p==1) {
						g.setColor(new Color(image1.getRGB(i+(3*order), j)));
					}
					if(p==2) {
						g.setColor(new Color(image2.getRGB(i+(2*order), j)));
					}
				}
				if(((j>=24)&&(j<=31))&&((i>=8)&&(i<=15))) {//SECTOR 14------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image2.getRGB(i-order, j)));
					}		
					if(p==1) {
						g.setColor(new Color(image2.getRGB(i+order, j)));
					}
					if(p==2) {
						g.setColor(new Color(image1.getRGB(i+(2*order), j)));
					}
				}
				if(((j>=24)&&(j<=31))&&((i>=16)&&(i<=23))) {//SECTOR 15------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image1.getRGB(i+order, j)));
					}
					if(p==1) {
						g.setColor(new Color(image2.getRGB(i-(2*order), j)));
					}
					if(p==2) {
						g.setColor(new Color(image1.getRGB(i-order, j)));
					}
				}
				if(((j>=24)&&(j<=31))&&((i>=24)&&(i<=31))) {//SECTOR 16------------------------------------------------------------
					if(p==0) {
						g.setColor(new Color(image2.getRGB(i-order, j)));
					}
					if(p==1) {
						g.setColor(new Color(image1.getRGB(i-(2*order), j)));
					}
					if(p==2) {
						g.setColor(new Color(image2.getRGB(i-(3*order), j)));
					}
				}
				g.fillRect(i, j, 1, 1);
			}
		}
		return new MyImage(result,2);
	}
	
	public MyImage cross2x2Random(MyImage myImage1, MyImage myImage2) 
	{
		BufferedImage image1 = myImage1.getImage();
		BufferedImage image2 = myImage2.getImage();
		BufferedImage result = new BufferedImage(image1.getWidth(),image1.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		int midX = image1.getWidth()/2;
		int midY = image1.getHeight()/2;
		int p = (int)(Math.random() * 7);
		for(int i = 0; i < image1.getWidth();i++) {
			for(int j = 0; j < image1.getHeight();j++) {
				if(i < midX && j < midY){ // FIRST SUBSQUARE
					if(p == 0) {
						g.setColor(new Color(image1.getRGB(i+midX, j)));
					}
					if(p == 1) {
						g.setColor(new Color(image2.getRGB(i+midX, j+midY)));
					}
					if(p == 2) {
						g.setColor(new Color(image2.getRGB(i+midX, j)));
					}
					if(p == 3) {
						g.setColor(new Color(image1.getRGB(i+midX, j+midY)));
					}
					if(p == 4) {
						g.setColor(new Color(image1.getRGB(i, j+midY)));
					}
					if(p == 5) {
						g.setColor(new Color(image1.getRGB(i+midX, j+midY)));
					}
					if(p == 6) {
						g.setColor(new Color(image2.getRGB(i, j+midY)));
					}
				}
				if(i >= midX && j < midY) { // SECOND SUBSQUARE
					if(p == 0) {
						g.setColor(new Color(image1.getRGB(i-midX, j)));
					}
					if(p == 1) {
						g.setColor(new Color(image2.getRGB(i-midX, j+midY)));
					}
					if(p == 2) {
						g.setColor(new Color(image1.getRGB(i-midX, j+midY)));
					}
					if(p == 3) {
						g.setColor(new Color(image2.getRGB(i-midX, j)));	
					}
					if(p == 4) {
						g.setColor(new Color(image1.getRGB(i, j+midY)));
					}
					if(p == 5) {
						g.setColor(new Color(image2.getRGB(i, j+midY)));
					}
					if(p == 6) {
						g.setColor(new Color(image2.getRGB(i-midX, j)));
					}
				}
				if(i < midX && j >= midY) { // THIRD SUBSQUARE
					if(p == 0) {
						g.setColor(new Color(image2.getRGB(i+midX, j)));
					}
					if(p == 1) {
						g.setColor(new Color(image1.getRGB(i+midX, j-midY)));
					}
					if(p == 2) {
						g.setColor(new Color(image1.getRGB(i, j-midY)));
					}
					if(p == 3) {
						g.setColor(new Color(image1.getRGB(i+midX, j-midY)));
					}
					if(p == 4) {
						g.setColor(new Color(image2.getRGB(i+midX, j-midY)));
					}
					if(p == 5) {
						g.setColor(new Color(image2.getRGB(i, j-midY)));
					}
					if(p == 6) {
						g.setColor(new Color(image1.getRGB(i+midX, j)));
					}
				}
				if(i >= midX && j >= midY) { // FOURTH SUBSQUARE
					if(p == 0) {
						g.setColor(new Color(image2.getRGB(i-midX, j)));
					}
					if(p == 1) {
						g.setColor(new Color(image1.getRGB(i-midX, j-midY)));
					}
					if(p == 2) {
						g.setColor(new Color(image2.getRGB(i-midX, j)));
					}
					if(p == 3) {
						g.setColor(new Color(image2.getRGB(i, j-midY)));
					}
					if(p == 4) {
						g.setColor(new Color(image2.getRGB(i-midX, j-midY)));	
					}
					if(p == 5) {
						g.setColor(new Color(image1.getRGB(i-midX, j)));	
					}
					if(p == 6) {
						g.setColor(new Color(image1.getRGB(i-midX, j)));	
					}
                }
				g.fillRect(i, j, 1, 1);
			}
		}
		return new MyImage(result,2);
	}
	
	public MyImage crossDirected(MyImage myImage1, MyImage myImage2, MyImage myGoal) {
		BufferedImage image1 = myImage1.getImage();
		BufferedImage image2 = myImage2.getImage();
		BufferedImage goalImage = myGoal.getImage();
		BufferedImage result = new BufferedImage(image1.getWidth(),image1.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		for(int i = 0;i<image1.getWidth();i++) {
			for(int j = 0;j<image1.getHeight();j++) {
				Color colorP = new Color(image1.getRGB(i, j));
				Color colorQ = new Color(image2.getRGB(i, j));
				Color colorG = new Color(goalImage.getRGB(i, j));
			    int RP = colorP.getRed();
			    int BP = colorP.getBlue();
				int GP = colorP.getGreen();
				int RQ = colorQ.getRed();
			    int BQ = colorQ.getBlue();
				int GQ = colorQ.getGreen();
				int RG = colorG.getRed();
			    int BG = colorG.getBlue();
				int GG = colorG.getGreen();
				g.setColor(new Color(getBest(RP,RQ,RG),getBest(GP,GQ,GG),getBest(BP,BQ,BG)));
				g.fillRect(i, j, 1, 1);
			}
		}	
		return new MyImage(result,4);
	}
	private int getBest(int color1,int color2, int colorMeta) {
		if(Math.abs(colorMeta-color1)<=Math.abs(colorMeta-color2)) {
			return color1;
		}
		else {
			return color2;
		}
	}
	public MyImage crossSectorized(MyImage myImage1,MyImage myImage2, int sectors) {
		BufferedImage image1 = myImage1.getImage();
		BufferedImage image2 = myImage2.getImage();
		BufferedImage result = new BufferedImage(image1.getWidth(),image2.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		Double matrix1[][] = myImage1.getMatrix();
		Double matrix2[][] = myImage2.getMatrix();
		int x;
		int y;
		int c = (image1.getWidth()/sectors)-1;
		//System.out.println("c: "+c);
		for(int i = 0;i<sectors;i++) {
			for(int j = 0;j<sectors;j++) {
				x = ((i+1)*(image1.getWidth()/sectors)-1);
				y = ((j+1)*(image1.getWidth()/sectors)-1);
				//System.out.println("x: "+x+" y: "+y);
				for(int m = x-c;m<=x;m++) {
					for(int n = y-c;n<=y;n++) {
						//System.out.println("m: "+m+" n: "+n);
						//System.out.println("Matrix1["+i+"]["+j+"]= "+matrix1[i][j]+"  Matrix2["+i+"]["+j+"]= "+matrix2[i][j]);
						if(matrix1[i][j] >= matrix2[i][j]) {
							g.setColor(new Color(image1.getRGB(m, n)));
							g.fillRect(m, n, 1, 1);	
						}
						else {
							g.setColor(new Color(image2.getRGB(m, n)));
							g.fillRect(m, n, 1, 1);
						}
					}	
				}
				
			}	
		}
		return new MyImage(result,sectors);
	}
    
    public BufferedImage openImage()
    {
    	BufferedImage jpg = null;
        JFileChooser selector=new JFileChooser();
        selector.setDialogTitle("Seleccione una imagen");
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("PNG","png");
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
    public BufferedImage mutate(BufferedImage image1, int useGray) {
    	int c = (int)(Math.random() * 2);
    	if(c == 0) {
    		return mutate1(image1,useGray);
    	}
        if(c == 1) {
        	return mutate2(image1);
    	}
        return null;
    }
    public BufferedImage mutate3(BufferedImage image1){
        Graphics g = image1.getGraphics();
        for(int i = 0;i<32;i++) {
            for(int k = 0;k<32;k++) {
              int mutateProbability = (int)(Math.random() * 3);
              int randomGray = (int)(Math.random() * 255);
                if(mutateProbability ==0) {
                    g.setColor(new Color(randomGray, randomGray, randomGray));
                    g.fillRect(i, k, 1, 1);
		        }
	        }
        }
        return image1;
    }
    public BufferedImage mutate2 (BufferedImage image1){ //This mutation makes the transpose of the image 
        BufferedImage result = new BufferedImage(32, 32,BufferedImage.TYPE_INT_RGB);
        Graphics g = result.getGraphics(); 
        for(int i = 0;i<32;i++) {
            for(int k = 0;k<32;k++) {
                g.setColor(new Color(image1.getRGB(k, i)));
                g.fillRect(i, k, 1, 1);
	        }
        }
        return result;
    }
    public BufferedImage mutate1(BufferedImage image1, int useGray){
        Graphics g = image1.getGraphics();
        if(useGray == 0) {
	        for(int i = 0;i<image1.getWidth();i++) {
	            for(int k = 0;k<image1.getHeight();k++) {
	              int mutateProbability = (int)(Math.random()*4);
	              int randomRGB = (int)(Math.random()*255);
	                if(mutateProbability ==0) { //si es 1, se pasa a grises
	                    g.setColor(new Color(randomRGB, randomRGB, randomRGB));
	                    g.fillRect(i, k, 1, 1);
			        }
	                if(mutateProbability ==1) { //si es 2, se cambia el azul
	                    Color colorP = new Color(image1.getRGB(i, k));
	                    g.setColor(new Color(colorP.getRed(), colorP.getGreen(), randomRGB));
	                    g.fillRect(i, k, 1, 1);
			        }
	                if(mutateProbability ==2) { //si es 3, se cambia el rojo
	                    Color colorP = new Color(image1.getRGB(i, k));
	                    g.setColor(new Color(randomRGB, colorP.getGreen(), colorP.getBlue()));
	                    g.fillRect(i, k, 1, 1);
			        }
	                if(mutateProbability ==3) { //si es 4, se cambia el verde
	                    Color colorP = new Color(image1.getRGB(i, k));
	                    g.setColor(new Color(colorP.getRed(), randomRGB, colorP.getBlue()));
	                    g.fillRect(i, k, 1, 1);
			        }
		        }
	        }
        }
        else {
        	return mutate3(image1);
        }
        return image1;
    }
}


















