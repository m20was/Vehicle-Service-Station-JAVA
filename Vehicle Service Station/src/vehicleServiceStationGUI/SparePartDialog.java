package vehicleServiceStationGUI;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vehicleServiceStation.MaintenanceService;
import vehicleServiceStation.SparePart;

public class SparePartDialog extends JDialog
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnSubmit;
	private JLabel lblDesc,lblCost;
	private JTextField txtDesc,txtCost;
	private ServiceStationFrame p;
	
	public SparePartDialog(Window p) 
	{
		super(p,"New Spare Part");
		this.initializeComponent();
		this.showJFrame();
		this.p=(ServiceStationFrame) p;
	}
	private void btnSubmitActionPerformed(ActionEvent arg0)
	{
		try
		{
			if(!txtDesc.getText().isEmpty()&&!txtCost.getText().isEmpty())
			{
				SparePart sp=new SparePart(txtDesc.getText(), Double.parseDouble(txtCost.getText()));
				p.getSparePartListModel().addElement(sp.getDesc()+" - "+sp.getRate());
				((MaintenanceService)p.getService()).newSparePart(sp);
				p.getLblServiceDesc().setText("Parts Changed: "+((MaintenanceService)p.getService()).getPartList().size());
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
	private void initializeComponent()
	{
		lblDesc=new JLabel("Description");
		lblDesc.setBounds(25,25,100,25);
		this.add(lblDesc);
		
		lblCost=new JLabel("Cost");
		lblCost.setBounds(25, 55, 100, 25);
		this.add(lblCost);
		
		txtDesc=new JTextField();
		txtDesc.setBounds(150,25,150,25);
		this.add(txtDesc);
		
		txtCost=new JTextField();
		txtCost.setBounds(150,55,150,25);
		this.add(txtCost);
		
		btnSubmit=new JButton("Submit");
		btnSubmit.setBounds(125, 100, 120, 25);
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
		this.setSize(350,190);
		this.setLocation(450, 270);
		this.setVisible(true);
		
	}

}
