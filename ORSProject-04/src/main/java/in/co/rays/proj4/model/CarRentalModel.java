package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CarRentalBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CarRentalModel {

	public long nextPk() throws DatabaseException {
		long pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from carrental");
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

	public long add(CarRentalBean bean) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		Connection conn = null;

		CarRentalBean existBean = findByName(bean.getName());
		if (existBean != null)
			throw new DuplicateRecordException("Customer Name Already Exists");

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into carrental values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getCarModel());
			pstmt.setDouble(4, bean.getRentpayDay());
			pstmt.setString(5, bean.getFuelType());
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
			throw new ApplicationException("Exception : Exception in adding car rental record");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(CarRentalBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		CarRentalBean existBean = findByName(bean.getName());
		if (existBean != null && existBean.getId() != bean.getId())
			throw new DuplicateRecordException("Customer Name Already Exists");

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update carrental set name = ?, car_model = ?, rent_per_day = ?, fuel_type = ? where id = ?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getCarModel());
			pstmt.setDouble(3, bean.getRentpayDay());
			pstmt.setString(4, bean.getFuelType());
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
			throw new ApplicationException("Exception : Exception in updating car rental record");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(CarRentalBean bean) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from carrental where id = ?");
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
			throw new ApplicationException("Exception : Exception in deleting car rental record");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public CarRentalBean findById(long id) throws ApplicationException {
		CarRentalBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from carrental where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CarRentalBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCarModel(rs.getString(3));
				bean.setRentpayDay(rs.getDouble(4));
				bean.setFuelType(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting car rental by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public CarRentalBean findByName(String name) throws ApplicationException {
		CarRentalBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from carrental where name = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CarRentalBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCarModel(rs.getString(3));
				bean.setRentpayDay(rs.getDouble(4));
				bean.setFuelType(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting car rental by name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<CarRentalBean> search(CarRentalBean bean, int pageNo, int pageSize) throws ApplicationException {
		Connection conn = null;
		List<CarRentalBean> list = new ArrayList<CarRentalBean>();
		StringBuffer sb = new StringBuffer("select * from carrental where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());
			if (bean.getName() != null && bean.getName().length() > 0)
				sb.append(" and name like '" + bean.getName() + "%'");
			if (bean.getCarModel() != null && bean.getCarModel().length() > 0)
				sb.append(" and car_model like '" + bean.getCarModel() + "%'");
			if (bean.getFuelType() != null && bean.getFuelType().length() > 0)
				sb.append(" and fuel_type like '" + bean.getFuelType() + "%'");
			if (bean.getRentpayDay() > 0)
				sb.append(" and rent_per_day = " + bean.getRentpayDay());
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
				CarRentalBean rentalBean = new CarRentalBean();
				rentalBean.setId(rs.getLong(1));
				rentalBean.setName(rs.getString(2));
				rentalBean.setCarModel(rs.getString(3));
				rentalBean.setRentpayDay(rs.getDouble(4));
				rentalBean.setFuelType(rs.getString(5));
				list.add(rentalBean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search Car Rental");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List<CarRentalBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}
}