package com.example.aur_kit.IA;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.example.aur_kit.IA.formulation.State;
import com.example.aur_kit.IA.search.blind.BreadthFS;
import com.example.aur_kit.IA.xml.InformationXMLReader;

public class StartSearch {
	
	public static ArrayList<GraphNode> calculateRoute(String source, String destination, boolean elevator, Context context) {
		try {
				
			InformationXMLReader entornoSAXParser = new GraphXMLReader(context, elevator);
			
			GraphEnvironment env = (GraphEnvironment) entornoSAXParser.getInformation();
			env.setPosAct(source);
			env.setDestino(destination);
			
			FindRoute problem = new FindRoute(env.getGrafo());
			problem.createOperators();
			problem.addInitialState(new State(env));

			List<String> res = problem.solve(BreadthFS.getInstance());
			
			ArrayList<GraphNode> routeNode = new ArrayList<GraphNode>();
			if(res != null)
			{
			
				routeNode.add(env.getNodo(source));
				for(int i = 0;i<res.size();i++)
				{
					routeNode.add(env.getNodo(res.get(i)));
				}
			}
			else
			{
				routeNode = null;
			}
			return routeNode;
			
		} catch (Exception ex) {
			System.err.println("% [Main Program] Error: " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
	}
	

}
