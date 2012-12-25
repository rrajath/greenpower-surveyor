package com.uic.gpsurveyor.validator;

import java.util.Enumeration;
import java.util.Hashtable;

public class GPValidator
{
	/**
	 * This method checks if the given input string contains special characters
	 * @param input
	 * @return true if it does not contain special characters; false, otherwise
	 * @throws Exception
	 */
	public static void validateString(String fieldName, String fieldValue) throws Exception
	{
		if(fieldValue != null && !fieldValue.equals(""))
		{
			if(!fieldValue.matches("[a-zA-Z0-9]+"))
				throw new Exception(fieldName + " must contain only alphanumeric characters");
		}
	}

	/**
	 * This method checks if the given input string contains only alphabets
	 * @param input
	 * @return true if it contains only alphabets; false, otherwise
	 * @throws Exception
	 */
	public static void isAlpha(String fieldName, String fieldValue) throws Exception
	{
		if(fieldValue != null && !fieldValue.equals(""))
		{
			if(!fieldValue.matches("[a-zA-Z ]+"))
				throw new Exception(fieldName + " must contain only alphabets");
		}
	}

	/**
	 * This method checks if the given input contains only numbers
	 * @param input
	 * @return true if it contains only numbers; false, otherwise
	 * @throws Exception
	 */
	public static void isNumber(String fieldName, String fieldValue) throws Exception
	{
		if(fieldValue != null)
		{

			if(!fieldValue.matches("[0-9\\.]+"))
				throw new Exception(fieldName + " must contain only numbers");
		}
	}

	/**
	 * This method checks if the input field is empty
	 * @param input
	 * @return true if the field is not empty; false, otherwise
	 * @throws Exception
	 */
	public static void mandatoryValidator(String fieldName, String fieldValue) throws Exception
	{
		if(fieldValue.equals(""))
			throw new Exception(fieldName + " is mandatory");
	}

	public static boolean validatePassword(String input) throws Exception
	{
		String sError = new String();
		if(input.length() < 7)
			throw new Exception("Length of password must be at least 6 characters");

		/*
		 * TODO: add more validations. Also check the return value!
		 * 
		 */
		return true;
	}

	public static void mandatoryFieldValidaitons(Hashtable ht) throws Exception
	{
		Enumeration keys = ht.keys();
		do
		{
			Object key = keys.nextElement();
			String value = ht.get(key).toString();

			if(value.equals(""))
			{
				throw new Exception(key + " is mandatory");
			}
		}while(keys.hasMoreElements());
	}

	public static void validateEmail(String fieldValue) throws Exception
	{
		if(!fieldValue.equals(""))
			if(!fieldValue.matches("[a-zA-Z0-9]+[\\._]?[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.{1}[a-zA-Z0-9]+[\\.]*[a-zA-Z0-9]?"))
				throw new Exception("Email invalid");
	}
}