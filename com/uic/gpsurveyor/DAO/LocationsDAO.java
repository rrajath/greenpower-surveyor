/**
 * 
 */
package com.uic.gpsurveyor.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.uic.gpsurveyor.VO.LocationsVO;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.GenerateSequenceNumber;

/**
 * @author rajath
 *
 */
public class LocationsDAO {

	/**
	 * @param objLocationsVO
	 * @param conn
	 * @throws SQLException
	 */
	public void create(LocationsVO objLocationsVO, java.sql.Connection conn) throws SQLException
	{
		try {
			String queryString = "INSERT INTO locations VALUES (" +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(queryString);

			int iNextSeqNo = GenerateSequenceNumber.getNextSequenceNumber("loc_seq", conn);

			int i = 1;

			ps.setInt(i++, iNextSeqNo);
			ps.setString(i++, objLocationsVO.getCityCode());
			ps.setString(i++, objLocationsVO.getCityName());
			ps.setString(i++, objLocationsVO.getCounty());
			ps.setString(i++, objLocationsVO.getState());
			ps.setString(i++, objLocationsVO.getFree_field_1());
			ps.setString(i++, objLocationsVO.getFree_field_2());
			ps.setString(i++, objLocationsVO.getFree_field_3());

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(GPConstants.INSERT_ERROR);
		}
	}

	/**
	 * @param objLocationsVO
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public ArrayList search(LocationsVO objLocationsVO, java.sql.Connection conn) throws SQLException
	{
		ArrayList alLocationsList = new ArrayList();

		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT " +
				"loc_id, city_code, city_name, county, state, " +
				"free_field_1, free_field_2, free_field_3 " +
				"FROM locations WHERE ");
		queryString.append(" 1 = 1 ");

		if(objLocationsVO.getLoc_id() != 0)
		{
			queryString.append(" AND loc_id = ? ");
		}

		if(!objLocationsVO.getCityCode().equals(""))
		{
			queryString.append(" AND city_code = ? ");
		}

		if(!objLocationsVO.getCityName().equals(""))
		{
			queryString.append(" AND city_name = ? ");
		}

		if(!objLocationsVO.getCounty().equals(""))
		{
			queryString.append(" AND county = ? ");
		}

		if(!objLocationsVO.getState().equals(""))
		{
			queryString.append(" AND state = ? ");
		}

		if(!objLocationsVO.getFree_field_1().equals(""))
		{
			queryString.append(" AND free_field_1 = ? ");
		}

		if(!objLocationsVO.getFree_field_2().equals(""))
		{
			queryString.append(" AND free_field_2 = ? ");
		}

		if(!objLocationsVO.getFree_field_3().equals(""))
		{
			queryString.append(" AND free_field_3 = ? ");
		}

		try
		{
			PreparedStatement ps = conn.prepareStatement(queryString.toString());

			int i = 1;

			if(objLocationsVO.getLoc_id() != 0)
			{
				ps.setInt(i++, objLocationsVO.getLoc_id());
			}

			if(!objLocationsVO.getCityCode().equals(""))
			{
				ps.setString(i++, objLocationsVO.getCityCode());
			}

			if(!objLocationsVO.getCityName().equals(""))
			{
				ps.setString(i++, objLocationsVO.getCityName());
			}

			if(!objLocationsVO.getCounty().equals(""))
			{
				ps.setString(i++, objLocationsVO.getCounty());
			}

			if(!objLocationsVO.getState().equals(""))
			{
				ps.setString(i++, objLocationsVO.getCounty());
			}

			if(!objLocationsVO.getFree_field_1().equals(""))
			{
				ps.setString(i++, objLocationsVO.getFree_field_1());
			}

			if(!objLocationsVO.getFree_field_2().equals(""))
			{
				ps.setString(i++, objLocationsVO.getFree_field_2());
			}

			if(!objLocationsVO.getFree_field_3().equals(""))
			{
				ps.setString(i++, objLocationsVO.getFree_field_3());
			}

			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				LocationsVO locationsVO = new LocationsVO();

				int j = 1;

				locationsVO.setLoc_id(rs.getInt(j++));
				locationsVO.setCityCode(rs.getString(j++));
				locationsVO.setCityName(rs.getString(j++));
				locationsVO.setCounty(rs.getString(j++));
				locationsVO.setState(rs.getString(j++));
				locationsVO.setFree_field_1(rs.getString(j++));
				locationsVO.setFree_field_2(rs.getString(j++));
				locationsVO.setFree_field_3(rs.getString(j++));

				alLocationsList.add(locationsVO);
			}
		}
		catch(SQLException e)
		{
			throw new SQLException(GPConstants.NO_RECORDS_FOUND);
		}
		return alLocationsList;
	}

	public int update(LocationsVO objLocationsVO, java.sql.Connection conn) throws SQLException
	{
		/*
		 * NOTE: IN THE GUI, MAKE CMID A LABEL AND NOT AN EDITABLE TEXT FIELD
		 */

		int rowsAffected = 0;
		//		cmId, type, manufacturer, model, cost, name, service
		String queryString = new String("UPDATE locations SET " +
				" city_code = ?, " +
				" city_name = ?, " +
				" county = ?, " +
				" state = ?, " +
				" free_field_1 = ?, " +
				" free_field_2 = ?, " +
				" free_field_3 = ? " +
				" WHERE loc_id = ?");

		try {
			PreparedStatement ps = conn.prepareStatement(queryString);

			int i = 1;

			ps.setString(i++, objLocationsVO.getCityCode());
			ps.setString(i++, objLocationsVO.getCityName());
			ps.setString(i++, objLocationsVO.getCounty());
			ps.setString(i++, objLocationsVO.getState());
			ps.setString(i++, objLocationsVO.getFree_field_1());
			ps.setString(i++, objLocationsVO.getFree_field_2());
			ps.setString(i++, objLocationsVO.getFree_field_3());
			ps.setInt(i++, objLocationsVO.getLoc_id());

			rowsAffected = ps.executeUpdate();

			if(rowsAffected == 0)
			{
				throw new SQLException(GPConstants.UPDATE_ERROR);
			}
		} catch (SQLException e) {
			throw new SQLException(GPConstants.UPDATE_ERROR);
		}

		return rowsAffected;
	}
}
