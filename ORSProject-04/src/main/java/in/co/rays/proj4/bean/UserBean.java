package in.co.rays.proj4.bean;

import java.util.Date;

/**
 * UserBean represents user information in the application.
 * <p>
 * It contains personal, login, and role-related details of users. The bean is
 * used for user registration, authentication, and profile management.
 * 
 * This bean extends {@link BaseBean} to inherit common properties like ID,
 * created/modified information, and timestamps.
 * 
 * @author Harshit
 */
public class UserBean extends BaseBean {

	/**
	 * First name of the user.
	 */
	private String firstName;

	/**
	 * Last name of the user.
	 */
	private String lastName;

	/**
	 * Login ID or username of the user.
	 */
	private String login;

	/**
	 * Password of the user.
	 */
	private String password;

	/**
	 * Confirm password entered by the user.
	 */
	private String confirmPassword;

	/**
	 * Date of birth of the user.
	 */
	private Date dob;

	/**
	 * Mobile number of the user.
	 */
	private String mobileNo;

	/**
	 * Role ID assigned to the user.
	 */
	private long roleId;

	/**
	 * Gender of the user.
	 */
	private String gender;

	/**
	 * Returns user's first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets user's first name.
	 * 
	 * @param firstName first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns user's last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets user's last name.
	 * 
	 * @param lastName last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns login ID.
	 * 
	 * @return login ID
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets login ID.
	 * 
	 * @param login login ID
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Returns password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 * 
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns confirm password.
	 * 
	 * @return confirm password
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * Sets confirm password.
	 * 
	 * @param confirmPassword confirm password
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * Returns date of birth.
	 * 
	 * @return date of birth
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets date of birth.
	 * 
	 * @param dob date of birth
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Returns mobile number.
	 * 
	 * @return mobile number
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets mobile number.
	 * 
	 * @param mobileNo mobile number
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Returns role ID.
	 * 
	 * @return role ID
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * Sets role ID.
	 * 
	 * @param roleId role ID
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Returns gender.
	 * 
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets gender.
	 * 
	 * @param gender gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns user's first name as display value for dropdown lists.
	 * 
	 * @return first name
	 */
	@Override
	public String getValue() {
		return firstName;
	}

}