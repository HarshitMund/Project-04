package in.co.rays.proj4.bean;

public class MobileBean extends BaseBean {

	private String beandName;
	private String mobileName;
	private int raw;
	private double price;

	public String getBeandName() {
		return beandName;
	}

	public void setBeandName(String beandName) {
		this.beandName = beandName;
	}

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public int getRaw() {
		return raw;
	}

	public void setRaw(int raw) {
		this.raw = raw;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String getValue() {
		return null;
	}

}
