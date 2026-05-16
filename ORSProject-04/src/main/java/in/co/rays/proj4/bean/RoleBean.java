package in.co.rays.proj4.bean;

/**
 * RoleBean represents role information in the application.
 * <p>
 * It is used to define different user roles and permissions
 * within the system.
 * 
 * Predefined roles include:
 * <ul>
 * <li>Admin</li>
 * <li>Student</li>
 * <li>College</li>
 * <li>Kiosk</li>
 * <li>Faculty</li>
 * </ul>
 * 
 * This bean extends {@link BaseBean} to inherit common properties
 * like ID, created/modified information, and timestamps.
 * 
 * @author Harshit
 */
public class RoleBean extends BaseBean {

	/**
	 * Constant representing Admin role.
	 */
	public static final int ADMIN = 1;

	/**
	 * Constant representing Student role.
	 */
	public static final int STUDENT = 2;

	/**
	 * Constant representing College role.
	 */
	public static final int COLLEGE = 3;

	/**
	 * Constant representing Kiosk role.
	 */
	public static final int KIOSK = 4;

	/**
	 * Constant representing Faculty role.
	 */
	public static final int FACULTY = 5;

	/**
	 * Name of the role.
	 */
	private String name;

	/**
	 * Description of the role.
	 */
	private String description;

	/**
	 * Returns role name.
	 * 
	 * @return role name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets role name.
	 * 
	 * @param name role name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns role description.
	 * 
	 * @return role description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets role description.
	 * 
	 * @param description role description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns role name as display value for dropdown lists.
	 * 
	 * @return role name
	 */
	@Override
	public String getValue() {
		return name;
	}

}