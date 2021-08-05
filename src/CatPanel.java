import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CatPanel extends JPanel implements MouseMotionListener, MouseListener{
	Animation ani;
	Frame f;
	int x, y;

	public CatPanel(Animation ani, Frame f){
		this.ani = ani;
		this.f = f;
		this.x = this.y = 0;
		ani.cp = this;
		addMouseMotionListener(this);
		addMouseListener(this);
		setBackground(new Color(255, 255, 255, 0));
	}

	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		ani.Render(g);
	}

	@Override
    public void mousePressed(MouseEvent e) {
    	this.x = e.getX();
    	this.y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        f.setLocation(e.getXOnScreen() - this.x, e.getYOnScreen()-this.y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}