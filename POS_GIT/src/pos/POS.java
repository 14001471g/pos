package pos;
import java.util.ArrayList;
import java.util.List;


public class POS {
	private List<IceCream> iceCreamFlavorList;
	private List<IceCream> iceCreamDecoratorList; 
	private IceCream iceCream;
	
	public POS(){
		iceCreamFlavorList = new ArrayList<IceCream>();
		iceCreamDecoratorList = new ArrayList<IceCream>();
		iceCream = null;
	}


	public List<IceCream> getIceCreamFlavorList() {
		return iceCreamFlavorList;
	}


	public void setIceCreamFlavorList(List<IceCream> iceCreamFlavorList) {
		this.iceCreamFlavorList = iceCreamFlavorList;
	}


	public List<IceCream> getIceCreamDecoratorList() {
		return iceCreamDecoratorList;
	}


	public void setIceCreamDecoratorList(List<IceCream> iceCreamDecoratorList) {
		this.iceCreamDecoratorList = iceCreamDecoratorList;
	}

	public IceCream addIceCreamDecorator(IceCream decorator) {
		iceCream = new IceCreamDecorator(iceCream.getName() + " with " + decorator.getName(), iceCream.getPrice() + decorator.getPrice()); 
		
		
		return iceCream;
		
	}

	public void newIceCream(){
		iceCream = null;
	}
	
	public double getTotalPrice(){
		return iceCream == null? 0: iceCream.getPrice();
	}

	public IceCream getIceCream() {
		return iceCream;
	}

	public void setIceCream(IceCream iceCream) {
		this.iceCream = iceCream;
	}

	
}
