package in.co.rays.proj4.bean;

/**
 * MarksheetBean represents student marksheet information in the application.
 * <p>
 * It contains academic details of a student such as:
 * <ul>
 * <li>Roll number</li>
 * <li>Student ID</li>
 * <li>Student name</li>
 * <li>Physics marks</li>
 * <li>Chemistry marks</li>
 * <li>Mathematics marks</li>
 * </ul>
 * 
 * This bean extends {@link BaseBean} to inherit common properties like ID,
 * created/modified information, and timestamps.
 * 
 * @author Harshit
 */
public class MarksheetBean extends BaseBean {

	/**
	 * Roll number of the student.
	 */
	private String rollNo;

	/**
	 * Student ID associated with marksheet.
	 */
	private long studentId;

	/**
	 * Name of the student.
	 */
	private String name;

	/**
	 * Marks obtained in Physics.
	 */
	private int physics;

	/**
	 * Marks obtained in Chemistry.
	 */
	private int chemistry;

	/**
	 * Marks obtained in Mathematics.
	 */
	private int maths;

	/**
	 * Returns roll number.
	 * 
	 * @return roll number
	 */
	public String getRollNo() {
		return rollNo;
	}

	/**
	 * Sets roll number.
	 * 
	 * @param rollNo roll number
	 */
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	/**
	 * Returns student ID.
	 * 
	 * @return student ID
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * Sets student ID.
	 * 
	 * @param studentId student ID
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	/**
	 * Returns student name.
	 * 
	 * @return student name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets student name.
	 * 
	 * @param name student name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns Physics marks.
	 * 
	 * @return physics marks
	 */
	public int getPhysics() {
		return physics;
	}

	/**
	 * Sets Physics marks.
	 * 
	 * @param physics physics marks
	 */
	public void setPhysics(int physics) {
		this.physics = physics;
	}

	/**
	 * Returns Chemistry marks.
	 * 
	 * @return chemistry marks
	 */
	public int getChemistry() {
		return chemistry;
	}

	/**
	 * Sets Chemistry marks.
	 * 
	 * @param chemistry chemistry marks
	 */
	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	/**
	 * Returns Mathematics marks.
	 * 
	 * @return mathematics marks
	 */
	public int getMaths() {
		return maths;
	}

	/**
	 * Sets Mathematics marks.
	 * 
	 * @param maths mathematics marks
	 */
	public void setMaths(int maths) {
		this.maths = maths;
	}

	/**
	 * Returns display value for dropdown lists.
	 * 
	 * @return student name
	 */
	@Override
	public String getValue() {
		return name;
	}

}