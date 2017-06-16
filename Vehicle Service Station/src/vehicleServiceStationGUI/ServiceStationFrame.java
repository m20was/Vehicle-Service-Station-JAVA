package vehicleServiceStationGUI;

import java.awt.Desktop;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import vehicleServiceStation.Bill;
import vehicleServiceStation.MaintenanceService;
import vehicleServiceStation.OilService;
import vehicleServiceStation.Service;
import vehicleServiceStation.ServiceRequest;
import vehicleServiceStation.ServiceStation;
import vehicleServiceStation.SparePart;

public class ServiceStationFrame extends JFrame
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnFinishServiceReq,btnNewService,btnNewServiceReq,btnNewSparePart,btnPrintBill,btnSaveAll,btnShowAccounts;
	private JTextField txtDate;
	private JLabel lblBusinessAmount,lblCustomerName,lblServiceDesc,lblVehNumber;
	private JList<String> listActiveServiceRequests,listServiceList,listSparePartList;
	private JTable tblBills;
	private List billList;
	private ServiceRequest selServiceRequest=null;
	private Service service=null;
	private ArrayList<ServiceRequest> activeServiceList;
	private DefaultListModel<String> activeServiceRequestsListModel=new DefaultListModel<String>();
	private DefaultListModel<String> serviceListModel=new DefaultListModel<String>();
	private DefaultListModel<String> sparePartListModel=new DefaultListModel<String>();
	private DefaultTableModel billsTableModel;
	
	public ServiceStationFrame()
	{
		// TODO Auto-generated constructor stub
		super("Vehicle Service Station");
		this.activeServiceList=new ArrayList<ServiceRequest>();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
				serviceStationFrameWindowClosing(e);
			}
		});
		this.initializeComponent();
		this.showJFrame();
	}
	private void btnFinishServiceReqActionPerformed(ActionEvent arg0)
	{
		if(selServiceRequest!=null)
			new BillDialog(this);
		else
			JOptionPane.showMessageDialog(this, "Select Service Request");
	}
	
	private void btnNewServiceActionPerformed(ActionEvent arg0)
	{
		if(this.selServiceRequest!=null)
			new ServiceDialog(this);
		else
			JOptionPane.showMessageDialog(this, "Select Service Request");
	}
	
	private void btnNewServiceRequestActionPerformed(ActionEvent arg0)
	{
		new ServiceReqDialog(this);
	}

	private void btnNewSparePartActionPerformed(ActionEvent e)
	{
		if(this.service!=null)
			new SparePartDialog(this);
		else
			JOptionPane.showMessageDialog(this, "Select Maintenance Service");
	}
	private void btnprintBillActionPerformed(ActionEvent e) throws IOException
	{
		/*JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			File file = fileChooser.getSelectedFile();
			file.createNewFile();
			for(Bill b:ServiceStation.station.getBillList())
				if(billsTableModel.getValueAt(tblBills.getSelectedRow(), 0).equals(b.getRequest().getCustomerName()+" - "+b.getRequest().getVehicleNumber()))
					b.print(new FileOutputStream(file));
		}*/
		if(tblBills.getSelectedRowCount()==0)
		{
			JOptionPane.showMessageDialog(this, "Select Bill to Print!!!");
			return;
		}
		try
		{
			File f=new File(JOptionPane.showInputDialog("Enter File Name"));
			for(Bill b:ServiceStation.station.getBillList())
				if(billsTableModel.getValueAt(tblBills.getSelectedRow(), 0).equals(b.getRequest().getCustomerName()+" - "+b.getRequest().getVehicleNumber()))
					b.print(new FileOutputStream(f));
			Desktop.getDesktop().open(f);
		}
		catch(NullPointerException x)
		{
			JOptionPane.showMessageDialog(this, "Enter Valid File Name");
		}
		
	}
	private void btnShowAccountsActionPerformed(ActionEvent arg0)
	{
		try 
		{
			//removes all rows from table
		   for (int i = billsTableModel.getRowCount() - 1; i >= 0; i--) 
		    {
		    	billsTableModel.removeRow(i);
		    }
			for(Bill b:ServiceStation.station.getBillList())
			{
				String date=new SimpleDateFormat("dd/MM/yyyy").format(b.getBillDate()).toString();
				if(txtDate.getText().equals(date))
					billsTableModel.addRow(new String[]{b.getRequest().getCustomerName()+" - "+b.getRequest().getVehicleNumber(),Double.toString(b.getPaidAmount())});
			}
			
				lblBusinessAmount.setText("Total Business Rs. "+Double.toString(ServiceStation.station.computeCash(new SimpleDateFormat("dd/MM/yyyy").parse(txtDate.getText()))));
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Enter Valid Date");
		}
	}

	private void displayServiceRequestDetails(ServiceRequest req)
	{
		if(sparePartListModel.size()!=0)
			sparePartListModel.removeAllElements();
		if(serviceListModel.size()!=0)
			serviceListModel.removeAllElements();
		this.service=null;
		lblCustomerName.setText(req.getCustomerName());
		lblVehNumber.setText(req.getVehicleNumber());
		for(Service s:req.getServiceList())
		{
			if(s instanceof OilService)
			{
				OilService o=(OilService)s;
				this.getServiceListModel().addElement("[O] "+o.getDesc()+" - "+o.getCost());
			}
			else
			{
				MaintenanceService m=(MaintenanceService)s;
				this.getServiceListModel().addElement("[M] "+m.getDesc()+" - "+m.getLaborCharges());
			}
		}
		lblServiceDesc.setText("");
	}

	public ArrayList<ServiceRequest> getActiveServiceList() {
		return activeServiceList;
	}
	public DefaultListModel<String> getActiveServiceRequestsListModel() {
		return activeServiceRequestsListModel;
	}
	
	public List getBillList() {
		return billList;
	}
	
	public DefaultTableModel getBillsTableModel() {
		return billsTableModel;
	}

	public JButton getBtnFinishServiceReq() {
		return btnFinishServiceReq;
	}

	public JButton getBtnNewService() {
		return btnNewService;
	}

	public JButton getBtnNewServiceReq() {
		return btnNewServiceReq;
	}

	public JButton getBtnNewSparePart() {
		return btnNewSparePart;
	}

	public JButton getBtnPrintAll() {
		return btnPrintBill;
	}

	public JButton getBtnSaveAll() {
		return btnSaveAll;
	}

	public JButton getBtnShowAccounts() {
		return btnShowAccounts;
	}

	public JLabel getLblBusinessAmount() {
		return lblBusinessAmount;
	}

	public JLabel getLblCustomerName() {
		return lblCustomerName;
	}

	public JLabel getLblServiceDesc() {
		return lblServiceDesc;
	}

	public JLabel getLblVehNumber() {
		return lblVehNumber;
	}

	public JList<String> getListActiveServiceRequests() {
		return listActiveServiceRequests;
	}

	public JList<String> getListServiceList() {
		return listServiceList;
	}

	public JList<String> getListSparePartList() {
		return listSparePartList;
	}

	public ServiceRequest getSelServiceRequest() {
		return selServiceRequest;
	}

	public Service getService() {
		return service;
	}

	public DefaultListModel<String> getServiceListModel() {
		return serviceListModel;
	}

	public DefaultListModel<String> getSparePartListModel() {
		return sparePartListModel;
	}

	public JTable getTblBills() {
		return tblBills;
	}

	public JTextField getTxtDate() {
		return txtDate;
	}
	private void initializeComponent()
	{
		txtDate=new JTextField(10);
		txtDate.setText((new SimpleDateFormat("dd/MM/yyyy").format(new Date())).toString());
		txtDate.setBounds(25,25,130,45);
		this.add(txtDate);
		
		btnShowAccounts=new JButton("Accounts");
		btnShowAccounts.setBounds(160,20,130,55);
		btnShowAccounts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnShowAccountsActionPerformed(arg0);
			}
		});
		this.add(btnShowAccounts);

		billsTableModel=new DefaultTableModel(new String[]{"Details","Amount"},0);
		
		tblBills=new JTable();
		tblBills.setModel(billsTableModel);
		tblBills.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBills.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				tblBillsValueChanged(arg0);
			}
		});
		JScrollPane panel = new JScrollPane(tblBills, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		panel.setBounds(25,90,260,200);
        this.getContentPane().add(panel);
		
		lblBusinessAmount=new JLabel("Total Business Rs. 0.0");
		lblBusinessAmount.setBounds(25,290,200,30);
		this.add(lblBusinessAmount);
		
		btnPrintBill=new JButton("Print Bill");
		btnPrintBill.setBounds(25,320,120,30);
		btnPrintBill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					btnprintBillActionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.add(btnPrintBill);
		
		btnSaveAll=new JButton("Save All");
		btnSaveAll.setBounds(160,320,120,30);
		btnSaveAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ServiceStation.station.storeBillDetails();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.add(btnSaveAll);
		
		JLabel lblActiveServiceRequest=new JLabel("Active Service Request");
		lblActiveServiceRequest.setBounds(330,20,150,25);
		this.add(lblActiveServiceRequest);
		
		listActiveServiceRequests=new JList<String>();
		listActiveServiceRequests.setModel(activeServiceRequestsListModel);
		listActiveServiceRequests.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				listActiveServiceRequestsValueChanged(arg0);
			}
		});
		JScrollPane panel1 = new JScrollPane(listActiveServiceRequests, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		panel1.setBounds(310,55,180,220);
		this.getContentPane().add(panel1);
		
		btnFinishServiceReq=new JButton("Finish Request");
		btnFinishServiceReq.setBounds(310,290,180,25);
		btnFinishServiceReq.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnFinishServiceReqActionPerformed(arg0);
			}
		});
		this.add(btnFinishServiceReq);
		
		btnNewServiceReq=new JButton("New Service Request");
		btnNewServiceReq.setBounds(310,320,180,25);
		btnNewServiceReq.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnNewServiceRequestActionPerformed(e);
			}
		});
		this.add(btnNewServiceReq);
		
		lblCustomerName=new JLabel("Name");
		lblCustomerName.setBounds(520,20,180,25);
		this.add(lblCustomerName);
		
		lblVehNumber=new JLabel("Vehicle");
		lblVehNumber.setBounds(520,45,180,25);
		this.add(lblVehNumber);
		
		listServiceList=new JList<String>();
		listServiceList.setModel(serviceListModel);
	    listServiceList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				listServiceListValueChanged(e);
			}
		});
	    JScrollPane panel2 = new JScrollPane(listServiceList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		panel2.setBounds(520,80,180,230);
		this.getContentPane().add(panel2);
		
		btnNewService=new JButton("New Service");
		btnNewService.setBounds(520,320,180,25);
		btnNewService.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnNewServiceActionPerformed(arg0);
			}
		});
		this.add(btnNewService);
		
		lblServiceDesc=new JLabel("Service Type");
		lblServiceDesc.setBounds(720,25,180,25);
		this.add(lblServiceDesc);
		
		listSparePartList=new JList<String>();
		listSparePartList.setModel(sparePartListModel);
		JScrollPane panel3 = new JScrollPane(listSparePartList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		panel3.setBounds(720,80,180,230);
		this.getContentPane().add(panel3);
		
		btnNewSparePart =new JButton("New Part");
		btnNewSparePart.setBounds(720,320,180,25);
		btnNewSparePart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnNewSparePartActionPerformed(e);
			}
		});
		this.add(btnNewSparePart);
	}
	private void listActiveServiceRequestsValueChanged(ListSelectionEvent arg0)
	{
		if(activeServiceRequestsListModel.getSize()==0||listActiveServiceRequests.isSelectionEmpty())
			return;
		for(ServiceRequest req:activeServiceList)
		{
			if(listActiveServiceRequests.getSelectedValue().equals(req.getCustomerName()+" - "+req.getVehicleNumber()))
			{
				this.selServiceRequest=req;
				displayServiceRequestDetails(req);
				break;
			}
		}
	}
	
	private void listServiceListValueChanged(ListSelectionEvent arg0)
	{
		if(selServiceRequest!=null&&!listServiceList.isSelectionEmpty())
		{
			if(sparePartListModel.size()!=0)
				sparePartListModel.removeAllElements();
			lblServiceDesc.setText("Oil Service");
			this.service=null;
			for(Service s : selServiceRequest.getServiceList())
			{
				if(s instanceof MaintenanceService&&listServiceList.getSelectedValue().equals("[M] "+s.getDesc()+" - "+((MaintenanceService)s).getLaborCharges()))
				{
					this.service=s;
					lblServiceDesc.setText("Parts Changed: "+((MaintenanceService)s).getPartList().size());
					for(SparePart sp:((MaintenanceService)s).getPartList())
						sparePartListModel.addElement(sp.getDesc()+" - "+sp.getRate());
				}
			}
		}
	}

	private void serviceStationFrameWindowClosing(WindowEvent e)
	{
		if(!activeServiceList.isEmpty())
    	{
			JOptionPane.showMessageDialog(this, "First finish active services!!!");
			setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    	}
		else
			setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	    
	}
	
	public void setSelServiceRequest(ServiceRequest selServiceRequest) {
		this.selServiceRequest = selServiceRequest;
	}
	
	public void setService(Service service) {
		this.service = service;
	}
	private void showJFrame()
	{
		this.setLayout(null);
		this.setSize(945,400);
		this.setLocation(170, 170);
		this.setVisible(true);
	}
	private void tblBillsValueChanged(ListSelectionEvent arg0)
	{
		serviceListModel.removeAllElements();
		sparePartListModel.removeAllElements();
		lblCustomerName.setText("Name");
		lblVehNumber.setText("Vehicle");
		lblServiceDesc.setText("");
		if (tblBills.getSelectedRow()>=0&&billsTableModel.getRowCount() > 0) 
			for(Bill b:ServiceStation.station.getBillList())
				if(billsTableModel.getValueAt(tblBills.getSelectedRow(), 0).equals(b.getRequest().getCustomerName()+" - "+b.getRequest().getVehicleNumber()))
					displayServiceRequestDetails(b.getRequest());
	}

}
