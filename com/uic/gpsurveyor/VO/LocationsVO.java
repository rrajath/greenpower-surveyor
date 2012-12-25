/**
 * 
 */
package com.uic.gpsurveyor.VO;

/**
 * @author rajath
 *
 */
public class LocationsVO {

	private int loc_id;
	private String cityCode;
	private String cityName;
	private String county;
	private String state;
	private String free_field_1;
	private String free_field_2;
	private String free_field_3;

	public LocationsVO() {
		loc_id = 0;
		cityCode = "";
		cityName = "";
		county = "";
		state = "";
		free_field_1 = "";
		free_field_2 = "";
		free_field_3 = "";
	}
	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the free_field_1
	 */
	public String getFree_field_1() {
		return free_field_1;
	}
	/**
	 * @param free_field_1 the free_field_1 to set
	 */
	public void setFree_field_1(String free_field_1) {
		this.free_field_1 = free_field_1;
	}
	/**
	 * @return the free_field_2
	 */
	public String getFree_field_2() {
		return free_field_2;
	}
	/**
	 * @param free_field_2 the free_field_2 to set
	 */
	public void setFree_field_2(String free_field_2) {
		this.free_field_2 = free_field_2;
	}
	/**
	 * @return the free_field_3
	 */
	public String getFree_field_3() {
		return free_field_3;
	}
	/**
	 * @param free_field_3 the free_field_3 to set
	 */
	public void setFree_field_3(String free_field_3) {
		this.free_field_3 = free_field_3;
	}
	/**
	 * @return the loc_id
	 */
	public int getLoc_id() {
		return loc_id;
	}
	/**
	 * @param loc_id the loc_id to set
	 */
	public void setLoc_id(int loc_id) {
		this.loc_id = loc_id;
	}
}
