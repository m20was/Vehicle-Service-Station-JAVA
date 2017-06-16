package vehicleServiceStation;

import java.io.Serializable;
import java.util.HashMap;

public class Customer implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String,Vehicle> vehList;
	private String name;
	private String mobile;
	private String address;	
	private double lastBalance;
	public Customer() 
	{
		this.vehList=new HashMap<String, Vehicle>();
		this.lastBalance=0;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (Double.doubleToLongBits(lastBalance) != Double
				.doubleToLongBits(other.lastBalance))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (vehList == null) {
			if (other.vehList != null)
				return false;
		} else if (!vehList.equals(other.vehList))
			return false;
		return true;
	}
	
	public String getAddress() {
		return address;
	}
	public double getLastBalance() {
		return lastBalance;
	}
	public String getMobile() {
		return mobile;
	}
	public String getName() {
		return name;
	}
	public Vehicle getVehicle(String number)
	{
		return vehList.get(number);
	}
	public HashMap<String,Vehicle> getVehicleList()
	{
		return this.vehList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		long temp;
		temp = Double.doubleToLongBits(lastBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((vehList == null) ? 0 : vehList.hashCode());
		return result;
	}
	public void newVehicle(Vehicle v)
	{
		this.vehList.put(v.number, v);
	}
	public double payBill(Bill b,double x)
	{
		b.setPaidAmount(x);
		this.setLastBalance(this.getLastBalance()+b.computeTotalBill()-x);
		return this.getLastBalance();
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setLastBalance(double lastBalance) {
		this.lastBalance = lastBalance;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobile=" + mobile + ", address="
				+ address + ", lastBalance=" + lastBalance + "]";
	}
}
