import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main{
	public static void main(String[] args) {
		Frame dlg = new Frame(Texture.FromFolder("./ts/idle"));
		dlg.setVisible(true);
        Cat c = new Cat(dlg);
        new Thread(c).start();
        Timer showTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dlg.setVisible(!dlg.isVisible());
            }
        });
        showTimer.start();
	}
}