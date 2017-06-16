package vehicleServiceStation;

import java.io.Serializable;

public class SparePart implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String desc;
	public double rate;
	public SparePart(String desc, double rate) {
		super();
		this.desc = desc;
		this.rate = rate;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SparePart other = (SparePart) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (Double.doubleToLongBits(rate) != Double
				.doubleToLongBits(other.rate))
			return false;
		return true;
	}
	public String getDesc() {
		return desc;
	}
	public double getRate() {
		return rate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return "SparePart [desc=" + desc + ", rate=" + rate + "]";
	}
}
