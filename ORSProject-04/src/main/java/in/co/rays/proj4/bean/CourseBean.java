package in.co.rays.proj4.bean;

/**
 * CourseBean represents course information in the application.
 * <p>
 * It contains details related to a course such as:
 * <ul>
 * <li>Course name</li>
 * <li>Course duration</li>
 * <li>Course description</li>
 * </ul>
 * 
 * This bean extends {@link BaseBean} to inherit common properties like ID,
 * created/modified information, and timestamps.
 * 
 * @author Harshit
 */
public class CourseBean extends BaseBean {

	/**
	 * Name of the course.
	 */
	private String name;

	/**
	 * Duration of the course.
	 */
	private String duration;

	/**
	 * Description of the course.
	 */
	private String description;

	/**
	 * Returns course name.
	 * 
	 * @return course name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets course name.
	 * 
	 * @param name course name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns course duration.
	 * 
	 * @return duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets course duration.
	 * 
	 * @param duration course duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Returns course description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets course description.
	 * 
	 * @param description course description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns course name as display value for dropdown lists.
	 * 
	 * @return course name
	 */
	@Override
	public String getValue() {
		return name;
	}

}