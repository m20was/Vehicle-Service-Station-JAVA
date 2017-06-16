package vehicleServiceStation;

import java.io.Serializable;

public class Vehicle implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String company;
	public String model;
	public String number;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	public String getCompany() {
		return company;
	}
	public String getModel() {
		return model;
	}
	public String getNumber() {
		return number;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Vehicle [company=" + company + ", model=" + model + ", number="
				+ number + "]";
	}
}
