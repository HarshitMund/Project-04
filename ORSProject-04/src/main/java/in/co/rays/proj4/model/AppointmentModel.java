package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.AppointmentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class AppointmentModel {

	public long nextPk() throws DatabaseException {
		long pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from appointment");
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

	public long add(AppointmentBean bean) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		Connection conn = null;

		AppointmentBean existBean = findByName(bean.getName());
		if (existBean != null)
			throw new DuplicateRecordException("Appointment Name Already Exists");

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into appointment values (?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			if (bean.getDate() != null) {
				pstmt.setDate(3, new java.sql.Date(bean.getDate().getTime()));
			} else {
				pstmt.setDate(3, null);
			}
			pstmt.setString(4, bean.getStatus());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (Rows inserted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : Add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in adding appointment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(AppointmentBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		AppointmentBean existBean = findByName(bean.getName());
		if (existBean != null && existBean.getId() != bean.getId())
			throw new DuplicateRecordException("Appointment Name Already Exists");

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update appointment set name = ?, date = ?, status = ? where id = ?");
			pstmt.setLong(4, bean.getId());
			pstmt.setString(1, bean.getName());
			if (bean.getDate() != null) {
				pstmt.setDate(2, new java.sql.Date(bean.getDate().getTime()));
			} else {
				pstmt.setDate(2, null);
			}
			pstmt.setString(3, bean.getStatus());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (Rows update)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in updating appointment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(AppointmentBean bean) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from appointment where id = ?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (Rows delete)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in deleting appointment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public AppointmentBean findById(long id) throws ApplicationException {
		AppointmentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from appointment where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AppointmentBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDate(rs.getDate(3));
				bean.setStatus(rs.getString(4));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting appointment by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public AppointmentBean findByName(String name) throws ApplicationException {
		AppointmentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from appointment where name = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AppointmentBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDate(rs.getDate(3));
				bean.setStatus(rs.getString(4));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting appointment by name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<AppointmentBean> search(AppointmentBean bean, int pageNo, int pageSize) throws ApplicationException {
		Connection conn = null;
		List<AppointmentBean> list = new ArrayList<AppointmentBean>();
		StringBuffer sb = new StringBuffer("select * from appointment where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());
			if (bean.getName() != null && bean.getName().length() > 0)
				sb.append(" and name like '" + bean.getName() + "%'");
			if (bean.getStatus() != null && bean.getStatus().length() > 0)
				sb.append(" and status like '" + bean.getStatus() + "%'");
			if (bean.getDate() != null) {
				sb.append(" and appointment_date = '" + new java.sql.Date(bean.getDate().getTime()) + "'");
			}
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
				AppointmentBean appBean = new AppointmentBean();
				appBean.setId(rs.getLong(1));
				appBean.setName(rs.getString(2));
				appBean.setDate(rs.getDate(3));
				appBean.setStatus(rs.getString(4));
				list.add(appBean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search Appointment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List<AppointmentBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}
}