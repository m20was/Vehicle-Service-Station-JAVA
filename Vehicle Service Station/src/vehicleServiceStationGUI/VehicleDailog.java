package vehicleServiceStationGUI;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vehicleServiceStation.Customer;
import vehicleServiceStation.ServiceStation;
import vehicleServiceStation.Vehicle;

public class VehicleDailog extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vehicle vehicle;
	private Customer customer;
	private ServiceReqDialog p;
	private JButton btnSubmit;
	private JLabel lblehicle,lblCompany,lblModel;
	private JTextField txtVehicle,txtCompany,txtModel;
	
	public VehicleDailog(Window obj,String customerName) {
		// TODO Auto-generated constructor stub
		super(obj,"New Vehicle");
		this.p=(ServiceReqDialog) obj;
		this.initializeComponent();
		this.showJFrame();
		this.customer=ServiceStation.station.findCustomer(customerName);
	}
	private void btnSubmitActionPerformed(ActionEvent arg0)
	{
		if(!txtVehicle.getText().isEmpty()&&!txtCompany.getText().isEmpty()&&!txtModel.getText().isEmpty())
		{
			vehicle=new Vehicle();
			vehicle.setNumber(txtVehicle.getText());
			vehicle.setCompany(txtCompany.getText());
			vehicle.setModel(txtModel.getText());
			customer.newVehicle(vehicle);
			this.p.getCmbVehicle().addItem(vehicle.getNumber());
			this.p.getCmbVehicle().setSelectedItem(vehicle.getNumber());
			this.dispose();
		}
		else
			JOptionPane.showMessageDialog(this, "Enter All Fields");
	}
	public JLabel getLblCompany() {
		return lblCompany;
	}
	
	public JLabel getLblehicle() {
		return lblehicle;
	}
	public JLabel getLblModel() {
		return lblModel;
	}
	public JTextField getTxtCompany() {
		return txtCompany;
	}
	public JTextField getTxtModel() {
		return txtModel;
	}
	public JTextField getTxtVehicle() {
		return txtVehicle;
	}
	private void initializeComponent()
	{
		lblehicle=new JLabel("Vehicle No");
		lblehicle.setBounds(25,25,100,25);
		this.add(lblehicle);
		
		lblCompany=new JLabel("Company");
		lblCompany.setBounds(25, 55, 100, 25);
		this.add(lblCompany);
		
		lblModel=new JLabel("Model");
		lblModel.setBounds(25, 85, 100, 25);
		this.add(lblModel);
		
		txtVehicle=new JTextField();
		txtVehicle.setBounds(150,25,150,25);
		this.add(txtVehicle);
		
		txtCompany=new JTextField();
		txtCompany.setBounds(150,55,150,25);
		this.add(txtCompany);
		
		txtModel=new JTextField();
		txtModel.setBounds(150,85,150,25);
		this.add(txtModel);
		
		btnSubmit=new JButton("Submit");
		btnSubmit.setBounds(125, 125, 120, 25);
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
		this.setSize(350,220);
		this.setLocation(170, 170);
		this.setVisible(true);
		
	}
}
