package vehicleServiceStation;

import java.io.Serializable;

public class OilService extends Service implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double cost;

	public double getCost() {
		return cost;
	}

	public double price()
	{
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "OilService [cost=" + cost + ", desc=" + desc + "]";
	}
}
