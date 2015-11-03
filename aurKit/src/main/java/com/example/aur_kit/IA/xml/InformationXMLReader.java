package com.example.aur_kit.IA.xml;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * It implements the funcionality of a parser for an XML document that contains the
 * description of the problem environment.
 *  */
public abstract class InformationXMLReader extends DefaultHandler {
	Context context;
	/**
	 * problem environment information.
	 */
	private Object information = null;

	/**
	 * Constructor method.
	 * 
	 * @param context
	 *            	Context of the MainActivity.
	 * @param elevator
	 * 				Boolean to select the graph to read.
	 */
	public InformationXMLReader(Context context, boolean elevator) {
		this.context = context;
		this.processXML(elevator);
	}

	/**
	 * It parses an XML file.
	 * 
	 * @param elevator
	 *            Boolean to select the graph to read.
	 */
	private void processXML(boolean elevator) {
		try {
			AssetManager assetManager = context.getAssets();
			InputStream xml;
			if(elevator)
				xml = assetManager.open("entornoAsc.xml");
			else
				xml = assetManager.open("entorno.xml");
			
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setValidating(false);
			parserFactory.setNamespaceAware(true);
			SAXParser parser = parserFactory.newSAXParser();
			parser.parse(xml, this);
			xml.close();
		} catch (Exception ex) {
			System.err.println(this.getClass().getName()
					+ ".parseDocument(): " + ex);
		}
	}
	
	/**
	 * Constructor method.
	 * 
	 * @param @param context
	 *            	Context of the MainActivity.
	 */
	public InformationXMLReader(Context context) {
		this.context = context;
		this.processXML();
	}

	/**
	 * It parses an XML file.
	 * 
	 * @param fileXML
	 *            String that is the name of the file to be parsed.
	 */
	private void processXML() {
		try {
			AssetManager assetManager = context.getAssets();
			InputStream xml = assetManager.open("QRtoMap.xml");
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setValidating(false);
			parserFactory.setNamespaceAware(true);
			SAXParser parser = parserFactory.newSAXParser();
			parser.parse(xml, this);
		} catch (Exception ex) {
			System.err.println(this.getClass().getName()
					+ ".parseDocument(): " + ex);
		}
	}

	/**
	 * It returns the problem state information.
	 * 
	 * @return Object having the problem state information.
	 */
	public Object getInformation() {
		return this.information;
	}

	/**
	 * Implementation of the startElement method from class DefaultHandler. This
	 * is executed every time the parser finds a new XML element.
	 * 
	 * @param uri
	 *            String having the URL names space.
	 * @param localName
	 *            String having the element's name without the names space.
	 * @param qName
	 *            String having the element's name with the names space.
	 * @param attributes
	 *            Attributes having the list of element's attributes.
	 * @throws SAXException
	 */
	public abstract void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException;
}