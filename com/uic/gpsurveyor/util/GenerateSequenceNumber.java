/**
 * 
 */
package com.uic.gpsurveyor.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author rajath
 *
 */
public class GenerateSequenceNumber {

	public static int getNextSequenceNumber(String tableName, java.sql.Connection conn) throws SQLException
	{
		String queryString = "INSERT INTO " + tableName + " VALUES (NULL)";
		int nextSeqNum = 0;

		try
		{
			PreparedStatement ps = conn.prepareStatement(queryString);
			ps.execute();

			queryString = "SELECT LAST_INSERT_ID()";

			ps = conn.prepareStatement(queryString);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				nextSeqNum = rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return nextSeqNum;
	}
}
