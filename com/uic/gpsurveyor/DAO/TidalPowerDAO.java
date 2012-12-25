/**
 * 
 */
package com.uic.gpsurveyor.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.uic.gpsurveyor.VO.TidalPowerVO;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.GenerateSequenceNumber;

/**
 * @author rajath
 *
 */
public class TidalPowerDAO {

	/**
	 * Constructor
	 */
	public TidalPowerDAO()
	{

	}

	/**
	 * This method is used to create a record in tidal_power_table
	 * @param objTidalPowerVO
	 * @param conn
	 * @throws SQLException
	 */
	public void create(TidalPowerVO objTidalPowerVO, java.sql.Connection conn) throws SQLException
	{
		try {
			String queryString = "INSERT INTO tidal_power_table VALUES (" +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?, ?)";

			PreparedStatement ps = conn.prepareStatement(queryString);

			int iNextSeqNo = GenerateSequenceNumber.getNextSequenceNumber("tp_seq", conn);

			int i = 1;

			ps.setInt(i++, iNextSeqNo);
			ps.setFloat(i++, objTidalPowerVO.getLength());
			ps.setFloat(i++, objTidalPowerVO.getDensity());
			ps.setFloat(i++, objTidalPowerVO.getHeight());
			ps.setFloat(i++, objTidalPowerVO.getFlow_rate());
			ps.setFloat(i++, objTidalPowerVO.getArea());
			ps.setString(i++, objTidalPowerVO.getCity());
			ps.setString(i++, objTidalPowerVO.getCounty());
			ps.setString(i++, objTidalPowerVO.getState());
			ps.setString(i++, objTidalPowerVO.getFree_field_1());
			ps.setString(i++, objTidalPowerVO.getFree_field_2());
			ps.setString(i++, objTidalPowerVO.getFree_field_3());
			ps.setString(i++, GPConstants.DEL_FLG_NO);
			ps.setString(i++, objTidalPowerVO.getR_cre_id());
			ps.setTimestamp(i++, new Timestamp(new java.util.Date().getTime()));
			ps.setString(i++, objTidalPowerVO.getR_mod_id());
			ps.setTimestamp(i++, new Timestamp(new java.util.Date().getTime()));

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(GPConstants.INSERT_ERROR);
		}
	}

	/**
	 * This method is used to search for record(s) in tidal_power_table
	 * @param tidalPowerObj
	 * @param conn
	 * @return ArrayList containing TidalPowerVO objects
	 * @throws SQLException
	 */
	public ArrayList search(TidalPowerVO tidalPowerObj, java.sql.Connection conn) throws SQLException
	{
		ArrayList alTidalPowerList = new ArrayList();

		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT " +
				"tpId, length, density, height, flow_rate, " +
				"area, city, county, state, free_field_1, " +
				"free_field_2, free_field_3, del_flg, r_cre_id, r_cre_time, " +
				"r_mod_id, r_mod_time " +
				"FROM tidal_power_table WHERE ");
		queryString.append(" 1 = 1 ");

		if(tidalPowerObj.getTpId() != 0)
		{
			queryString.append(" AND wpId = ? ");
		}

		if(tidalPowerObj.getLength() != 0.0f)
		{
			queryString.append(" AND length = ? ");
		}

		if(tidalPowerObj.getDensity() != 0.0f)
		{
			queryString.append(" AND density = ? ");
		}

		if(tidalPowerObj.getHeight() != 0.0f)
		{
			queryString.append(" AND height = ? ");
		}

		if(tidalPowerObj.getFlow_rate() != 0.0f)
		{
			queryString.append(" AND flow_rate = ? ");
		}

		if(tidalPowerObj.getArea() != 0.0f)
		{
			queryString.append(" AND area = ? ");
		}

		if(!tidalPowerObj.getCity().equals(""))
		{
			queryString.append(" AND city = ? ");
		}

		if(!tidalPowerObj.getCounty().equals(""))
		{
			queryString.append(" AND county = ? ");
		}

		if(!tidalPowerObj.getState().equals(""))
		{
			queryString.append(" AND state = ? ");
		}

		if(!tidalPowerObj.getFree_field_1().equals(""))
		{
			queryString.append(" AND free_field_1 = ? ");
		}

		if(!tidalPowerObj.getFree_field_2().equals(""))
		{
			queryString.append(" AND free_field_2 = ? ");
		}

		if(!tidalPowerObj.getFree_field_3().equals(""))
		{
			queryString.append(" AND free_field_3 = ? ");
		}

		if(!tidalPowerObj.getDel_flg().equals(""))
		{
			queryString.append(" AND del_flg = ? ");
		}

		if(!tidalPowerObj.getR_cre_id().equals(""))
		{
			queryString.append(" AND r_cre_id = ? ");
		}

		if(tidalPowerObj.getR_cre_time() != null)
		{
			queryString.append(" AND r_cre_time = ? ");
		}

		if(!tidalPowerObj.getR_mod_id().equals(""))
		{
			queryString.append(" AND r_mod_id = ? ");
		}

		if(tidalPowerObj.getR_mod_time() != null)
		{
			queryString.append(" AND r_mod_time = ? ");
		}

		try
		{
			PreparedStatement ps = conn.prepareStatement(queryString.toString());

			int i = 1;

			if(tidalPowerObj.getTpId() != 0)
			{
				ps.setInt(i++, tidalPowerObj.getTpId());
			}

			if(tidalPowerObj.getLength() != 0.0f)
			{
				ps.setFloat(i++, tidalPowerObj.getLength());
			}

			if(tidalPowerObj.getDensity() != 0.0f)
			{
				ps.setFloat(i++, tidalPowerObj.getDensity());
			}

			if(tidalPowerObj.getHeight() != 0.0f)
			{
				ps.setFloat(i++, tidalPowerObj.getHeight());
			}

			if(tidalPowerObj.getFlow_rate() != 0.0f)
			{
				ps.setFloat(i++, tidalPowerObj.getFlow_rate());
			}

			if(tidalPowerObj.getArea() != 0.0f)
			{
				ps.setFloat(i++, tidalPowerObj.getArea());
			}

			if(!tidalPowerObj.getCity().equals(""))
			{
				ps.setString(i++, tidalPowerObj.getCity());
			}

			if(!tidalPowerObj.getCounty().equals(""))
			{
				ps.setString(i++, tidalPowerObj.getCounty());
			}

			if(!tidalPowerObj.getState().equals(""))
			{
				ps.setString(i++, tidalPowerObj.getCounty());
			}

			if(!tidalPowerObj.getFree_field_1().equals(""))
			{
				ps.setString(i++, tidalPowerObj.getFree_field_1());
			}

			if(!tidalPowerObj.getFree_field_2().equals(""))
			{
				ps.setString(i++, tidalPowerObj.getFree_field_2());
			}

			if(!tidalPowerObj.getFree_field_3().equals(""))
			{
				ps.setString(i++, tidalPowerObj.getFree_field_3());
			}

			if(!tidalPowerObj.getDel_flg().equals(""))
			{
				ps.setString(i++, tidalPowerObj.getDel_flg());
			}

			if(!tidalPowerObj.getR_cre_id().equals(""))
			{
				ps.setString(i++, tidalPowerObj.getR_cre_id());
			}

			if(tidalPowerObj.getR_cre_time() != null)
			{
				ps.setTimestamp(i++, tidalPowerObj.getR_cre_time());
			}

			if(!tidalPowerObj.getR_mod_id().equals(""))
			{
				ps.setString(i++, tidalPowerObj.getR_mod_id());
			}

			if(tidalPowerObj.getR_mod_time() != null)
			{
				ps.setTimestamp(i++, tidalPowerObj.getR_mod_time());
			}

			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				TidalPowerVO objTidalPowerVO = new TidalPowerVO();

				int j = 1;

				objTidalPowerVO.setTpId(rs.getInt(j++));
				objTidalPowerVO.setLength(rs.getFloat(j++));
				objTidalPowerVO.setDensity(rs.getFloat(j++));
				objTidalPowerVO.setHeight(rs.getFloat(j++));
				objTidalPowerVO.setFlow_rate(rs.getFloat(j++));
				objTidalPowerVO.setArea(rs.getFloat(j++));
				objTidalPowerVO.setCity(rs.getString(j++));
				objTidalPowerVO.setCounty(rs.getString(j++));
				objTidalPowerVO.setState(rs.getString(j++));
				objTidalPowerVO.setFree_field_1(rs.getString(j++));
				objTidalPowerVO.setFree_field_2(rs.getString(j++));
				objTidalPowerVO.setFree_field_3(rs.getString(j++));
				objTidalPowerVO.setDel_flg(rs.getString(j++));
				objTidalPowerVO.setR_cre_id(rs.getString(j++));
				objTidalPowerVO.setR_cre_time(rs.getTimestamp(j++));
				objTidalPowerVO.setR_mod_id(rs.getString(j++));
				objTidalPowerVO.setR_mod_time(rs.getTimestamp(j++));

				alTidalPowerList.add(objTidalPowerVO);
			}
		}
		catch(SQLException e)
		{
			throw new SQLException(GPConstants.NO_RECORDS_FOUND);
		}
		return alTidalPowerList;
	}

	/**
	 * This method updates a record in the database
	 * @param objTidalPowerVO
	 * @param conn
	 * @return number of rows updated
	 * @throws SQLException
	 */
	public int update(TidalPowerVO objTidalPowerVO, java.sql.Connection conn) throws SQLException
	{
		/*
		 * NOTE: IN THE GUI, MAKE CMID A LABEL AND NOT AN EDITABLE TEXT FIELD
		 */

		int rowsAffected = 0;
		//		cmId, type, manufacturer, model, cost, name, service
		String queryString = new String("UPDATE tidal_power_table SET " +
				" length = ?, " +
				" density = ?, " +
				" height = ?, " +
				" flow_rate = ?, " +
				" area = ?, " +
				" city = ?, " +
				" county = ?, " +
				" state = ?, " +
				" free_field_1 = ?, " +
				" free_field_2 = ?, " +
				" free_field_3 = ?, " +
				" del_flg = ?, " +
				" r_cre_id = ?, " +
				" r_cre_time = ?, " +
				" r_mod_id = ?, " +
				" r_mod_time = ? " +
				" WHERE tpId = ?");

		try {
			PreparedStatement ps = conn.prepareStatement(queryString);

			int i = 1;

			ps.setFloat(i++, objTidalPowerVO.getLength());
			ps.setFloat(i++, objTidalPowerVO.getDensity());
			ps.setFloat(i++, objTidalPowerVO.getHeight());
			ps.setFloat(i++, objTidalPowerVO.getFlow_rate());
			ps.setFloat(i++, objTidalPowerVO.getArea());
			ps.setString(i++, objTidalPowerVO.getCity());
			ps.setString(i++, objTidalPowerVO.getCounty());
			ps.setString(i++, objTidalPowerVO.getState());
			ps.setString(i++, objTidalPowerVO.getFree_field_1());
			ps.setString(i++, objTidalPowerVO.getFree_field_2());
			ps.setString(i++, objTidalPowerVO.getFree_field_3());
			ps.setString(i++, objTidalPowerVO.getDel_flg());
			ps.setString(i++, objTidalPowerVO.getR_cre_id());
			ps.setTimestamp(i++, objTidalPowerVO.getR_cre_time());
			ps.setString(i++, objTidalPowerVO.getR_mod_id());
			ps.setTimestamp(i++, objTidalPowerVO.getR_mod_time());
			ps.setFloat(i++, objTidalPowerVO.getTpId());

			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(GPConstants.UPDATE_ERROR);
		}

		return rowsAffected;
	}
}
