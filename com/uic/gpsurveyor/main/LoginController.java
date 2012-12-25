package com.uic.gpsurveyor.main;

import java.sql.SQLException;
import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.LoginDAO;
import com.uic.gpsurveyor.VO.LoginVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

public class LoginController
{
	// Create a new database connection
	static Connection conn = new Connection();

	public static String execute(Hashtable ht) throws Exception
	{
		// Getting a database connection
		java.sql.Connection conn1 = conn.getConnection();

		/*String username = ht.get("login").toString();
		String pwd = ht.get("password").toString();*/

		LoginVO objLoginVO = new LoginVO();
		if(ht.get("LoginVO") != null)
		{
			objLoginVO = (LoginVO)ht.get("LoginVO");
		}

		// Performing mandatory field validations
		GPValidator.mandatoryValidator("Username", objLoginVO.getUsername());
		GPValidator.mandatoryValidator("Password", objLoginVO.getPassword());

		// Performing string validations
		GPValidator.validateString("Username", objLoginVO.getUsername());

		// Authenticating user
		LoginVO loginCrit = new LoginVO();
		loginCrit.setUsername(objLoginVO.getUsername());
		loginCrit.setPassword(objLoginVO.getPassword());

		LoginDAO objLoginDAO = new LoginDAO();
		LoginVO loginInfo = new LoginVO();
		try
		{
			loginInfo = objLoginDAO.search(loginCrit, conn1);
		}
		catch(SQLException e)
		{
			throw new Exception(GPConstants.INCORRECT_USERNAME_PASSWORD);
		}

		Scratchpad.ht.put("last_login", loginInfo.getLast_login());

		try
		{
			objLoginDAO.update(loginInfo, conn1);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception(GPConstants.UPDATE_ERROR);
		}

		return loginInfo.getUsertype();
	}
}