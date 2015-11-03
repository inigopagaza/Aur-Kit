package com.example.aur_kit.utils;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.content.Context;

import com.example.aur_kit.IA.xml.InformationXMLReader;

public class QRtoMapXMLReader extends InformationXMLReader{
	
	private HashMap<String, ResultQR> qrClassMap;

	public QRtoMapXMLReader(Context context) {
		super(context);
	}
	
	public Object getInformation() 
	{
		return qrClassMap;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		try 
		{
			if (qName.equals("pfc:info"))
			{
				this.qrClassMap = new HashMap<String, ResultQR>();
			}
			else if (qName.equals("pfc:class0"));
			else if (qName.equals("pfc:class1"));
			else if (qName.equals("pfc:class2"));
			else if (qName.equals("pfc:class3"));
			else if (qName.equals("pfc:class")) 
			{
				qrClassMap.put(String.valueOf(attributes.getValue("class")), 
						new ResultQR(String.valueOf(attributes.getValue("qr")), 
								Double.valueOf(attributes.getValue("orientation"))));
			}
		}
		catch(Exception ex){
			System.out.println(this.getClass().getName() + ".startElement(): " + ex);
		}
		
	}

}
