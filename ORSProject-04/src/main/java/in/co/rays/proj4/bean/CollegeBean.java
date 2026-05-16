package in.co.rays.proj4.bean;

/**
 * CollegeBean represents College information in the application.
 * <p>
 * It contains details related to a college such as:
 * <ul>
 * <li>College name</li>
 * <li>Address</li>
 * <li>State</li>
 * <li>City</li>
 * <li>Phone number</li>
 * </ul>
 * 
 * This bean extends {@link BaseBean} to inherit common properties
 * like ID, created/modified information, and timestamps.
 * 
 * @author Harshit
 */
public class CollegeBean extends BaseBean {

	/**
	 * Name of the college.
	 */
	private String name;

	/**
	 * Address of the college.
	 */
	private String address;

	/**
	 * State where the college is located.
	 */
	private String state;

	/**
	 * City where the college is located.
	 */
	private String city;

	/**
	 * Contact phone number of the college.
	 */
	private String phoneNo;

	/**
	 * Returns college name.
	 * 
	 * @return college name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets college name.
	 * 
	 * @param name college name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns college address.
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets college address.
	 * 
	 * @param address college address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns state name.
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets state name.
	 * 
	 * @param state state name
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns city name.
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets city name.
	 * 
	 * @param city city name
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Returns college phone number.
	 * 
	 * @return phone number
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets college phone number.
	 * 
	 * @param phoneNo phone number
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Returns college name as display value for dropdown lists.
	 * 
	 * @return college name
	 */
	@Override
	public String getValue() {
		return name;
	}

}