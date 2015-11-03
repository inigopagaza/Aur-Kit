package com.example.aur_kit.IA;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.example.aur_kit.IA.xml.InformationXMLReader;

import android.content.Context;
import android.graphics.Point;

public class GraphXMLReader extends InformationXMLReader {
	
	private GraphEnvironment entSin;

	public GraphXMLReader(Context context, boolean elevator) {
		super(context, elevator);
	}
	
	public Object getInformation() 
	{
		return entSin;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		try 
		{
			if (qName.equals("pfc:info"))
			{
				this.entSin = new GraphEnvironment();
			}
			else if (qName.equals("pfc:nodes"));
			else if (qName.equals("pfc:node0"));
			else if (qName.equals("pfc:node1"));
			else if (qName.equals("pfc:node2"));
			else if (qName.equals("pfc:node3"));
			else if (qName.equals("pfc:node4"));
			else if (qName.equals("pfc:node5"));
			else if (qName.equals("pfc:node")) 
			{
				entSin.getGrafo().add(new GraphNode(String.valueOf(attributes.getValue("point")),
					Integer.valueOf(attributes.getValue("floor")), 
					new Point(Integer.valueOf(attributes.getValue("x")),Integer.valueOf(attributes.getValue("y")))));
			}
			else if (qName.equals("pfc:verts"));
			else if (qName.equals("pfc:vert0"));
			else if (qName.equals("pfc:vert1"));
			else if (qName.equals("pfc:vert2"));
			else if (qName.equals("pfc:vert3"));
			else if (qName.equals("pfc:vert4"));
			else if (qName.equals("pfc:vert5"));
			else if (qName.equals("pfc:vertVert"));
			else if (qName.equals("pfc:vert"))
			{
				entSin.getNodo(String.valueOf(attributes.getValue("source"))).addVert(entSin.getNodo(String.valueOf(attributes.getValue("destination"))));
			}
		}
		catch(Exception ex){
			System.out.println(this.getClass().getName() + ".startElement(): " + ex);
		}
		
	}

}
