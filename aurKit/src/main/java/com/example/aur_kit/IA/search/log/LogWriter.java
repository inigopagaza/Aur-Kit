package com.example.aur_kit.IA.search.log;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogWriter extends OutputStreamWriter {


	/**
	 * Constructor method.
	 * @param fileName String that is the log file's name.
	 * @throws Exception In case an error occurs while creating the log file.
	 */
	public LogWriter(String fileName) throws Exception {
		super(new FileOutputStream(fileName));
		this.openLog();
	}
	
	/**
	 * Opens the log.
	 */
	private void openLog() {
		try {
			this.write("Log: '" + this.getClass().getSimpleName() + "' (");						
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy / HH:mm:ss", java.util.Locale.getDefault());
			this.write(formatter.format(Calendar.getInstance().getTime()) + ")\r\n\r\n");
			this.flush();
		} catch (Exception ex) {
			System.err.println("% [" + this.getClass().getName() + 
					           "] Error while opening the log: " + ex.getMessage());
		}
	}
	
	/**
	 * Closes the log.
	 */
	public void closeLog() {
		try {
			this.close();
		} catch (Exception ex) {
			System.err.println("% [" + this.getClass().getName() + 
							   "] Error while closing the log: " + ex.getMessage());
		}
	}
}
