package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.GymBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class GymModel {

	public long nextPk() throws DatabaseException {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from gymManagement");
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

	public long add(GymBean bean) throws ApplicationException, DuplicateRecordException {

		long pk = 0;
		Connection conn = null;

		GymBean existBean = findByMemberName(bean.getMemberName());
		if (existBean != null)
			throw new DuplicateRecordException("Member Name Already Exist");

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into gymManagement values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getMemberName());
			pstmt.setString(3, bean.getTrainerName());
			pstmt.setDouble(4, bean.getFee());
			pstmt.setDate(5, new Date(bean.getJoiningDate().getTime()));
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
			throw new ApplicationException("Exception : Exception in adding gym management" + e.getMessage());
		}

		return pk;
	}

	public void update(GymBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		GymBean existBean = findByMemberName(bean.getMemberName());
		if (existBean != null)
			throw new DuplicateRecordException("Member Name Already Exist");

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update gymManagement set member_name = ?, trainer_name = ?, fee = ?, joining_date = ? where id = ?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getMemberName());
			pstmt.setString(2, bean.getTrainerName());
			pstmt.setDouble(3, bean.getFee());
			pstmt.setDate(4, new Date(bean.getJoiningDate().getTime()));
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
			throw new ApplicationException("Exception : Exception in updating gym management");
		}

	}

	public void delete(GymBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from gymManagement where id = ?");
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
			throw new ApplicationException("Exception : Exception in deleting gym management");
		}

	}

	public GymBean findById(long id) throws ApplicationException {

		GymBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from gymManagement where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new GymBean();

				bean.setId(rs.getLong(1));
				bean.setMemberName(rs.getString(2));
				bean.setTrainerName(rs.getString(3));
				bean.setFee(rs.getDouble(4));
				bean.setJoiningDate(rs.getDate(5));
			}
			rs.close();
			pstmt.cancel();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting gym member by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public GymBean findByMemberName(String name) throws ApplicationException {

		GymBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from gymManagement where trainer_name = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new GymBean();

				bean.setId(rs.getLong(1));
				bean.setMemberName(rs.getString(2));
				bean.setTrainerName(rs.getString(3));
				bean.setFee(rs.getDouble(4));
				bean.setJoiningDate(rs.getDate(5));
			}
			rs.close();
			pstmt.cancel();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting gym member by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List<GymBean> search(GymBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<GymBean> list = new ArrayList<GymBean>();

		StringBuffer sb = new StringBuffer("select * from gymManagement where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());

			if (bean.getMemberName() != null && bean.getMemberName().length() > 0)
				sb.append(" and member_name = " + bean.getMemberName());

			if (bean.getTrainerName() != null && bean.getTrainerName().length() > 0)
				sb.append(" and trainer_name = " + bean.getTrainerName());

			if (bean.getFee() > 0)
				sb.append(" and fee = " + bean.getFee());

			if (bean.getJoiningDate() != null && bean.getJoiningDate().getTime() > 0)
				sb.append(" and joining_date = " + bean.getJoiningDate());
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
				bean = new GymBean();

				bean.setId(rs.getLong(1));
				bean.setMemberName(rs.getString(2));
				bean.setTrainerName(rs.getString(3));
				bean.setFee(rs.getDouble(4));
				bean.setJoiningDate(rs.getDate(5));

				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception : Exception in search Gym Membership ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

}
