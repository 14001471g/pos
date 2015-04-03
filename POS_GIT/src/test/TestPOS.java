package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pos.IceCream;
import pos.IceCreamDecorator;
import pos.IceCreamFlavor;
import pos.POS;

public class TestPOS {
	POS pos;
	List<IceCream> iceCreamFlavorList = null;
	List<IceCream> iceCreamDecoratorList = null;
	
	@Before
	public void before(){
		pos = new POS();
		iceCreamFlavorList = pos.getIceCreamFlavorList();
		iceCreamDecoratorList = pos.getIceCreamDecoratorList();
		
		
		iceCreamFlavorList.add(new IceCreamFlavor("Chocolate",20));
		iceCreamFlavorList.add(new IceCreamFlavor("Vanilla",20));
		
		iceCreamDecoratorList.add(new IceCreamDecorator("M&M",5));
		iceCreamDecoratorList.add(new IceCreamDecorator("Strawberry",5));
		
		pos.setIceCreamFlavorList(iceCreamFlavorList);
		pos.setIceCreamDecoratorList(iceCreamDecoratorList);

	}

	@Test
	public void testGetTotalPrice(){
		assertEquals(pos.getTotalPrice(), 0.0, 0);
	}
	
	/* test in pos */	
	@Test
	public void testGetIceCreamFlavorList() {

		assertEquals(pos.getIceCreamFlavorList().size(), 2);
	}
	
	@Test
	public void testAddIceCreamFlavor() {
		iceCreamFlavorList.add(new IceCreamFlavor("test1",20));
		iceCreamFlavorList.add(new IceCreamFlavor("test2",20));
		
		pos.setIceCreamFlavorList(iceCreamFlavorList);
		assertEquals(pos.getIceCreamFlavorList().size(), 4);
	}

	@Test
	public void testGetIceCreamDecoratorList() {
		assertEquals(pos.getIceCreamDecoratorList().size(), 2);
	}
	
	@Test
	public void testAddDecorator() {
		iceCreamDecoratorList.add(new IceCreamDecorator("test1",5));
		iceCreamDecoratorList.add(new IceCreamDecorator("test2",5));
		
		pos.setIceCreamDecoratorList(iceCreamDecoratorList);
		assertEquals(pos.getIceCreamDecoratorList().size(), 4);

	}
	
	@Test
	public void testNewIceCreamInPOS(){
		pos.newIceCream();
		assertEquals(pos.getIceCream(), null);
		assertEquals(pos.getTotalPrice(), 0, 0);
	}
	
	@Test
	public void testGetTotalPriceInPOS(){
		pos.newIceCream();
		pos.setIceCream(new IceCreamFlavor("test1",20));
		pos.addIceCreamDecorator(new IceCreamDecorator("test1",5));
		pos.addIceCreamDecorator(new IceCreamDecorator("test1",5));
		assertEquals(pos.getTotalPrice(), 30, 0);
	}
		
}
