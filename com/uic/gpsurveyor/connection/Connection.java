package com.uic.gpsurveyor.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection extends ConnectionManager
{
	String username = null;
	String password = null;
	String dbUrl = null;

	/**
	 * This method creates a new database connection
	 * @param username
	 * @param password
	 * @param dbUrl
	 * @return conn
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public java.sql.Connection getConnection() throws ClassNotFoundException, SQLException
	{
		return DriverManager.getConnection("jdbc:mysql://localhost/gpsurveyor", "root", "matrix");
	}

	/**
	 * This method is used to close a database connection
	 * @param conn
	 * @throws SQLException
	 */
	@Override
	public void closeConnection(java.sql.Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		conn.close();
	}

}