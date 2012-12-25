/**
 * 
 */
package com.uic.gpsurveyor.service.tidal;

import java.util.ArrayList;
import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.TidalPowerDAO;
import com.uic.gpsurveyor.VO.TidalPowerVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class SearchTidalDataService {

	public static ArrayList execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();
		ArrayList alTidalPower;

		try {

			TidalPowerVO objTidalPowerVO = new TidalPowerVO();

			if(ht.get("TidalPowerVO") != null)
			{
				objTidalPowerVO = (TidalPowerVO)ht.get("TidalPowerVO");
			}

			// Performing validations on all fields
			validateFields(objTidalPowerVO);

			TidalPowerDAO objTidalPowerDAO = new TidalPowerDAO();
			alTidalPower = objTidalPowerDAO.search(objTidalPowerVO, conn);
		} catch (Exception e) {
			throw e;
		}

		// return this array list to the calling method and then display the list
		return alTidalPower;
	}

	public static void validateFields(TidalPowerVO objTidalPowerVO) throws Exception
	{
		// Performing string validations
		GPValidator.isAlpha("City", objTidalPowerVO.getCity());
		GPValidator.isAlpha("County", objTidalPowerVO.getCounty());
		GPValidator.isAlpha("State", objTidalPowerVO.getState());
	}
}
