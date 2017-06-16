package vehicleServiceStationGUI;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import vehicleServiceStation.Bill;
import vehicleServiceStation.Customer;
import vehicleServiceStation.ServiceStation;

public class BillDialog extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bill b;
	private JButton btnBillPaid;
	private JLabel lblTotalBill,lblPaidAmount;
	private JTextArea txtBill;
	private JTextField txtPaidAmount,txtTotalBill;
	private ServiceStationFrame p;
	public BillDialog(Window p) 
	{
		// TODO Auto-generated constructor stub
		super(p,"Bill : "+((ServiceStationFrame)p).getListActiveServiceRequests().getSelectedValue());
		this.initializeComponent();
		this.showJFrame();
		this.p=(ServiceStationFrame) p;
		b=ServiceStation.station.newBill(this.p.getSelServiceRequest());
		this.loadBill();
	}
	private void btnBillPaidActionPerformed(ActionEvent arg0)
	{
		double bal=0;
		try
		{
			if(txtPaidAmount.getText()!=null&& !txtPaidAmount.getText().isEmpty())
			{
				Customer c=ServiceStation.station.findCustomer(p.getSelServiceRequest().getCustomerName());
				bal=c.payBill(b, Double.parseDouble(txtPaidAmount.getText()));
				try {
					ServiceStation.station.storeBillDetails();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.getActiveServiceList().remove(b.getRequest());
				p.setSelServiceRequest(null);
				p.setService(null);
				p.getActiveServiceRequestsListModel().removeElement(b.getRequest().getCustomerName()+" - "+b.getRequest().getVehicleNumber());
				p.getLblCustomerName().setText("Name");
				p.getLblVehNumber().setText("Vehicle");
				p.getLblServiceDesc().setText("");
				p.getServiceListModel().removeAllElements();
				p.getSparePartListModel().removeAllElements();
				JOptionPane.showMessageDialog(this," Amount Paid Rs."+txtPaidAmount.getText()+" & Remaining Amount Rs. "+bal);
				this.dispose();
			}
			else
				JOptionPane.showMessageDialog(this, "Enter Valid Paid Amount");
		}
		catch(NumberFormatException x)
		{
			JOptionPane.showMessageDialog(this, "Enter Valid Paid Amount");
		}
	}
	private void initializeComponent()
	{		
		txtBill=new JTextArea();
		JScrollPane scroll=new JScrollPane(txtBill,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		scroll.setBounds(25,25,600,300);
		this.getContentPane().add(scroll);
	
		lblTotalBill=new JLabel("Total Bill");
		lblTotalBill.setBounds(25,340,100,25);
		this.add(lblTotalBill);
		
		txtTotalBill=new JTextField();
		txtTotalBill.setBounds(100, 340, 100, 25);
		txtTotalBill.setEnabled(false);
		this.add(txtTotalBill);
		
		lblPaidAmount=new JLabel("Total Bill");
		lblPaidAmount.setBounds(230,340,100,25);
		this.add(lblPaidAmount);
		
		txtPaidAmount=new JTextField();
		txtPaidAmount.setBounds(300, 340, 100,25);
		this.add(txtPaidAmount);
		
		btnBillPaid=new JButton("Bill Paid");
		btnBillPaid.setBounds(440,340,80,25);
		btnBillPaid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnBillPaidActionPerformed(arg0);
			}
		});
		this.add(btnBillPaid);
	}
	private void loadBill()
	{
		ByteArrayOutputStream o=new ByteArrayOutputStream();
		b.print(o);
		txtBill.setText(o.toString());
		txtTotalBill.setText(String.format("%.2f",b.computeTotalBill()+ServiceStation.station.findCustomer(b.getRequest().getCustomerName()).getLastBalance()));
	}
	private void showJFrame()
	{
		this.setLayout(null);
		this.setSize(675,450);
		this.setLocation(300,145);
		this.setVisible(true);
	}
}
