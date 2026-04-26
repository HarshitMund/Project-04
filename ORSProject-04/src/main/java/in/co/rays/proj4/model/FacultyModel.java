package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class FacultyModel {

	public Long nextPk() throws DatabaseException {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_faculty");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception: Exception in getting next PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}

	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		long pk = 0;

		FacultyBean duplicateBean = findByEmail(bean.getEmail());
		if (duplicateBean != null)
			throw new DuplicateRecordException("Email already exist");

		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPk(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getName());

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into st_faculty values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getFirstname());
			pstmt.setString(3, bean.getLastName());
			pstmt.setDate(4, new Date(bean.getDob().getTime()));
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setLong(8, bean.getCollegeId());
			pstmt.setString(9, bean.getCollegeName());
			pstmt.setLong(10, bean.getCourseId());
			pstmt.setString(11, bean.getCourseName());
			pstmt.setLong(12, bean.getSubjectId());
			pstmt.setString(13, bean.getSubjectName());
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception: Add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception: Exception in add faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		FacultyBean duplicateBean = findByEmail(bean.getEmail());
		if (duplicateBean != null)
			throw new DuplicateRecordException("Email already exist");

		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPk(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_faculty set first_name = ?, last_name = ?, dob = ?, gender = ?, mobile_no = ?, email = ?, "
							+ "college_id = ?, college_name = ?, course_id = ?, course_name = ?, subject_id = ?, "
							+ "subject_name = ?, created_by = ?, modified_by = ?, created_datetime = ?, "
							+ "modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getFirstname());
			pstmt.setString(2, bean.getLastName());
			pstmt.setDate(3, new Date(bean.getDob().getTime()));
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getEmail());
			pstmt.setLong(7, bean.getCollegeId());
			pstmt.setString(8, bean.getCollegeName());
			pstmt.setLong(9, bean.getCourseId());
			pstmt.setString(10, bean.getCourseName());
			pstmt.setLong(11, bean.getSubjectId());
			pstmt.setString(12, bean.getSubjectName());
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
			pstmt.setLong(17, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception: update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception: Exception in update faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(FacultyBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_faculty where id = ?");
			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception: delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception: Exception in delete faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public FacultyBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		FacultyBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty  where id = ?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstname(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCourseId(rs.getLong(10));
				bean.setCourseName(rs.getString(11));
				bean.setSubjectId(rs.getLong(12));
				bean.setSubjectName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in getting subject by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public FacultyBean findByEmail(String email) throws ApplicationException {

		Connection conn = null;
		FacultyBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty  where email = ?");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstname(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCourseId(rs.getLong(10));
				bean.setCourseName(rs.getString(11));
				bean.setSubjectId(rs.getLong(12));
				bean.setSubjectName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in getting subject by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List<FacultyBean> search(FacultyBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<FacultyBean> list = new ArrayList<FacultyBean>();

		StringBuffer sb = new StringBuffer("select * from st_faculty where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());

			if (bean.getCollegeId() > 0)
				sb.append(" and college_id = " + bean.getCollegeId());

			if (bean.getCourseId() > 0)
				sb.append(" and course_id = " + bean.getCourseId());

			if (bean.getSubjectId() > 0)
				sb.append(" and student_id = " + bean.getSubjectId());

			if (bean.getFirstname() != null && bean.getFirstname().length() > 0)
				sb.append(" and first_name like '" + bean.getFirstname() + "%'");

			if (bean.getLastName() != null && bean.getLastName().length() > 0)
				sb.append(" and last_name like '" + bean.getLastName() + "%'");

			if (bean.getEmail() != null && bean.getEmail().length() > 0)
				sb.append(" and email like '" + bean.getEmail() + "%'");

			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0)
				sb.append(" and mobile_no like '" + bean.getMobileNo() + "%'");

			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0)
				sb.append(" and college_name like '" + bean.getCollegeName() + "%'");

			if (bean.getCourseName() != null && bean.getCourseName().length() > 0)
				sb.append(" and course_name like '" + bean.getCourseName() + "%'");

			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0)
				sb.append(" and subject_name like '" + bean.getSubjectName() + "%'");
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo + ", " + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstname(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCourseId(rs.getLong(10));
				bean.setCourseName(rs.getString(11));
				bean.setSubjectId(rs.getLong(12));
				bean.setSubjectName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

}
