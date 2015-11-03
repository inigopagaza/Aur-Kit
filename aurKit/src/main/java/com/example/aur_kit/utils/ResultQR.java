package com.example.aur_kit.utils;

public class ResultQR {
	
	private String code;
	private double orientation;
	
	public ResultQR(String codigo, double orientacion) {
		super();
		this.code = codigo;
		this.orientation = orientacion;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public double getOrientation() {
		return orientation;
	}
	
	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}
}
