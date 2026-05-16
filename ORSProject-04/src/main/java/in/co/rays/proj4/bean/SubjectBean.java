package in.co.rays.proj4.bean;

/**
 * SubjectBean represents subject information in the application.
 * <p>
 * It contains details related to a subject such as:
 * <ul>
 * <li>Subject name</li>
 * <li>Associated course ID</li>
 * <li>Associated course name</li>
 * <li>Subject description</li>
 * </ul>
 * 
 * This bean extends {@link BaseBean} to inherit common properties like ID,
 * created/modified information, and timestamps.
 * 
 * @author Harshit
 */
public class SubjectBean extends BaseBean {

	/**
	 * Name of the subject.
	 */
	private String name;

	/**
	 * Course ID associated with the subject.
	 */
	private long courseId;

	/**
	 * Course name associated with the subject.
	 */
	private String courseName;

	/**
	 * Description of the subject.
	 */
	private String description;

	/**
	 * Returns subject name.
	 * 
	 * @return subject name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets subject name.
	 * 
	 * @param name subject name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Returns subject description.
	 * 
	 * @return subject description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets subject description.
	 * 
	 * @param description subject description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns subject name as display value for dropdown lists.
	 * 
	 * @return subject name
	 */
	@Override
	public String getValue() {
		return name;
	}

}