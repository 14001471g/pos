package pos;

import javax.swing.JButton;

public abstract class IceCream extends JButton{
	protected double price;
	protected String name;
	
	public abstract double getPrice();
	public abstract String getName();

}
