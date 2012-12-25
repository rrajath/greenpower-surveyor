/**
 * 
 */
package com.uic.gpsurveyor.VO;

/**
 * @author rajath
 *
 */
public class CostMaintVO {

	private int cmId;
	private String type;
	private String manufacturer;
	private String model;
	private String modelName;
	private float rotorLength;
	private float cost;
	private float service;

	public CostMaintVO()
	{
		cmId = 0;
		type = "";
		manufacturer = "";
		model = "";
		modelName = "";
		cost = 0.0f;
		service = 0.0f;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the cost
	 */
	public float getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}
	/**
	 * @return the service
	 */
	public float getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(float service) {
		this.service = service;
	}
	/**
	 * @return the cmId
	 */
	public int getCmId() {
		return cmId;
	}
	/**
	 * @param cmId the cmId to set
	 */
	public void setCmId(int cmId) {
		this.cmId = cmId;
	}
	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}
	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the rotorLength
	 */
	public float getRotorLength() {
		return rotorLength;
	}

	/**
	 * @param rotorLength the rotorLength to set
	 */
	public void setRotorLength(float rotorLength) {
		this.rotorLength = rotorLength;
	}
}
