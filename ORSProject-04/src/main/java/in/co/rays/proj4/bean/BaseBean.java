package in.co.rays.proj4.bean;

import java.sql.Timestamp;

/**
 * BaseBean is an abstract parent class for all DTO/Bean classes.
 * <p>
 * It contains common properties used in all modules such as:
 * <ul>
 * <li>Primary key ID</li>
 * <li>Created by user</li>
 * <li>Modified by user</li>
 * <li>Creation date and time</li>
 * <li>Modification date and time</li>
 * </ul>
 * 
 * This class also implements {@link DropdownListBean} so that child
 * classes can be used in dropdown lists.
 * 
 * @author Harshit
 */
public abstract class BaseBean implements DropdownListBean {

	/**
	 * Unique identifier of the record.
	 */
	protected long id;

	/**
	 * Name or login ID of the user who created the record.
	 */
	protected String createdBy;

	/**
	 * Name or login ID of the user who modified the record.
	 */
	protected String modifiedBy;

	/**
	 * Date and time when the record was created.
	 */
	protected Timestamp createdDatetime;

	/**
	 * Date and time when the record was last modified.
	 */
	protected Timestamp modifiedDatetime;

	/**
	 * Returns record ID.
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets record ID.
	 * 
	 * @param id record ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Returns creator name.
	 * 
	 * @return createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets creator name.
	 * 
	 * @param createdBy creator name
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Returns modifier name.
	 * 
	 * @return modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets modifier name.
	 * 
	 * @param modifiedBy modifier name
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Returns creation date and time.
	 * 
	 * @return createdDatetime
	 */
	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	/**
	 * Sets creation date and time.
	 * 
	 * @param createdDatetime creation timestamp
	 */
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	/**
	 * Returns modification date and time.
	 * 
	 * @return modifiedDatetime
	 */
	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	/**
	 * Sets modification date and time.
	 * 
	 * @param modifiedDatetime modification timestamp
	 */
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	/**
	 * Returns ID as key for dropdown lists.
	 * 
	 * @return key value
	 */
	@Override
	public String getKey() {
		return id + "";
	}

}