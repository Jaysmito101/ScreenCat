import java.awt.event.*;
import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;

public class Frame extends JDialog{
	public CatPanel cpanel;
	public Animation ani;
    public Frame(Texture[] t){
    	setLayout(new BorderLayout());
    	ani = new Animation(t);
    	ani.repeat = true;
    	cpanel = new CatPanel(ani, this);
    	add(cpanel, BorderLayout.CENTER);
    	//setBounds(100, 100, 200, 100);
        setUndecorated(true);
        getContentPane().setBackground(new Color (0, 0, 0, 0));
        setBackground(new Color (0, 0, 0, 0));
        setSize(100, 100);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
    }

    
}