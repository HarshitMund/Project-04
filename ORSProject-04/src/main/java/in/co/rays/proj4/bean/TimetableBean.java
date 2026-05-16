package in.co.rays.proj4.bean;

import java.util.Date;

/**
 * TimetableBean represents examination timetable information in the
 * application.
 * <p>
 * It contains details related to examination schedules such as:
 * <ul>
 * <li>Semester</li>
 * <li>Exam description</li>
 * <li>Exam date</li>
 * <li>Exam time</li>
 * <li>Associated course details</li>
 * <li>Associated subject details</li>
 * </ul>
 * 
 * This bean extends {@link BaseBean} to inherit common properties like ID,
 * created/modified information, and timestamps.
 * 
 * @author Harshit
 */
public class TimetableBean extends BaseBean {

	/**
	 * Semester of the examination.
	 */
	private String semester;

	/**
	 * Description of the examination timetable.
	 */
	private String description;

	/**
	 * Date of the examination.
	 */
	private Date examDate;

	/**
	 * Time of the examination.
	 */
	private String examTime;

	/**
	 * Course ID associated with the timetable.
	 */
	private long courseId;

	/**
	 * Course name associated with the timetable.
	 */
	private String courseName;

	/**
	 * Subject ID associated with the timetable.
	 */
	private long subjectId;

	/**
	 * Subject name associated with the timetable.
	 */
	private String subjectName;

	/**
	 * Returns semester.
	 * 
	 * @return semester
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * Sets semester.
	 * 
	 * @param semester semester
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * Returns timetable description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets timetable description.
	 * 
	 * @param description timetable description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns examination date.
	 * 
	 * @return exam date
	 */
	public Date getExamDate() {
		return examDate;
	}

	/**
	 * Sets examination date.
	 * 
	 * @param examDate examination date
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	/**
	 * Returns examination time.
	 * 
	 * @return exam time
	 */
	public String getExamTime() {
		return examTime;
	}

	/**
	 * Sets examination time.
	 * 
	 * @param examTime examination time
	 */
	public void setExamTime(String examTime) {
		this.examTime = examTime;
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
	 * Returns course name as display value for dropdown lists.
	 * 
	 * @return course name
	 */
	@Override
	public String getValue() {
		return courseName;
	}

}