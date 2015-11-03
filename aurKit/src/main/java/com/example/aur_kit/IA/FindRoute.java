package com.example.aur_kit.IA;

import java.util.ArrayList;
import java.util.List;

import com.example.aur_kit.IA.formulation.Operator;
import com.example.aur_kit.IA.formulation.Problem;
import com.example.aur_kit.IA.formulation.State;
import com.example.aur_kit.IA.search.Node;
import com.example.aur_kit.IA.search.SearchMethod;

public class FindRoute extends Problem{
	
	private ArrayList<GraphNode> grafo = new ArrayList<GraphNode>();
	
	public FindRoute() {
		super();
	}
	
	public FindRoute(ArrayList<GraphNode> grafo) {
		super();
		this.grafo = grafo;
	}

	@Override
	protected void createOperators() {
		if(grafo != null)
		{
			for(int i = 0; i<grafo.size(); i++)
			{
				GraphNode n = grafo.get(i);
				for (int y = 0; y<n.getVertices().size();y++)
				{
					Operator operator = new Move(n,n.getVertices().get(y));
					this.addOperator(operator);
				}
			}
		}
		
	}
	
	public boolean isFinalState(State state)
	{
		if(state != null)
		{
			GraphEnvironment ent = (GraphEnvironment)state.getInformacion();
			boolean b = (ent.getPosAct().equals(ent.getDestino()));
			return b;
		}
		else
			return false;
	}
	
	public List<String> solve(SearchMethod searchMethod) {
		
		Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
			
		if (finalNode != null) {
			System.out.println("Solution found!!");
			List<String> operators = new ArrayList<String>();
			searchMethod.solutionPath(finalNode, operators);
			searchMethod.createSolutionLog(operators);
			return operators;
		} else {
			System.out.println("Unable to find the solution!");
			return null;
		}
		
	}
}
