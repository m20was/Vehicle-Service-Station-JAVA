package vehicleServiceStation;

import java.io.Serializable;
import java.util.LinkedList;

public class ServiceRequest implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Service> serviceList;
	private String customerName;
	private String vehicleNumber;
	public ServiceRequest() {
		serviceList=new LinkedList<Service>();
	}
	public ServiceRequest(String customerName, String vehicleNumber) {
		super();
		serviceList=new LinkedList<Service>();
		this.customerName = customerName;
		this.vehicleNumber = vehicleNumber;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceRequest other = (ServiceRequest) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (serviceList == null) {
			if (other.serviceList != null)
				return false;
		} else if (!serviceList.equals(other.serviceList))
			return false;
		if (vehicleNumber == null) {
			if (other.vehicleNumber != null)
				return false;
		} else if (!vehicleNumber.equals(other.vehicleNumber))
			return false;
		return true;
	}
	public String getCustomerName() {
		return customerName;
	}
	public LinkedList<Service> getServiceList() {
		return serviceList;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result
				+ ((serviceList == null) ? 0 : serviceList.hashCode());
		result = prime * result
				+ ((vehicleNumber == null) ? 0 : vehicleNumber.hashCode());
		return result;
	}
	public void newService(Service s)
	{
		this.serviceList.add(s);
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setServiceList(LinkedList<Service> serviceList) {
		this.serviceList = serviceList;
	}
	
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	@Override
	public String toString() {
		return "ServiceRequest [serviceList=" + serviceList + ", customerName="
				+ customerName + ", vehicleNumber=" + vehicleNumber + "]";
	}
}
