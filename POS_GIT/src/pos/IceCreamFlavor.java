package pos;

public class IceCreamFlavor extends IceCream {
	
	public IceCreamFlavor(String name, double price){
		this.setText("["+name+", $"+ price+"]");
		this.name = name;
		this.price = price;
	}
	
	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public String getName() {
		return name;
	}

}
