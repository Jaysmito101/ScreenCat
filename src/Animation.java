import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
public class Animation{

	public Texture[] textures;
	public int animationState;
	private boolean isPlaying;
	public boolean repeat;
	public long animDelay;
	public CatPanel cp;

	private Thread animation;

	public Animation(){
		this.isPlaying = true;
		this.repeat = true;
		this.animationState = 0;
		this.textures = null;
		this.animDelay = 60;
		startAnimation();
	}

	public Animation(Texture[] textures){
		this.isPlaying = true;
		this.repeat = true;
		this.animationState = 0;
		this.animDelay = 60;
		this.textures = textures;
		startAnimation();
	}

	public void setTextures(Texture[] textures){
		this.isPlaying = true;
		this.repeat = true;
		this.animationState = 0;
		this.animDelay = 60;
		this.textures = textures;
		startAnimation();
	}


	public boolean isPlaying(){
		return isPlaying;
	}

	public void pause(){
		isPlaying = false;
		animation.interrupt();
		animation = null;
	}

	public void stop(){
		pause();
		animationState = 0;
	}

	public void startAnimation(){
		if(textures == null)
			return;
		if(animation == null){
			animation = new Thread(new Runnable(){
				@Override
				public void run(){
					while(isPlaying){
						try{
							animationState++;
							if(animationState == textures.length){
								if(repeat){
									animationState = 0;
								}else{
									animationState--;
								}
							}
							if(cp!=null){
									cp.repaint();
							}
							Thread.sleep(animDelay);
						}catch(InterruptedException ie){
							// Nothing needed
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}
				}
			});
			animation.start();
		}else{
			try{
				animation.interrupt();
				animation = null;
				startAnimation();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	public void Render(Graphics g){
		if(this.textures == null){
			//System.out.println("Textures not set!");
			return;
		}
		g.drawImage(textures[animationState].resizeClone(cp.getWidth(), cp.getHeight()), 0, 0, null);
	}
}