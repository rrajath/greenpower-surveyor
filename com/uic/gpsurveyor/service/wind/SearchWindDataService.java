/**
 * 
 */
package com.uic.gpsurveyor.service.wind;

import java.util.ArrayList;
import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.WindPowerDAO;
import com.uic.gpsurveyor.VO.WindPowerVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class SearchWindDataService {

	public static ArrayList execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();
		ArrayList alWindPower;

		try {

			WindPowerVO objWindPowerVO = new WindPowerVO();

			if(ht.get("WindPowerVO") != null)
			{
				objWindPowerVO = (WindPowerVO)ht.get("WindPowerVO");
			}

			// Performing validations on all fields
			validateFields(objWindPowerVO);

			WindPowerDAO objWindPowerDAO = new WindPowerDAO();
			alWindPower = objWindPowerDAO.search(objWindPowerVO, conn);
		} catch (Exception e) {
			throw e;
		}

		// return this array list to the calling method and then display the list
		return alWindPower;
	}

	public static void validateFields(WindPowerVO objWindPowerVO) throws Exception
	{
		// Performing string validations
		GPValidator.isAlpha("City", objWindPowerVO.getCity());
		GPValidator.isAlpha("County", objWindPowerVO.getCounty());
		GPValidator.isAlpha("State", objWindPowerVO.getState());
	}
}
