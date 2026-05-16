package in.co.rays.proj4.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Single-point database interaction controller implementing a pool-backed Singleton mechanism (via C3P0).
 * * @author Harshit
 */
public final class JDBCDataSource {

	/** Single memory instance initialization reference */
	private static JDBCDataSource jds = null;

	/** C3P0 connection-pooling object controller mapping configuration properties */
	private static ComboPooledDataSource cpds = null;

	/** System properties bundle tracking server properties details lookup */
	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.proj4.bundle.system");

	/**
	 * Private instance constructor configuring the C3P0 connection tracking engine layout.
	 */
	private JDBCDataSource() {
		try {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass(PropertyReader.getValue("driver"));
			cpds.setJdbcUrl(rb.getString("url"));
			cpds.setUser(rb.getString("username"));
			cpds.setPassword(rb.getString("password"));
			cpds.setInitialPoolSize(Integer.parseInt(rb.getString("initialpoolsize")));
			cpds.setAcquireIncrement(Integer.parseInt(rb.getString("acquireincrement")));
			cpds.setMaxPoolSize(Integer.parseInt(rb.getString("maxpoolsize")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fetches the active static execution pointer tracking class initialization parameters.
	 * * @return JDBCDataSource active control configuration point instance
	 */
	public static JDBCDataSource getInstance() {
		if (jds == null) {
			jds = new JDBCDataSource();
		}
		return jds;
	}

	/**
	 * Fetches an open and operational relational {@link Connection} from the pooling array pool.
	 * * @return Database Connection instance string, or null if initialization fails
	 */
	public static Connection getConnection() {
		try {
			return getInstance().cpds.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Closes opened structures including Connections, Statements and ResultSets safely avoiding null references.
	 * * @param conn database operational connection instance
	 * @param stmt transactional active execution instructions query pointer
	 * @param rs dataset table cursor tracking variable pointer reference
	 */
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes Connection and Statement tracking wrappers.
	 * * @param conn database operational connection instance
	 * @param stmt transactional active execution instructions query pointer
	 */
	public static void closeConnection(Connection conn, Statement stmt) {
		closeConnection(conn, stmt, null);
	}

	/**
	 * Safely drops active standalone database connections back into the connection registry array.
	 * * @param conn database connection model instance
	 */
	public static void closeConnection(Connection conn) {
		closeConnection(conn, null);
	}
}