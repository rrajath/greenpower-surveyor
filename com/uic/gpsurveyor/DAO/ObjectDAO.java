/**
 * 
 */
package com.uic.gpsurveyor.DAO;

import java.util.ArrayList;

/**
 * @author rajath
 *
 */
public interface ObjectDAO {

	public abstract void create(Object objCostMaintVO, java.sql.Connection conn);

	public abstract ArrayList search(Object objCostMaintVO, java.sql.Connection conn);

	public abstract int update(Object objCostMaintVO, java.sql.Connection conn);
}
