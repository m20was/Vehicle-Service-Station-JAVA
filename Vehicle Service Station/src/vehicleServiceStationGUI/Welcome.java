package vehicleServiceStationGUI;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

import vehicleServiceStation.ServiceStation;

public class Welcome extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Welcome() throws InterruptedException
	{
		JLabel wel=new JLabel(ServiceStation.station.name);
		wel.setBounds(80,10,870,350);
		wel.setFont(new Font("Elephant", 100, 100));
		this.add(wel);	
		
		this.setLayout(null);
		this.setSize(945,400);
		this.setLocation(170, 170);
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		this.setOpacity(0.8f);
		this.setVisible(true);
		
		for(double i=0;i<=1000000000;i++);
		
		new Login();
		
		this.dispose();
	}
}
