package vehicleServiceStationGUI;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import vehicleServiceStation.MaintenanceService;
import vehicleServiceStation.OilService;
import vehicleServiceStation.Service;

public class ServiceDialog extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceStationFrame p;
	private JButton btnSubmit;
	private ButtonGroup radGroup;
	private JLabel lblCost,lblDesc;
	private JRadioButton radMaintenance,radOilService;
	private JTextField txtCost,txtDesc;
	private Service service;
	public ServiceDialog(Window p)
	{
		super(p,"New Service");
		this.initializeComponent();
		this.showJFrame();
		this.p=(ServiceStationFrame) p;
	}
	
	private void btnSubmitActionPerformed(ActionEvent e)
	{
		try
		{
			if((!radOilService.isSelected()||!radMaintenance.isSelected())&&!txtDesc.getText().isEmpty()&&!txtCost.getText().isEmpty())
			{
				if(radOilService.isSelected())
				{
					OilService oil=new OilService();
					oil.setDesc(txtDesc.getText());
					oil.setCost(Double.parseDouble(txtCost.getText()));
					p.getSelServiceRequest().newService(oil);
					p.getServiceListModel().addElement("[O] "+oil.getDesc()+" - "+oil.getCost());
				}
				else
				{
					MaintenanceService m=new MaintenanceService();
					m.setDesc(txtDesc.getText());
					m.setLaborCharges((Double.parseDouble(txtCost.getText())));
					p.getSelServiceRequest().newService(m);
					p.getServiceListModel().addElement("[M] "+m.getDesc()+" - "+m.getLaborCharges());
				}
				this.dispose();
			}
			else
				JOptionPane.showMessageDialog(this, "Enter All Fields");
		}
		catch(NumberFormatException x)
		{
			JOptionPane.showMessageDialog(this, "Enter Valid Cost");
		}
	}
	public JButton getBtnSubmit() {
		return btnSubmit;
	}
	
	public JLabel getLblCost() {
		return lblCost;
	}
	
	public JLabel getLblDesc() {
		return lblDesc;
	}
	public ButtonGroup getRadGroup() {
		return radGroup;
	}
	public JRadioButton getRadMaintenance() {
		return radMaintenance;
	}
	public JRadioButton getRadOilService() {
		return radOilService;
	}
	public Service getService() {
		return service;
	}
	public JTextField getTxtCost() {
		return txtCost;
	}
	public JTextField getTxtDesc() {
		return txtDesc;
	}
	
	private void initializeComponent()
	{
		radOilService=new JRadioButton("Oil Service");
		radOilService.setBounds(25,25,100,25);
		this.add(radOilService);
		
		radMaintenance=new JRadioButton("Maintenance");
		radMaintenance.setBounds(140,25,100,25);
		this.add(radMaintenance);
		
		radGroup=new ButtonGroup();
		radGroup.add(radOilService);
		radGroup.add(radMaintenance);
		
		lblDesc=new JLabel("Description:");
		lblDesc.setBounds(25,60,100,25);
		this.add(lblDesc);
		
		txtDesc=new JTextField();
		txtDesc.setBounds(140,60,150,25);
		this.add(txtDesc);
		
		lblCost=new JLabel("Cost");
		lblCost.setBounds(25,95,100,25);
		this.add(lblCost);
		
		txtCost=new JTextField();
		txtCost.setBounds(140,95,150,25);
		this.add(txtCost);
		
		btnSubmit=new JButton("Submit");
		btnSubmit.setBounds(100,130,100,25);
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnSubmitActionPerformed(arg0);
			}
		});
		this.add(btnSubmit);	
	}
	private void showJFrame()
	{
		this.setLayout(null);
		this.setSize(340,210);
		this.setLocation(450, 270);
		this.setVisible(true);
		
	}
}
