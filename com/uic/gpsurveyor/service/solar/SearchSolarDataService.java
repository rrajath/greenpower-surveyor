/**
 * 
 */
package com.uic.gpsurveyor.service.solar;

import java.util.ArrayList;
import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.SolarPowerDAO;
import com.uic.gpsurveyor.VO.SolarPowerVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class SearchSolarDataService {

	public static ArrayList execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();
		ArrayList alSolarPower;

		try {

			SolarPowerVO objSolarPowerVO = new SolarPowerVO();

			if(ht.get("SolarPowerVO") != null)
			{
				objSolarPowerVO = (SolarPowerVO)ht.get("SolarPowerVO");
			}

			// Performing validations on all fields
			validateFields(objSolarPowerVO);

			SolarPowerDAO objSolarPowerDAO = new SolarPowerDAO();
			alSolarPower = objSolarPowerDAO.search(objSolarPowerVO, conn);
		} catch (Exception e) {
			throw e;
		}

		// return this array list to the calling method and then display the list
		return alSolarPower;
	}

	public static void validateFields(SolarPowerVO objSolarPowerVO) throws Exception
	{
		// Performing string validations
		GPValidator.isAlpha("City", objSolarPowerVO.getCity());
		GPValidator.isAlpha("County", objSolarPowerVO.getCounty());
		GPValidator.isAlpha("State", objSolarPowerVO.getState());
	}
}
