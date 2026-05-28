package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CollegeSystemBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CollegeSystemModel {

	public long nextPk() throws DatabaseException {
		long pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from collegesystem");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public long add(CollegeSystemBean bean) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		Connection conn = null;

		CollegeSystemBean existBean = findByStudentName(bean.getStudentName());
		if (existBean != null)
			throw new DuplicateRecordException("Student Name Already Exist");

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into collegesystem values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getStudentName());
			pstmt.setString(3, bean.getBranch());
			pstmt.setInt(4, bean.getSemester());
			pstmt.setDouble(5, bean.getCgpa());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (Rows inserted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : Add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in adding college system record");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(CollegeSystemBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		CollegeSystemBean existBean = findByStudentName(bean.getStudentName());
		if (existBean != null && existBean.getId() != bean.getId())
			throw new DuplicateRecordException("Student Name Already Exist");

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update collegesystem set student_name = ?, branch = ?, semester = ?, cgpa = ? where id = ?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getStudentName());
			pstmt.setString(2, bean.getBranch());
			pstmt.setInt(3, bean.getSemester());
			pstmt.setDouble(4, bean.getCgpa());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (Rows update)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in updating college system record");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(CollegeSystemBean bean) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from collegesystem where id = ?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (Rows delete)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in deleting college system record");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public CollegeSystemBean findById(long id) throws ApplicationException {
		CollegeSystemBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from collegesystem where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeSystemBean();
				bean.setId(rs.getLong(1));
				bean.setStudentName(rs.getString(2));
				bean.setBranch(rs.getString(3));
				bean.setSemester(rs.getInt(4));
				bean.setCgpa(rs.getDouble(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting record by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public CollegeSystemBean findByStudentName(String name) throws ApplicationException {
		CollegeSystemBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from collegesystem where student_name = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeSystemBean();
				bean.setId(rs.getLong(1));
				bean.setStudentName(rs.getString(2));
				bean.setBranch(rs.getString(3));
				bean.setSemester(rs.getInt(4));
				bean.setCgpa(rs.getDouble(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting record by student name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<CollegeSystemBean> search(CollegeSystemBean bean, int pageNo, int pageSize)
			throws ApplicationException {
		Connection conn = null;
		List<CollegeSystemBean> list = new ArrayList<CollegeSystemBean>();
		StringBuffer sb = new StringBuffer("select * from collegesystem where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());
			if (bean.getStudentName() != null && bean.getStudentName().length() > 0)
				sb.append(" and student_name like '" + bean.getStudentName() + "%'");
			if (bean.getBranch() != null && bean.getBranch().length() > 0)
				sb.append(" and branch like '" + bean.getBranch() + "%'");
			if (bean.getSemester() > 0)
				sb.append(" and semester = " + bean.getSemester());
			if (bean.getCgpa() > 0)
				sb.append(" and cgpa = " + bean.getCgpa());
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
				CollegeSystemBean systemBean = new CollegeSystemBean();
				systemBean.setId(rs.getLong(1));
				systemBean.setStudentName(rs.getString(2));
				systemBean.setBranch(rs.getString(3));
				systemBean.setSemester(rs.getInt(4));
				systemBean.setCgpa(rs.getDouble(5));
				list.add(systemBean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search CollegeSystem");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List<CollegeSystemBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}
}