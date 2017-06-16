package vehicleServiceStation;

import java.io.Serializable;
import java.util.LinkedList;

public class MaintenanceService extends Service implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<SparePart> partList;
	private double laborCharges;
	//public static long serialVersionUID;
	
	public MaintenanceService() {
		super();
		// TODO Auto-generated constructor stub
		partList=new LinkedList<SparePart>();
	}
	
	public double getLaborCharges() {
		return laborCharges;
	}

	public LinkedList<SparePart> getPartList() {
		return partList;
	}
	public void newSparePart(SparePart s)
	{
		this.partList.add(s);
	}
	public double price()
	{
		double p=this.laborCharges;
		for(SparePart s : partList)
			p+=s.getRate();
		return p;
	}
	public void setLaborCharges(double laborCharges) {
		this.laborCharges = laborCharges;
	}
	
	public void setPartList(LinkedList<SparePart> partList) {
		this.partList = partList;
	}
	@Override
	public String toString() {
		return "MaintenanceService [partList=" + partList + ", laborCharges="
				+ laborCharges + ", desc=" + desc + "]";
	}
}
