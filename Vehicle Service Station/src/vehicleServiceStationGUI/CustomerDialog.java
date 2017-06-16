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

public class CustomerDialog extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer=null;
	private ServiceReqDialog p;
	private JButton btnSubmit;
	private JLabel lblCustomerName,lblAddress,lblMobile;
	private JTextField txtCustomerName,txtAddress,txtMobile;
	
	public CustomerDialog(Window p) {
		// TODO Auto-generated constructor stub
		super(p,"New Customer");
		this.initializeComponent();
		this.showJFrame();
		this.p=(ServiceReqDialog) p;
	}

	private void btnSubmitActionPerformed(ActionEvent arg0)
	{
		if(!txtCustomerName.getText().isEmpty()&&!txtAddress.getText().isEmpty()&&!txtMobile.getText().isEmpty())
		{
			customer=new Customer();
			customer.setName(txtCustomerName.getText());
			customer.setAddress(txtAddress.getText());
			customer.setMobile(txtMobile.getText());
			ServiceStation.station.newCustomer(customer);
			p.getCmbCustomerName().addItem(customer.getName());
			p.getCmbCustomerName().setSelectedItem(customer.getName());
			this.dispose();
		}
		else
			JOptionPane.showMessageDialog(this, "Enter All Fields");
	}
	public JLabel getLblAddress() {
		return lblAddress;
	}
	public JLabel getLblCustomerName() {
		return lblCustomerName;
	}
	public JLabel getLblMobile() {
		return lblMobile;
	}
	public JTextField getTxtAddress() {
		return txtAddress;
	}
	public JTextField getTxtCustomerName() {
		return txtCustomerName;
	}
	public JTextField getTxtMobile() {
		return txtMobile;
	}
	private void initializeComponent()
	{
		lblCustomerName=new JLabel("Customer Name");
		lblCustomerName.setBounds(25,25,100,25);
		this.add(lblCustomerName);
		
		lblAddress=new JLabel("Address");
		lblAddress.setBounds(25, 55, 100, 25);
		this.add(lblAddress);
		
		lblMobile=new JLabel("Mobile No");
		lblMobile.setBounds(25, 85, 100, 25);
		this.add(lblMobile);
		
		txtCustomerName=new JTextField();
		txtCustomerName.setBounds(150,25,150,25);
		this.add(txtCustomerName);
		
		txtAddress=new JTextField();
		txtAddress.setBounds(150,55,150,25);
		this.add(txtAddress);
		
		txtMobile=new JTextField();
		txtMobile.setBounds(150,85,150,25);
		this.add(txtMobile);
		
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
