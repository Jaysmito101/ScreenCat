import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.*;
import java.util.concurrent.*;

public class Texture{
	private BufferedImage image;

	public static Texture FromFile(String filepath){
		try{
			return new Texture(ImageIO.read(new File(filepath)));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	public static Texture FromFile(File file){
		try{
			return new Texture(ImageIO.read(file));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	public static Texture FromURL(String imageUrl){
		try{
			URL url = new URL(imageUrl);
			return new Texture(ImageIO.read(url));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	public static Texture[] FromFiles(File[] files){
		Texture[] textures = new Texture[files.length];
		for(int i=0;i<textures.length;i++){
			try{
				textures[i] = Texture.FromFile(files[i]);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return textures;
	}

	public static Texture[] FromFiles(String[] files){
		Texture[] textures = new Texture[files.length];
		for(int i=0;i<textures.length;i++){
			try{
				textures[i] = Texture.FromFile(files[i]);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return textures;
	}

	public static Texture[] FromURLs(String[] urls){
		Texture[] textures = new Texture[urls.length];
		for(int i=0;i<textures.length;i++){
			try{
				textures[i] = Texture.FromURL(urls[i]);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return textures;
	}

	public static Texture[] FromFolder(String folderPath){
		try{
			ArrayList<File> files = new ArrayList<File>();
			File folder = new File(folderPath);
			if(!folder.isDirectory())
				return null;
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					files.add(listOfFiles[i]);
				} else if (listOfFiles[i].isDirectory()) {
					// Nothing to do as of not but in future may be recursive file loading!
				}
			}
			File[] fileList = new File[files.size()];
			int i=0;
			for(File file : files){
				fileList[i++] = file;
			}
			return FromFiles(fileList);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}


	private Texture(BufferedImage image){
		this.image = image;
	}

	public Texture clone(){
		BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		byte[] sourceData = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		byte[] biData = ((DataBufferByte)bi.getRaster().getDataBuffer()).getData();
		System.arraycopy(sourceData, 0, biData, 0, sourceData.length);
		return new Texture(bi);
	}

	public BufferedImage getImage(){
		return image;
	}


	public int getWidth(){
		return image.getWidth();
	}

	public int getHeight(){
		return image.getHeight();
	}

	public void resize(int newWidth, int newHeight){
		Image tmp = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		this.image = dimg;
	}

	public BufferedImage resizeClone(int newWidth, int newHeight){
		Image tmp = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return dimg;
	}

	public void save(File file, String format){
		try{
			ImageIO.write(image, format, file);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void save(String fileName, String format){
		try{
			ImageIO.write(image, format, new File(fileName));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}