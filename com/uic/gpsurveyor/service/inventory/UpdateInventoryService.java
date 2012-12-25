/**
 * 
 */
package com.uic.gpsurveyor.service.inventory;

import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.CostMaintDAO;
import com.uic.gpsurveyor.VO.CostMaintVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class UpdateInventoryService {

	public static void execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();

		try {
			CostMaintVO objCostMaintVO = new CostMaintVO();

			if(ht.get("CostMaintVO")!=null)
			{
				objCostMaintVO = (CostMaintVO)ht.get("CostMaintVO");
			}

			// Perform validations
			validateFields(objCostMaintVO);

			// Inserting a record into login_table once a user is created.
			CostMaintDAO objCostMaintDAO = new CostMaintDAO();
			objCostMaintDAO.update(objCostMaintVO, conn);

			Scratchpad.ht.put("notification", "Inventory Updated");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void validateFields(CostMaintVO objCostMaintVO) throws Exception
	{
		// Performing mandatory field validations
		GPValidator.mandatoryValidator("Type", objCostMaintVO.getType());
		GPValidator.mandatoryValidator("Cost", String.valueOf(objCostMaintVO.getCost()));
		GPValidator.mandatoryValidator("Manufacturer", String.valueOf(objCostMaintVO.getManufacturer()));
		GPValidator.mandatoryValidator("Model", objCostMaintVO.getModel());
		GPValidator.mandatoryValidator("Model Name", objCostMaintVO.getModelName());
		GPValidator.mandatoryValidator("Rotor Length", String.valueOf(objCostMaintVO.getRotorLength()));
		GPValidator.mandatoryValidator("Service", String.valueOf(objCostMaintVO.getService()));

		// Performing numeric validations
		GPValidator.isNumber("Cost", String.valueOf(objCostMaintVO.getCost()));
		GPValidator.isNumber("Rotor Length", String.valueOf(objCostMaintVO.getRotorLength()));
		GPValidator.isNumber("Service", String.valueOf(objCostMaintVO.getService()));

		// Performing string validations
		GPValidator.isAlpha("Type", objCostMaintVO.getType());
		GPValidator.isAlpha("Manufacturer", objCostMaintVO.getManufacturer());
		//		GPValidator.isAlpha("Model", objCostMaintVO.getModel());
	}
}
