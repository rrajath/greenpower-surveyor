/**
 * 
 */
package com.uic.gpsurveyor.service.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author rajath
 *
 */
public class GetRealTimeDataService {

	public GetRealTimeDataService()
	{

	}

	public static Hashtable execute(String cityCode) throws Exception {

		Hashtable ht = new Hashtable();
		String sUrl = "http://free.worldweatheronline.com/feed/weather.ashx?q=" + cityCode + "&format=xml&num_of_days=2&key=0a9e731dd2053353122711";

		URL url = new URL(sUrl);
		BufferedReader in;
		try
		{
			in = new BufferedReader(new InputStreamReader(url.openStream()));
		}
		catch(Exception e)
		{
			throw new Exception("No Internet Connection");
		}
		String inputLine;
		while ((inputLine = in.readLine()) != null);
		//			System.out.println(inputLine);
		in.close();

		String sTemp = getValue("//temp_F/text()", sUrl);
		float fTemp = Float.parseFloat(sTemp);
		float fWindSpeed = Float.parseFloat(getValue("//windspeedMiles/text()", sUrl));
		float fPressure = Float.parseFloat(getValue("//pressure/text()", sUrl))*100;
		float fHumidity = Float.parseFloat(getValue("//humidity/text()",sUrl));
		float fAirDensity = 0.0f;

		/*
		 * To calculate air density, we need air pressure and temperature in Kelvin
		 * Formula: air density = (air pressure)/(R*T)
		 * where, R is a constant = 287.05
		 */
		float fGasConstant = 287.05f;
		float fKelvin = 5*(fTemp - 32.0f)/9 + 273;

		fAirDensity = fPressure/(fGasConstant*fKelvin);

		ht.put("airDensity", fAirDensity);
		ht.put("windSpeed", fWindSpeed);
		ht.put("humidity", fHumidity);

		return ht;
	}

	public static String getValue(String input, String sUrl) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(sUrl);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile(input);

		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;

		return nodes.item(0).getNodeValue();
	}
}
