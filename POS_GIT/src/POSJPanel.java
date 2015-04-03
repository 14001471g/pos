
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import pos.IceCream;
import pos.IceCreamDecorator;
import pos.IceCreamFlavor;
import pos.POS;

public class POSJPanel extends JPanel {
	private JFrame parent;
	private POS pos = new POS();
	private final String TOTAL = "Total: $";
	private GridBagLayout POSPanelLayout= new GridBagLayout();
	private List<IceCream> iceCreamFlavorList = null;
	private List<IceCream> iceCreamDecoratorList = null;
	
	public POSJPanel(JFrame parent) {
		this.parent = parent;
		iceCreamFlavorList = pos.getIceCreamFlavorList();
		iceCreamDecoratorList = pos.getIceCreamDecoratorList();
		
		
		iceCreamFlavorList.add(new IceCreamFlavor("Chocolate",20));
		iceCreamFlavorList.add(new IceCreamFlavor("Vanilla",20));
		
		iceCreamDecoratorList.add(new IceCreamDecorator("M&M",5));
		iceCreamDecoratorList.add(new IceCreamDecorator("Strawberry",5));
		
		pos.setIceCreamFlavorList(iceCreamFlavorList);
		pos.setIceCreamDecoratorList(iceCreamDecoratorList);

		addComponentsToPane();
	}

	public void addComponentsToPane() {
		this.setLayout(POSPanelLayout);
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 1.0;// request any extra vertical space
		
		final JLabel totalLabel = new JLabel(TOTAL+pos.getTotalPrice());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		this.add(totalLabel, c);
		
		JButton newIceCreamButton = new JButton("New Ice-cream");
		newIceCreamButton.addActionListener(new ActionListener() {				 
            public void actionPerformed(ActionEvent e)
            {
            	pos.newIceCream();
            	totalLabel.setText(TOTAL+pos.getTotalPrice());
            }
        });  
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(newIceCreamButton, c);

		JButton systemAdministratorButton = new JButton("System Administrator");
		systemAdministratorButton.addActionListener(new ActionListener() {				 
            public void actionPerformed(ActionEvent e)
            {
            	showDialog();
            }
        });      
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		this.add(systemAdministratorButton, c);

		

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		JPanel flavorPanel = new JPanel(new GridLayout(0,1));
		
		for (int i=0; i < iceCreamFlavorList.size(); i++){
			final IceCream iceCreamFlavor = iceCreamFlavorList.get(i);
			
			iceCreamFlavor.addActionListener(new ActionListener() {				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	 if (pos.getIceCream() == null){
	            		 pos.setIceCream(iceCreamFlavor);
	            		 totalLabel.setText(TOTAL+pos.getTotalPrice());
	            	 }
	            }
	        });      
			 
			flavorPanel.add(iceCreamFlavor);
		}	

		JScrollPane flavorScrollPane = new JScrollPane(flavorPanel, 
														JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		flavorScrollPane.setPreferredSize(new Dimension(parent.getWidth()/2 - 10, parent.getHeight()/4));
		
		this.add(flavorScrollPane, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		JPanel decoratorPanel = new JPanel(new GridLayout(0,1));
	
		for (int i=0; i < iceCreamDecoratorList.size(); i++){
			final IceCream decorator = iceCreamDecoratorList.get(i);
			
			decorator.addActionListener(new ActionListener() {				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	if(pos.getIceCream() != null){
		           		pos.addIceCreamDecorator(decorator);
		           		totalLabel.setText(TOTAL+pos.getTotalPrice());
	            	}
		        }
	        });      
			 
			decoratorPanel.add(decorator);
		}	
		
		JScrollPane decoratorScrollPane = new JScrollPane(decoratorPanel, 
															JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
											                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		decoratorScrollPane.setPreferredSize(new Dimension(parent.getWidth()/2 - 10, parent.getHeight()/4));
		this.add(decoratorScrollPane, c);

	}
	
	public void refreshLayout(){
		this.removeAll();
		addComponentsToPane();

		this.revalidate();
		this.validate();
		this.repaint();
		POSPanelLayout.layoutContainer(this);
		
	}
	
	public void showDialog(){
		//Create the dialog.
		final JDialog dialog = new JDialog(parent, "New");
		JPanel inputPanel = new JPanel(new GridLayout(3, 2));
		final JTextField newName = new JTextField();
		final JTextField newUnitPrice = new JTextField();
		
		JPanel radioPanel = new JPanel(new GridLayout(1, 2));
		ButtonGroup buttonGroup = new ButtonGroup();
		final JRadioButton flavorRadio = new JRadioButton("Flavor");
		final JRadioButton decoratorRadio = new JRadioButton("Decorator");
		buttonGroup.add(flavorRadio);
		buttonGroup.add(decoratorRadio);
		
		radioPanel.add(flavorRadio);
		radioPanel.add(decoratorRadio);
		
		inputPanel.add(new JLabel("Type:"));
		inputPanel.add(radioPanel);
		inputPanel.add(new JLabel("Name:"));
		inputPanel.add(newName);
		inputPanel.add(new JLabel("Price:"));
		inputPanel.add(newUnitPrice);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (flavorRadio.isSelected()){
					iceCreamFlavorList.add(new IceCreamFlavor(newName.getText(),Double.parseDouble(newUnitPrice.getText())));
					pos.setIceCreamFlavorList(iceCreamFlavorList);
					
	
				}
				else {
					iceCreamDecoratorList.add(new IceCreamDecorator(newName.getText(),Double.parseDouble(newUnitPrice.getText())));
					pos.setIceCreamDecoratorList(iceCreamDecoratorList);
				}
				refreshLayout();
			}
		});
		
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(saveButton);
		buttonPanel.add(closeButton);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,0,5,5));
		
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.add(inputPanel, BorderLayout.CENTER);
		contentPane.add(buttonPanel, BorderLayout.PAGE_END);
		contentPane.setOpaque(true);
		dialog.setContentPane(contentPane);

		//Show it.
		dialog.setSize(new Dimension(400, 150));
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}
}
