package com.uic.gpsurveyor.connection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionManager
{
	/**
	 * This method creates a new database connection
	 * @param username
	 * @param password
	 * @param dbUrl
	 * @return conn
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

	/**
	 * This method is used to close a database connection
	 * @param conn
	 * @throws SQLException
	 */
	public abstract void closeConnection(Connection conn) throws SQLException;
}