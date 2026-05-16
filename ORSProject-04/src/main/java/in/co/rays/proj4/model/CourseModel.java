package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * CourseModel performs database operations related to Course.
 * <p>
 * This model provides methods for:
 * <ul>
 * <li>Generating primary keys</li>
 * <li>Adding course records</li>
 * <li>Updating course records</li>
 * <li>Deleting course records</li>
 * <li>Searching course records</li>
 * <li>Finding course by primary key or name</li>
 * <li>Listing all courses</li>
 * </ul>
 * 
 * This class uses JDBC API for database interaction.
 * 
 * @author Harshit
 */
public class CourseModel {

	/**
	 * Generates next primary key for course table.
	 * 
	 * @return next primary key
	 * @throws DatabaseException if database error occurs
	 */
	public Long nextPk() throws DatabaseException {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_course");
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

	/**
	 * Adds a new course record to database.
	 * 
	 * @param bean course bean containing course information
	 * @return generated primary key
	 * @throws ApplicationException     if application error occurs
	 * @throws DuplicateRecordException if duplicate course exists
	 */
	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		long pk = 0;

		CourseBean duplicateBean = findByName(bean.getName());

		if (duplicateBean != null && duplicateBean.getId() != bean.getId())
			throw new DuplicateRecordException("Course name already exist");

		try {

			pk = nextPk();

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_course values (?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());

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

			throw new ApplicationException("Exception: Exception in add course");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	/**
	 * Updates existing course record.
	 * 
	 * @param bean course bean containing updated information
	 * @throws ApplicationException     if application error occurs
	 * @throws DuplicateRecordException if duplicate course exists
	 */
	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		CourseBean duplicateBean = findByName(bean.getName());

		if (duplicateBean != null && duplicateBean.getId() != bean.getId())
			throw new DuplicateRecordException("Course name already exist");

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_course set name = ?, duration = ?, description = ?, created_by = ?, modified_by = ?, created_datetime = ?, "
							+ "modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDuration());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

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

			throw new ApplicationException("Exception: Exception in update course");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Deletes course record from database.
	 * 
	 * @param bean course bean containing ID of record to delete
	 * @throws ApplicationException if application error occurs
	 */
	public void delete(CourseBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_course where id = ?");
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

			throw new ApplicationException("Exception: Exception in delete course");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Finds course by primary key.
	 * 
	 * @param pk primary key
	 * @return CourseBean containing course information
	 * @throws ApplicationException if application error occurs
	 */
	public CourseBean findByPk(long pk) throws ApplicationException {

		CourseBean bean = null;
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_course where id = ?");
			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new CourseBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in getting course by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	/**
	 * Finds course by name.
	 * 
	 * @param name course name
	 * @return CourseBean containing course information
	 * @throws ApplicationException if application error occurs
	 */
	public CourseBean findByName(String name) throws ApplicationException {

		CourseBean bean = null;
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_course where name = ?");
			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new CourseBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in getting course by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	/**
	 * Searches course records using search criteria.
	 * 
	 * @param bean     search bean containing search criteria
	 * @param pageNo   page number
	 * @param pageSize number of records per page
	 * @return list of matching course records
	 * @throws ApplicationException if application error occurs
	 */
	public List<CourseBean> search(CourseBean bean, int pageNo, int pageSize) throws ApplicationException {

		List<CourseBean> list = new ArrayList<CourseBean>();
		Connection conn = null;

		StringBuffer sb = new StringBuffer("select * from st_course where 1 = 1");

		if (bean != null) {

			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());

			if (bean.getName() != null && bean.getName().length() > 0)
				sb.append(" and name like '" + bean.getName() + "%'");

			if (bean.getDuration() != null && bean.getDuration().length() > 0)
				sb.append(" and duration like '" + bean.getDuration() + "%'");

			if (bean.getDescription() != null && bean.getDescription().length() > 0)
				sb.append(" and description like '" + bean.getDescription() + "%'");
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

				bean = new CourseBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

				list.add(bean);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in search course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

	/**
	 * Returns list of all course records.
	 * 
	 * @return list of all courses
	 * @throws ApplicationException if application error occurs
	 */
	public List<CourseBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}
}