import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class POSJFrame extends JFrame {

	public POSJFrame() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setSize((int) width / 2, (int) height - 55);
		setTitle("Ice Cream POS");
		this.getContentPane().add(new POSJPanel(this));
		setResizable(false);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		POSJFrame pos = new POSJFrame();
		pos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
