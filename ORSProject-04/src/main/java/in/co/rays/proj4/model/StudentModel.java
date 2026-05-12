package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class StudentModel {

	public Long nextPk() throws DatabaseException {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_student");
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

	public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		long pk = 0;
		Connection conn = null;

		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		StudentBean duplicateBean = findByEmail(bean.getEmail());
		if (duplicateBean != null)
			throw new DuplicateRecordException("Email already exist");

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_student values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setDate(4, new Date(bean.getDob().getTime()));
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setLong(8, bean.getCollegeId());
			pstmt.setString(9, bean.getCollegeName());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());

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
			throw new ApplicationException("Exception: Exception in add student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		StudentBean duplicateBean = findByEmail(bean.getEmail());
		if (duplicateBean != null)
			throw new DuplicateRecordException("Email already exist");

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_student set first_name = ?, last_name = ?, dob = ?, gender = ?, mobile_no = ?, email = ?, college_id = ?, college_name = ?,"
							+ " created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setDate(3, new Date(bean.getDob().getTime()));
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getEmail());
			pstmt.setLong(7, bean.getCollegeId());
			pstmt.setString(8, bean.getCollegeName());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception: Exception update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception: Exception in update student");
		}
	}

	public void delete(StudentBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_student where id = ?");
			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception: Exception delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception: Exception in delete student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public StudentBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		StudentBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_student where id = ?");
			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in getting student by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public StudentBean findByEmail(String email) throws ApplicationException {

		Connection conn = null;
		StudentBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_student where email = ?");
			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in getting student by email");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List<StudentBean> search(StudentBean bean, int pageNo, int pageSize) throws ApplicationException {

		List<StudentBean> list = new ArrayList<StudentBean>();
		Connection conn = null;

		StringBuffer sb = new StringBuffer("select * from st_student where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id  = " + bean.getId());

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0)
				sb.append(" and first_name like '" + bean.getFirstName() + "%'");

			if (bean.getLastName() != null && bean.getLastName().length() > 0)
				sb.append(" and last_name like '" + bean.getLastName() + "%'");

			if (bean.getDob() != null && bean.getDob().getDate() > 0)
				sb.append(" and dob like '" + new Date(bean.getDob().getTime()) + "%'");

			if (bean.getGender() != null && bean.getGender().length() > 0)
				sb.append(" and gender like '" + bean.getGender() + "%'");

			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0)
				sb.append(" and mobile_no like '" + bean.getMobileNo() + "%'");

			if (bean.getEmail() != null && bean.getEmail().length() > 0)
				sb.append(" and email like '" + bean.getEmail() + "%'");

			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0)
				sb.append(" and college_name like '" + bean.getCollegeName() + "%'");
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
				bean = new StudentBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));

				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception: Exception in search student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}
	
	public List<StudentBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

}
