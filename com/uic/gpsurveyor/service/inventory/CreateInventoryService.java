/**
 * 
 */
package com.uic.gpsurveyor.service.inventory;

import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.CostMaintDAO;
import com.uic.gpsurveyor.VO.CostMaintVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class CreateInventoryService {

	public void execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();

		try {
			CostMaintVO objCostMaintVO = new CostMaintVO();

			objCostMaintVO = (CostMaintVO)ht.get("CostMaintVO");
			// Set the CostMaint object with the inputs from the Create Inventory form
			/*objCostMaintVO.setCost(Float.parseFloat(ht.get("cost").toString()));
			objCostMaintVO.setManufacturer(ht.get("manufacturer").toString());
			objCostMaintVO.setModel(ht.get("model").toString());
			objCostMaintVO.setModelName(ht.get("modelName").toString());
			objCostMaintVO.setRotorLength(Float.parseFloat(ht.get("rotorLength").toString()));
			objCostMaintVO.setService(Float.parseFloat(ht.get("service").toString()));
			objCostMaintVO.setType(ht.get("type").toString());*/

			// Perform validations
			validateFields(objCostMaintVO);

			// Inserting a record into login_table once a user is created.
			CostMaintDAO objCostMaintDAO = new CostMaintDAO();
			objCostMaintDAO.create(objCostMaintVO, conn);
		} catch (Exception e) {
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
		GPValidator.isAlpha("Model", objCostMaintVO.getModel());
	}
}
