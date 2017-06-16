package vehicleServiceStation;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final double taxPercent=12.6;
	private Date billDate;
	private ServiceRequest request;
	private double amount;
	private double paidAmount;
	public double computeAmount()
	{
		double a=0;
		for(Service s :this.request.getServiceList())
			a+=s.price();
		this.amount=a;
		return a;
	}
	public double computeTax()
	{
		return this.amount*(Bill.taxPercent/100);
	}
	public double computeTotalBill()
	{
		return this.amount+computeTax();
	}
	public double getAmount() {
		return amount;
	}
	public Date getBillDate() {
		return billDate;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	
	public ServiceRequest getRequest() {
		return request;
	}
	public void print(OutputStream out)
	{
		PrintStream pr=new PrintStream(out);
		pr.println("*************************************************");
		pr.println("Date: "+new SimpleDateFormat("dd/mm/yyyy").format(this.billDate));
		pr.println("Customer Name: "+this.getRequest().getCustomerName());
		pr.println("Vehicle No.: "+this.getRequest().getVehicleNumber());
		pr.println("*************************************************");
		for(Service s:this.getRequest().getServiceList())
		{
			if(s instanceof OilService)
			{
				pr.println("Oil Name: "+s.getDesc());
				OilService o=(OilService)s;
				pr.println("Price: "+String.format("%.2f", o.getCost()));
			}
			else
			{
				pr.println("Maintenance Description: "+s.getDesc());
				MaintenanceService m=(MaintenanceService)s;
				pr.println("Labor Charges: "+String.format("%.2f", m.getLaborCharges()));
				for(SparePart p:m.getPartList())
					pr.println(p.toString());
				pr.println("Sub Total: "+String.format("%.2f", m.price()));
			}
		}
		pr.println("*************************************************");
		pr.println("Amount: "+String.format("%.2f", computeAmount()));
		pr.println("Tax: "+String.format("%.2f", computeTax()));
		pr.println("Total Amount: "+String.format("%.2f", computeTotalBill()));
		pr.println("Previous Balance: "+String.format("%.2f", ServiceStation.station.findCustomer(getRequest().getCustomerName()).getLastBalance()));
		pr.println("Final Total: "+String.format("%.2f", (computeTotalBill()+ServiceStation.station.findCustomer(getRequest().getCustomerName()).getLastBalance())));
		pr.println("*************************************************");
		
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public void setRequest(ServiceRequest request) {
		this.request = request;
	}
	
	@Override
	public String toString() {
		return "Bill [billDate=" + billDate + ", request=" + request
				+ ", amount=" + amount + ", paidAmount=" + paidAmount + "]";
	}
}

