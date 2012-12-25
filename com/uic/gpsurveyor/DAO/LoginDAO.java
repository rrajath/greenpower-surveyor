package com.uic.gpsurveyor.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.uic.gpsurveyor.VO.LoginVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.util.GPConstants;

public class LoginDAO
{
	/**
	 * Constructor
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public LoginDAO() throws ClassNotFoundException, SQLException
	{
		Connection conn = new Connection();
		java.sql.Connection conn1 = conn.getConnection();
	}

	/**
	 * This method is used to create a record in login_table
	 * @param loginObj
	 * @param conn
	 * @throws Exception
	 */
	public void create(LoginVO loginObj, java.sql.Connection conn) throws Exception
	{
		try {
			String queryString = "INSERT INTO login_table VALUES (?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(queryString);

			ps.setString(1, loginObj.getUsername());
			ps.setString(2, loginObj.getPassword());
			ps.setString(3, loginObj.getUsertype());
			ps.setTimestamp(4, new Timestamp(new java.util.Date().getTime()));

			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Error insert record into database");
		}
	}

	/**
	 * This method is used to search for record(s) in login_table
	 * @param loginObj
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public LoginVO search(LoginVO loginObj, java.sql.Connection conn) throws SQLException
	{
		LoginVO loginInfo = new LoginVO();

		String queryString = "SELECT username, password, user_type, last_login FROM login_table WHERE " +
				"username = ? AND password = ?";

		PreparedStatement ps = conn.prepareStatement(queryString);

		ps.setString(1, loginObj.getUsername());
		ps.setString(2, loginObj.getPassword());

		ResultSet rs = ps.executeQuery();

		if(!rs.next())
		{
			throw new SQLException("Incorrect username/password");
		}
		else
		{
			loginInfo.setUsername(rs.getString(1));
			loginInfo.setPassword(rs.getString(2));
			loginInfo.setUsertype(rs.getString(3));
			loginInfo.setLast_login(rs.getTimestamp(4));
		}
		return loginInfo;
	}

	/**
	 * This method is used to update a record in login_table
	 * @param loginObj
	 * @param conn
	 * @return number of rows updated
	 * @throws SQLException
	 */
	public int update(LoginVO loginObj, java.sql.Connection conn) throws SQLException
	{
		// Prepare the query string
		String queryString = "UPDATE login_table SET " +
				" password = ?, " +
				" user_type = ?, " +
				" last_login = ? " +
				" WHERE username = ?";

		int i = 1;

		int rowsAffected;
		try {
			PreparedStatement ps = conn.prepareStatement(queryString);

			ps.setString(i++, loginObj.getPassword());
			ps.setString(i++, loginObj.getUsertype());
			ps.setTimestamp(i++, new Timestamp(new java.util.Date().getTime()));
			ps.setString(i++, loginObj.getUsername());

			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(GPConstants.UPDATE_ERROR);
		}
		return rowsAffected;
	}

}