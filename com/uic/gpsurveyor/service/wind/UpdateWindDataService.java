/**
 * 
 */
package com.uic.gpsurveyor.service.wind;

import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.WindPowerDAO;
import com.uic.gpsurveyor.VO.WindPowerVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class UpdateWindDataService {

	public static void execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();

		try {

			WindPowerVO objWindPowerVO = new WindPowerVO();

			if(ht.get("WindPowerVO")!=null)
			{
				objWindPowerVO = (WindPowerVO)ht.get("WindPowerVO");
			}

			// Performing all validations
			validateFields(objWindPowerVO);

			WindPowerDAO objWindPowerDAO = new WindPowerDAO();
			objWindPowerDAO.update(objWindPowerVO, conn);

			Scratchpad.ht.put("notification", "Wind Data Updated");
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public static void validateFields(WindPowerVO objWindPowerVO) throws Exception
	{
		// Performing mandatory field validations
		GPValidator.mandatoryValidator("Wind Speed", String.valueOf(objWindPowerVO.getWindSpeed()));
		GPValidator.mandatoryValidator("Air Density", String.valueOf(objWindPowerVO.getAirDensity()));
		GPValidator.mandatoryValidator("Humidity", String.valueOf(objWindPowerVO.getHumidity()));
		GPValidator.mandatoryValidator("Area", String.valueOf(objWindPowerVO.getArea()));
		GPValidator.mandatoryValidator("City", objWindPowerVO.getCity());
		GPValidator.mandatoryValidator("County", objWindPowerVO.getCounty());
		GPValidator.mandatoryValidator("State", objWindPowerVO.getState());

		// Performing numeric validations
		GPValidator.isNumber("Wind Speed", String.valueOf(objWindPowerVO.getWindSpeed()));
		GPValidator.isNumber("Air Density", String.valueOf(objWindPowerVO.getAirDensity()));
		GPValidator.isNumber("Humidity", String.valueOf(objWindPowerVO.getHumidity()));
		GPValidator.isNumber("Area", String.valueOf(objWindPowerVO.getArea()));

		// Performing string validations
		GPValidator.isAlpha("City", objWindPowerVO.getCity());
		GPValidator.isAlpha("County", objWindPowerVO.getCounty());
		GPValidator.isAlpha("State", objWindPowerVO.getState());
	}
}
