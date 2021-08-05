import java.awt.event.*;
import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;


public class Cat implements Runnable, MouseListener{
	public Frame cf;
	public int speed = 5;
	private boolean isRunning;

	public Cat(Frame f){
		this.cf = f;
		isRunning = false;
	}

	public void sleep(int t){
		try{
			Thread.sleep(t);
		}catch(Exception ex){}
	}

	public void walkTo(){
		if(isRunning)
			return;
		isRunning = true;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		int x = (int)(Math.random()*width);
		int y = (int)(Math.random()*height);
		new Thread(new Runnable(){
			@Override
			public void run(){
				while(isRunning){
					int xs = (int)cf.getLocation().getX();
					int ys = (int)cf.getLocation().getY();
					if(x > xs){
						xs += speed;
					}else{
						xs -= speed;
					}
					if(y > ys){
						ys += speed;
					}else{
						ys -= speed;
					}
					if(Math.abs(x*x+y*y - (xs*xs+y*y)) <= 5000){
						break;
					}
					cf.setLocation(xs, ys);
					try{
						Thread.sleep(100);
					}catch(Exception ex){}
				}
				isRunning = false;
			}
		}).start();
	}

	@Override
	public void run(){
		cf.cpanel.addMouseListener(this);
		while(true){
			walkTo();
			sleep((int)(Math.random()*30000));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		cf.ani.textures = Texture.FromFolder("./ts/click");
		cf.ani.animDelay = 100;
		new Thread(new Runnable(){
			@Override
			public void run(){
				try{
					Thread.sleep(500);
				}catch(Exception ex){}
				cf.ani.textures = Texture.FromFolder("./ts/idle");
				cf.ani.animDelay = 60;
			}
		}).start();

		AudioP.Play("./ts/audio/meow.wav", 2000);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		isRunning = false;
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}