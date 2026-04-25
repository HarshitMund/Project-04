package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CollegeModel {

	public Long nextPk() throws DatabaseException {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_college");
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

	public long add(CollegeBean bean) throws ApplicationException {

		Connection conn = null;
		long pk = 0;

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_college values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add College");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(CollegeBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_college set name = ?, address = ?, state = ?, city = ?, phone_no = ?, created_by = ?, modified_by = ?, created_datetime = ?, "
							+ "modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add College");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(CollegeBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_college where id = ?");
			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();

			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add College");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public CollegeBean findByPk(long pk) throws ApplicationException {

		CollegeBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_college where id = ?");
			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setCity(rs.getString(4));
				bean.setState(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception: Exceptioin in getting college by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public CollegeBean findByName(String name) throws ApplicationException {

		CollegeBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_college where name = ?");
			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setCity(rs.getString(4));
				bean.setState(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception: Exceptioin in getting college by name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List<CollegeBean> search(CollegeBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sb = new StringBuffer("select * from st_college where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());

			if (bean.getName() != null && bean.getName().length() > 0)
				sb.append(" and name like '" + bean.getName() + "%'");

			if (bean.getAddress() != null && bean.getAddress().length() > 0)
				sb.append(" and address like '" + bean.getAddress() + "%'");

			if (bean.getCity() != null && bean.getCity().length() > 0)
				sb.append(" and city like '" + bean.getCity() + "%'");

			if (bean.getState() != null && bean.getState().length() > 0)
				sb.append(" and state like '" + bean.getState() + "%'");

			if (bean.getPhoneNo() != null && bean.getPhoneNo().length() > 0)
				sb.append(" and phone_no like '" + bean.getPhoneNo() + "%'");
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo + ", " + pageSize);
		}

		List<CollegeBean> list = new ArrayList<CollegeBean>();
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setCity(rs.getString(4));
				bean.setState(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception: Exceptioin in search college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
	
	public List<CollegeBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

}
