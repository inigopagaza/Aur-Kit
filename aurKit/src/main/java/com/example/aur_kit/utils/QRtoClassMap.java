package com.example.aur_kit.utils;

import java.util.HashMap;

import android.content.Context;

import com.example.aur_kit.IA.xml.InformationXMLReader;

public class QRtoClassMap {
	
	private HashMap<String, ResultQR> qrClassMap;

	public QRtoClassMap(Context context) {
		super();
		this.qrClassMap = new HashMap<String, ResultQR>();
		loadMap(context);
	}

	public HashMap<String, ResultQR> getQrClassMap() {
		return qrClassMap;
	}

	@SuppressWarnings("unchecked")
	public void loadMap(Context context){
		InformationXMLReader entornoSAXParser = new QRtoMapXMLReader(context);
		qrClassMap = (HashMap<String, ResultQR>) entornoSAXParser.getInformation();
	}
	
	public String classOrientation(String destination){
		
		String orient;
		double orientation = qrClassMap.get(destination).getOrientation();
		
		if (orientation == 180) 
				orient = "up";
		else if (orientation == 90) 
			orient = "right";
		else if (orientation == 270) 
			orient = "left";
		else 
			orient = "down";
		
		return orient;
	}
}
