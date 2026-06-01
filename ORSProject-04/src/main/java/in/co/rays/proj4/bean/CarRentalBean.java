package in.co.rays.proj4.bean;

public class CarRentalBean extends BaseBean {

	private String name;
	private String carModel;
	private double rentpayDay;
	private String fuelType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public double getRentpayDay() {
		return rentpayDay;
	}

	public void setRentpayDay(double rentpayDay) {
		this.rentpayDay = rentpayDay;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
