package vehicleServiceStationGUI;


import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import vehicleServiceStation.Customer;
import vehicleServiceStation.ServiceRequest;
import vehicleServiceStation.ServiceStation;

public class ServiceReqDialog extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCustomerName,lblVehicle;
	private ServiceStationFrame p;
	private JComboBox<String> cmbCustomerName,cmbVehicle;
	private DefaultComboBoxModel<String> modelCustomer,modelVehicle;
	private JButton btnSubmit;
	
	public ServiceReqDialog(Window p) {
		// TODO Auto-generated constructor stub
		super(p,"New Service Request");
		this.p=(ServiceStationFrame) p;
		this.initializeComponent();
		this.showJFrame();
	}
	private void btnSubmitActionPerformed(ActionEvent arg0) throws IOException
	{
		if(cmbCustomerName.getSelectedItem().toString()!="Select Customer"&&cmbVehicle.getSelectedItem().toString()!="Select Vehicle"&&cmbCustomerName.getSelectedItem().toString()!="New Customer"&&cmbVehicle.getSelectedItem().toString()!="New Vehicle")
		{
			for(ServiceRequest req:p.getActiveServiceList())
			{
				if(req.getCustomerName().equals(this.cmbCustomerName.getSelectedItem().toString())&&req.getVehicleNumber().equals(this.cmbVehicle.getSelectedItem().toString()))
				{
					JOptionPane.showMessageDialog(this,"Customer "+cmbCustomerName.getSelectedItem().toString()+"'s Vehicle "+cmbVehicle.getSelectedItem().toString()+" is already in servicing.");
					return;
				}
			}
					
			p.getActiveServiceList().add(ServiceStation.station.newRequest(cmbCustomerName.getSelectedItem().toString(), cmbVehicle.getSelectedItem().toString()));
			p.getActiveServiceRequestsListModel().addElement(cmbCustomerName.getSelectedItem().toString()+" - "+ cmbVehicle.getSelectedItem().toString());
			ServiceStation.station.storeCustDetails();
			this.dispose();
		}
		else
			JOptionPane.showMessageDialog(this, "Select Customer & its Vehicle");
	}
	private void cmbCustomerNameItemStateChanged(ItemEvent arg0)
	{
		if(arg0.getStateChange()==ItemEvent.SELECTED)
		{
			
			if(arg0.getItem().toString().equals("New Customer"))
			{
				new CustomerDialog(this);
			}
			else if(arg0.getItem().toString()!="Select Customer")
			{
				modelVehicle.removeAllElements();
				modelVehicle.addElement("Select Vehicle");
				modelVehicle.addElement("New Vehicle");
				for(String no:ServiceStation.station.findCustomer(this.cmbCustomerName.getSelectedItem().toString()).getVehicleList().keySet())
					modelVehicle.addElement(no);
			}
		}
	}
	private void cmbVehicleItemStateChanged(ItemEvent arg0)
	{
		if(arg0.getStateChange()==ItemEvent.SELECTED)
		{
			if(arg0.getItem().toString().equalsIgnoreCase("New Vehicle"))
				if(this.cmbCustomerName.getSelectedItem().toString().equalsIgnoreCase("Select Customer"))
					JOptionPane.showMessageDialog(this, "First Select Customer");
				else
					new VehicleDailog(this,cmbCustomerName.getSelectedItem().toString());
		}
	}
	public JButton getBtnSubmit() {
		return btnSubmit;
	}
	public JComboBox<String> getCmbCustomerName() {
		return cmbCustomerName;
	}
	public JComboBox<String> getCmbVehicle() {
		return cmbVehicle;
	}
	public JLabel getLblCustomerName() {
		return lblCustomerName;
	}
	public JLabel getLblVehicle() {
		return lblVehicle;
	}
	private void initializeComponent()
	{
		lblCustomerName=new JLabel("Customer Name");
		lblCustomerName.setBounds(25,25,100,25);
		this.add(lblCustomerName);
		
		lblVehicle=new JLabel("Vehicle");
		lblVehicle.setBounds(25,65,100,25);
		this.add(lblVehicle);
		
		modelCustomer=new DefaultComboBoxModel<String>(new String[] {"Select Customer","New Customer"});
		for(Customer c:ServiceStation.station.getCustList())
			modelCustomer.addElement(c.getName());
		cmbCustomerName=new JComboBox<String>();
		cmbCustomerName.setBounds(150,25,150,25);
		cmbCustomerName.setModel(modelCustomer);
		cmbCustomerName.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				cmbCustomerNameItemStateChanged(arg0);
				
			}
		});
		this.add(cmbCustomerName);
		
		modelVehicle=new DefaultComboBoxModel<String>(new String[] {"Select Vehicle","New Vehicle"});
		cmbVehicle=new JComboBox<String>();
		cmbVehicle.setBounds(150,60,150,25);
		cmbVehicle.setModel(modelVehicle);
		cmbVehicle.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				cmbVehicleItemStateChanged(arg0);
			}
		});
		this.add(cmbVehicle);
		
		btnSubmit=new JButton("Submit");
		btnSubmit.setBounds(150, 100, 100, 25);
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					btnSubmitActionPerformed(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		this.add(btnSubmit);

	}
	private void showJFrame()
	{
		this.setLayout(null);
		this.setSize(350,200);
		this.setLocation(170, 170);
		this.setVisible(true);
		
	}
}
