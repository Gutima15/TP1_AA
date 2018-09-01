package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	public void getRandomPicture(int width,int height,int amount,String path) {
		for(int j = 0;j<amount;j++) {
			File fichero = new File(path+"foto"+j+".jpg");
			String formato = "jpg";
			BufferedImage imagen = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = imagen.getGraphics();
			for(int i = 0;i<width;i++) {
				for(int k = 0;k<height;k++) {
					int c1 = (int)(Math.random() * 255);
					int c2 = (int)(Math.random() * 255);
					int c3 = (int)(Math.random() * 255);
					g.setColor(new Color(c1,c2,c3));
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
	private BufferedImage imageActual;
	
	public BufferedImage abrirImagen(){
        //Creamos la variable que ser� devuelta (la creamos como null)
        BufferedImage bmp=null;
        //Creamos un nuevo cuadro de di�logo para seleccionar imagen
        JFileChooser selector=new JFileChooser();
        //Le damos un t�tulo
        selector.setDialogTitle("Seleccione una imagen");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP", "jpg", "gif", "bmp");
        selector.setFileFilter(filtroImagen);
        //Abrimos el cuadro de di�log
        int flag=selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if(flag==JFileChooser.APPROVE_OPTION){
            try {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada = selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                bmp = ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            }
                  
        }
        //Asignamos la imagen cargada a la propiedad imageActual
        imageActual=bmp;
        //Retornamos el valor
        return bmp;
    }
     
    public BufferedImage escalaGrises(){
        //Variables que almacenar�n los p�xeles
        int mediaPixel,colorSRGB;
        Color colorAux;
                 
        //Recorremos la imagen p�xel a p�xel
        for( int i = 0; i < imageActual.getWidth(); i++ ){
            for( int j = 0; j < imageActual.getHeight(); j++ ){
                //Almacenamos el color del p�xel
                colorAux=new Color(this.imageActual.getRGB(i, j));
                //Calculamos la media de los tres canales (rojo, verde, azul)
                mediaPixel=(int)((colorAux.getRed()+colorAux.getGreen()+colorAux.getBlue())/3);
                //Cambiamos a formato sRGB
                colorSRGB=(mediaPixel << 16) | (mediaPixel << 8) | mediaPixel;
                //Asignamos el nuevo valor al BufferedImage
                imageActual.setRGB(i, j,colorSRGB);
            }
        }
        //Retornamos la imagen
        return imageActual;
    }
}


















