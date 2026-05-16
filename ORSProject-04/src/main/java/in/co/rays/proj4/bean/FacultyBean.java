package in.co.rays.proj4.bean;

import java.util.Date;

/**
 * FacultyBean represents faculty information in the application.
 * <p>
 * It contains personal, academic, and contact details of faculty members. The
 * bean also stores mapping information related to:
 * <ul>
 * <li>College</li>
 * <li>Course</li>
 * <li>Subject</li>
 * </ul>
 * 
 * This bean extends {@link BaseBean} to inherit common properties like ID,
 * created/modified information, and timestamps.
 * 
 * @author Harshit
 */
public class FacultyBean extends BaseBean {

	/**
	 * First name of faculty member.
	 */
	private String firstname;

	/**
	 * Last name of faculty member.
	 */
	private String lastName;

	/**
	 * Date of birth of faculty member.
	 */
	private Date dob;

	/**
	 * Gender of faculty member.
	 */
	private String gender;

	/**
	 * Mobile number of faculty member.
	 */
	private String mobileNo;

	/**
	 * Email address of faculty member.
	 */
	private String email;

	/**
	 * College ID associated with faculty.
	 */
	private long collegeId;

	/**
	 * College name associated with faculty.
	 */
	private String collegeName;

	/**
	 * Course ID associated with faculty.
	 */
	private long courseId;

	/**
	 * Course name associated with faculty.
	 */
	private String courseName;

	/**
	 * Subject ID associated with faculty.
	 */
	private long subjectId;

	/**
	 * Subject name associated with faculty.
	 */
	private String subjectName;

	/**
	 * Returns faculty first name.
	 * 
	 * @return first name
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets faculty first name.
	 * 
	 * @param firstname first name
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Returns faculty last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets faculty last name.
	 * 
	 * @param lastName last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * Returns email address.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email address.
	 * 
	 * @param email email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns college ID.
	 * 
	 * @return college ID
	 */
	public long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets college ID.
	 * 
	 * @param collegeId college ID
	 */
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Returns college name.
	 * 
	 * @return college name
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * Sets college name.
	 * 
	 * @param collegeName college name
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * Returns course ID.
	 * 
	 * @return course ID
	 */
	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets course ID.
	 * 
	 * @param courseId course ID
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Returns course name.
	 * 
	 * @return course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets course name.
	 * 
	 * @param courseName course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Returns subject ID.
	 * 
	 * @return subject ID
	 */
	public long getSubjectId() {
		return subjectId;
	}

	/**
	 * Sets subject ID.
	 * 
	 * @param subjectId subject ID
	 */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Returns subject name.
	 * 
	 * @return subject name
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * Sets subject name.
	 * 
	 * @param subjectName subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * Returns display value for dropdown lists.
	 * 
	 * @return faculty first name
	 */
	@Override
	public String getValue() {
		return firstname;
	}

}