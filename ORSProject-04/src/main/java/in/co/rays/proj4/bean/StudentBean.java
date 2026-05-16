package in.co.rays.proj4.bean;

import java.util.Date;

/**
 * StudentBean represents student information in the application.
 * <p>
 * It contains personal, academic, and contact details of students. The bean
 * also stores college-related information associated with the student.
 * 
 * This bean extends {@link BaseBean} to inherit common properties like ID,
 * created/modified information, and timestamps.
 * 
 * @author Harshit
 */
public class StudentBean extends BaseBean {

	/**
	 * First name of the student.
	 */
	private String firstName;

	/**
	 * Last name of the student.
	 */
	private String lastName;

	/**
	 * Date of birth of the student.
	 */
	private Date dob;

	/**
	 * Gender of the student.
	 */
	private String gender;

	/**
	 * Mobile number of the student.
	 */
	private String mobileNo;

	/**
	 * Email address of the student.
	 */
	private String email;

	/**
	 * College ID associated with the student.
	 */
	private long collegeId;

	/**
	 * College name associated with the student.
	 */
	private String collegeName;

	/**
	 * Returns student's first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets student's first name.
	 * 
	 * @param firstName first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns student's last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets student's last name.
	 * 
	 * @param lastName last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns student's date of birth.
	 * 
	 * @return date of birth
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets student's date of birth.
	 * 
	 * @param dob date of birth
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Returns student's gender.
	 * 
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets student's gender.
	 * 
	 * @param gender gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns student's mobile number.
	 * 
	 * @return mobile number
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets student's mobile number.
	 * 
	 * @param mobileNo mobile number
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Returns student's email address.
	 * 
	 * @return email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets student's email address.
	 * 
	 * @param email email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns college ID associated with student.
	 * 
	 * @return college ID
	 */
	public long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets college ID associated with student.
	 * 
	 * @param collegeId college ID
	 */
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Returns college name associated with student.
	 * 
	 * @return college name
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * Sets college name associated with student.
	 * 
	 * @param collegeName college name
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * Returns student's full name as display value for dropdown lists.
	 * 
	 * @return full name of student
	 */
	@Override
	public String getValue() {
		return firstName + " " + lastName;
	}

}