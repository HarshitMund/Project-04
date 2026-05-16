package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * CollegeModel performs database operations related to College.
 * <p>
 * This model provides methods for:
 * <ul>
 * <li>Generating primary keys</li>
 * <li>Adding college records</li>
 * <li>Updating college records</li>
 * <li>Deleting college records</li>
 * <li>Searching college records</li>
 * <li>Finding college by primary key or name</li>
 * <li>Listing all colleges</li>
 * </ul>
 * 
 * This class uses JDBC API for database interaction.
 * 
 * @author Harshit
 */
public class CollegeModel {

	/**
	 * Generates next primary key for college table.
	 * 
	 * @return next primary key
	 * @throws DatabaseException if database error occurs
	 */
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

	/**
	 * Adds a new college record to database.
	 * 
	 * @param bean college bean containing college information
	 * @return generated primary key
	 * @throws ApplicationException     if application error occurs
	 * @throws DuplicateRecordException if college name already exists
	 */
	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		long pk = 0;

		CollegeBean duplicateBean = findByName(bean.getName());
		if (duplicateBean != null && duplicateBean.getId() != bean.getId())
			throw new DuplicateRecordException("College name already exist");

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

	/**
	 * Updates existing college record.
	 * 
	 * @param bean college bean containing updated information
	 * @throws ApplicationException     if application error occurs
	 * @throws DuplicateRecordException if duplicate college name exists
	 */
	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		CollegeBean duplicateBean = findByName(bean.getName());
		if (duplicateBean != null && duplicateBean.getId() != bean.getId())
			throw new DuplicateRecordException("College name aleady exist");

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

	/**
	 * Deletes college record from database.
	 * 
	 * @param bean college bean containing ID of record to delete
	 * @throws ApplicationException if application error occurs
	 */
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

	/**
	 * Finds college by primary key.
	 * 
	 * @param pk primary key
	 * @return CollegeBean containing college information
	 * @throws ApplicationException if application error occurs
	 */
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

	/**
	 * Finds college by name.
	 * 
	 * @param name college name
	 * @return CollegeBean containing college information
	 * @throws ApplicationException if application error occurs
	 */
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

	/**
	 * Searches college records using search criteria.
	 * 
	 * @param bean     search bean containing search criteria
	 * @param pageNo   page number
	 * @param pageSize number of records per page
	 * @return list of matching college records
	 * @throws ApplicationException if application error occurs
	 */
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

	/**
	 * Returns list of all college records.
	 * 
	 * @return list of all colleges
	 * @throws ApplicationException if application error occurs
	 */
	public List<CollegeBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

}