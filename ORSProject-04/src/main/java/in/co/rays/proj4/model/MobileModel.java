package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.MobileBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MobileModel {

	public long nextPk() throws DatabaseException {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from mobilestore");
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

	public long add(MobileBean bean) throws ApplicationException, DuplicateRecordException {

		long pk = 0;
		Connection conn = null;

		MobileBean existBean = findByMobileName(bean.getMobileName());
		if (existBean != null)
			throw new DuplicateRecordException("Mobile Name Already Exist");

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into mobilestore values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getBeandName());
			pstmt.setString(3, bean.getMobileName());
			pstmt.setInt(4, bean.getRaw());
			pstmt.setDouble(5, bean.getPrice());
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
			throw new ApplicationException("Exception : Exception in adding mobile management");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void update(MobileBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		MobileBean existBean = findByMobileName(bean.getMobileName());
		if (existBean != null && existBean.getId() != bean.getId())
			throw new DuplicateRecordException("Mobile Name Already Exist");

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update mobilestore set brand_name = ?, mobile_name = ?, raw = ?, price = ? where id = ?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getBeandName());
			pstmt.setString(2, bean.getMobileName());
			pstmt.setInt(3, bean.getRaw());
			pstmt.setDouble(4, bean.getPrice());
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
			throw new ApplicationException("Exception : Exception in updating mobile management");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(MobileBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from mobilestore where id = ?");
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
			throw new ApplicationException("Exception : Exception in deleting mobile management");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public MobileBean findById(long id) throws ApplicationException {

		MobileBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from mobilestore where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MobileBean();

				bean.setId(rs.getLong(1));
				bean.setBeandName(rs.getString(2));
				bean.setMobileName(rs.getString(3));
				bean.setRaw(rs.getInt(4));
				bean.setPrice(rs.getDouble(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting mobile by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public MobileBean findByMobileName(String name) throws ApplicationException {

		MobileBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from mobilestore where mobile_name = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MobileBean();

				bean.setId(rs.getLong(1));
				bean.setBeandName(rs.getString(2));
				bean.setMobileName(rs.getString(3));
				bean.setRaw(rs.getInt(4));
				bean.setPrice(rs.getDouble(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting mobile by name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List<MobileBean> search(MobileBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<MobileBean> list = new ArrayList<MobileBean>();

		StringBuffer sb = new StringBuffer("select * from mobilestore where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());

			if (bean.getBeandName() != null && bean.getBeandName().length() > 0)
				sb.append(" and brand_name like '" + bean.getBeandName() + "%'");

			if (bean.getMobileName() != null && bean.getMobileName().length() > 0)
				sb.append(" and mobile_name like '" + bean.getMobileName() + "%'");

			if (bean.getRaw() > 0)
				sb.append(" and raw = " + bean.getRaw());

			if (bean.getPrice() > 0)
				sb.append(" and price = " + bean.getPrice());
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
				MobileBean mobileBean = new MobileBean();

				mobileBean.setId(rs.getLong(1));
				mobileBean.setBeandName(rs.getString(2));
				mobileBean.setMobileName(rs.getString(3));
				mobileBean.setRaw(rs.getInt(4));
				mobileBean.setPrice(rs.getDouble(5));

				list.add(mobileBean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception : Exception in search Mobile Store ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}
	
	public List<MobileBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

}