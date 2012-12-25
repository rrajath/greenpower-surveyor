/**
 * 
 */
package com.uic.gpsurveyor.service.inventory;

import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.UserInfoDAO;
import com.uic.gpsurveyor.VO.UserInfoVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class UpdateUserService {

	public static void execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();

		try {
			UserInfoVO objUserInfoVO = new UserInfoVO();

			if(ht.get("UserInfoVO")!=null)
			{
				objUserInfoVO = (UserInfoVO)ht.get("UserInfoVO");
			}

			// Perform validations
			//			validateFields(objUserInfoVO);

			// Inserting a record into login_table once a user is created.
			UserInfoDAO objUserInfoDAO = new UserInfoDAO();
			objUserInfoDAO.update(objUserInfoVO, conn);

			Scratchpad.ht.put("notification", "User Updated");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
