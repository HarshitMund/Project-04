package in.co.rays.proj4.bean;

import java.util.Date;

public class GymBean extends BaseBean {

	private String memberName;
	private String trainerName;
	private double fee;
	private Date joiningDate;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
