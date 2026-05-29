package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.EventBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class EventModel {

	public long nextPk() throws DatabaseException {
		long pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from eventmanagement");
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

	public long add(EventBean bean) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		Connection conn = null;

		EventBean existBean = findByEventName(bean.getEventName());
		if (existBean != null)
			throw new DuplicateRecordException("Event Name Already Exists");

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into eventmanagement values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getEventName());
			pstmt.setString(3, bean.getOrganizerName());
			pstmt.setString(4, bean.getVenue());
			pstmt.setDouble(5, bean.getBudget());
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
			throw new ApplicationException("Exception : Exception in adding event");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(EventBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		EventBean existBean = findByEventName(bean.getEventName());
		if (existBean != null && existBean.getId() != bean.getId())
			throw new DuplicateRecordException("Event Name Already Exists");

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update eventmanagement set event_name = ?, organizer_name = ?, venue = ?, budget = ? where id = ?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getEventName());
			pstmt.setString(2, bean.getOrganizerName());
			pstmt.setString(3, bean.getVenue());
			pstmt.setDouble(4, bean.getBudget());
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
			throw new ApplicationException("Exception : Exception in updating event");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(EventBean bean) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from eventmanagement where id = ?");
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
			throw new ApplicationException("Exception : Exception in deleting event");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public EventBean findById(long id) throws ApplicationException {
		EventBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from eventmanagement where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new EventBean();
				bean.setId(rs.getLong(1));
				bean.setEventName(rs.getString(2));
				bean.setOrganizerName(rs.getString(3));
				bean.setVenue(rs.getString(4));
				bean.setBudget(rs.getDouble(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting event by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public EventBean findByEventName(String name) throws ApplicationException {
		EventBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from eventmanagement where event_name = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new EventBean();
				bean.setId(rs.getLong(1));
				bean.setEventName(rs.getString(2));
				bean.setOrganizerName(rs.getString(3));
				bean.setVenue(rs.getString(4));
				bean.setBudget(rs.getDouble(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting event by name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<EventBean> search(EventBean bean, int pageNo, int pageSize) throws ApplicationException {
		Connection conn = null;
		List<EventBean> list = new ArrayList<EventBean>();
		StringBuffer sb = new StringBuffer("select * from eventmanagement where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());
			if (bean.getEventName() != null && bean.getEventName().length() > 0)
				sb.append(" and event_name like '" + bean.getEventName() + "%'");
			if (bean.getOrganizerName() != null && bean.getOrganizerName().length() > 0)
				sb.append(" and organizer_name like '" + bean.getOrganizerName() + "%'");
			if (bean.getVenue() != null && bean.getVenue().length() > 0)
				sb.append(" and venue like '" + bean.getVenue() + "%'");
			if (bean.getBudget() > 0)
				sb.append(" and budget = " + bean.getBudget());
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
				EventBean eventBean = new EventBean();
				eventBean.setId(rs.getLong(1));
				eventBean.setEventName(rs.getString(2));
				eventBean.setOrganizerName(rs.getString(3));
				eventBean.setVenue(rs.getString(4));
				eventBean.setBudget(rs.getDouble(5));
				list.add(eventBean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search Event ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List<EventBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}
}