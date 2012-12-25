/**
 * 
 */
package com.uic.gpsurveyor.service.tidal;

import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.TidalPowerDAO;
import com.uic.gpsurveyor.VO.TidalPowerVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class InsertTidalDataService {

	public static void execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();

		try {

			TidalPowerVO objTidalPowerVO = new TidalPowerVO();

			if(ht.get("TidalPowerVO")!=null)
			{
				objTidalPowerVO = (TidalPowerVO)ht.get("TidalPowerVO");
			}

			// Performing validations on all fields
			//			validateFields(objTidalPowerVO);

			TidalPowerDAO objTidalPowerDAO = new TidalPowerDAO();
			objTidalPowerDAO.create(objTidalPowerVO, conn);

			Scratchpad.ht.put("notification", "Tidal Data Created");
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public static void validateFields(TidalPowerVO objTidalPowerVO) throws Exception
	{
		// Performing mandatory field validations
		GPValidator.mandatoryValidator("Length", String.valueOf(objTidalPowerVO.getLength()));
		GPValidator.mandatoryValidator("Density", String.valueOf(objTidalPowerVO.getDensity()));
		GPValidator.mandatoryValidator("Height", String.valueOf(objTidalPowerVO.getHeight()));
		GPValidator.mandatoryValidator("Flow Rate", String.valueOf(objTidalPowerVO.getFlow_rate()));
		GPValidator.mandatoryValidator("City", objTidalPowerVO.getCity());
		GPValidator.mandatoryValidator("County", objTidalPowerVO.getCounty());
		GPValidator.mandatoryValidator("State", objTidalPowerVO.getState());

		// Performing numeric validations
		GPValidator.isNumber("Length", String.valueOf(objTidalPowerVO.getLength()));
		GPValidator.isNumber("Density", String.valueOf(objTidalPowerVO.getDensity()));
		GPValidator.isNumber("Height", String.valueOf(objTidalPowerVO.getHeight()));
		GPValidator.isNumber("Flow Rate", String.valueOf(objTidalPowerVO.getFlow_rate()));

		// Performing string validations
		GPValidator.isAlpha("City", objTidalPowerVO.getCity());
		GPValidator.isAlpha("County", objTidalPowerVO.getCounty());
		GPValidator.isAlpha("State", objTidalPowerVO.getState());
	}
}
