package in.co.rays.proj4.bean;

import java.sql.Timestamp;

public class BaseBean {

	protected long id;
	protected String createdBy;
	protected String modifiedBy;
	protected Timestamp createdDatatime;
	protected Timestamp modifiedDatetime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDatatime() {
		return createdDatatime;
	}

	public void setCreatedDatatime(Timestamp createdDatatime) {
		this.createdDatatime = createdDatatime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

}
