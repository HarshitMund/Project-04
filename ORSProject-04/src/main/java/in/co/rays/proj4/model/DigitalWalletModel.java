package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.DigitalWalletBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class DigitalWalletModel {

	public long nextPk() throws DatabaseException {
		long pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from digitalwallet");
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

	public long add(DigitalWalletBean bean) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		Connection conn = null;

		DigitalWalletBean existBean = findByMobileNumber(bean.getMobileNumber());
		if (existBean != null)
			throw new DuplicateRecordException("Mobile Number Already Associated With A Wallet");

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into digitalwallet values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getUserName());
			pstmt.setDouble(3, bean.getBalance());
			pstmt.setString(4, bean.getMobileNumber());
			pstmt.setString(5, bean.getWalletStatus());
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
			throw new ApplicationException("Exception : Exception in adding digital wallet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(DigitalWalletBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		DigitalWalletBean existBean = findByMobileNumber(bean.getMobileNumber());
		if (existBean != null && existBean.getId() != bean.getId())
			throw new DuplicateRecordException("Mobile Number Already Associated With Another Wallet");

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update digitalwallet set user_name = ?, balance = ?, mobile_no = ?, status = ? where id = ?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getUserName());
			pstmt.setDouble(2, bean.getBalance());
			pstmt.setString(3, bean.getMobileNumber());
			pstmt.setString(4, bean.getWalletStatus());
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
			throw new ApplicationException("Exception : Exception in updating digital wallet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(DigitalWalletBean bean) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from digitalwallet where id = ?");
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
			throw new ApplicationException("Exception : Exception in deleting digital wallet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public DigitalWalletBean findById(long id) throws ApplicationException {
		DigitalWalletBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from digitalwallet where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new DigitalWalletBean();
				bean.setId(rs.getLong(1));
				bean.setUserName(rs.getString(2));
				bean.setBalance(rs.getDouble(3));
				bean.setMobileNumber(rs.getString(4));
				bean.setWalletStatus(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting wallet by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public DigitalWalletBean findByMobileNumber(String mobileNumber) throws ApplicationException {
		DigitalWalletBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from digitalwallet where mobile_no = ?");
			pstmt.setString(1, mobileNumber);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new DigitalWalletBean();
				bean.setId(rs.getLong(1));
				bean.setUserName(rs.getString(2));
				bean.setBalance(rs.getDouble(3));
				bean.setMobileNumber(rs.getString(4));
				bean.setWalletStatus(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting wallet by mobile number");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<DigitalWalletBean> search(DigitalWalletBean bean, int pageNo, int pageSize)
			throws ApplicationException {
		Connection conn = null;
		List<DigitalWalletBean> list = new ArrayList<DigitalWalletBean>();
		StringBuffer sb = new StringBuffer("select * from digitalwallet where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());
			if (bean.getUserName() != null && bean.getUserName().length() > 0)
				sb.append(" and user_name like '" + bean.getUserName() + "%'");
			if (bean.getMobileNumber() != null && bean.getMobileNumber().length() > 0)
				sb.append(" and mobile_no like '" + bean.getMobileNumber() + "%'");
			if (bean.getWalletStatus() != null && bean.getWalletStatus().length() > 0)
				sb.append(" and status like '" + bean.getWalletStatus() + "%'");
			if (bean.getBalance() > 0)
				sb.append(" and balance = " + bean.getBalance());
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
				DigitalWalletBean walletBean = new DigitalWalletBean();
				walletBean.setId(rs.getLong(1));
				walletBean.setUserName(rs.getString(2));
				walletBean.setBalance(rs.getDouble(3));
				walletBean.setMobileNumber(rs.getString(4));
				walletBean.setWalletStatus(rs.getString(5));
				list.add(walletBean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search Digital Wallet ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List<DigitalWalletBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}
}