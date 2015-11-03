package com.example.aur_kit.IA;

import java.util.ArrayList;

public class GraphEnvironment {
	
	private ArrayList<GraphNode> grafo = new ArrayList<GraphNode>();
	private String destino;
	private String posAct;

	public GraphEnvironment() {
		super();
	}

	public GraphEnvironment(ArrayList<GraphNode> grafo, String destino, String posAct) {
		super();
		this.grafo = grafo;
		this.destino = destino;
		this.posAct = posAct;
	}

	public ArrayList<GraphNode> getGrafo() {
		return grafo;
	}

	public void setGrafo(ArrayList<GraphNode> grafo) {
		this.grafo = grafo;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getPosAct() {
		return posAct;
	}

	public void setPosAct(String posAct) {
		this.posAct = posAct;
	}

	public GraphNode getNodo(String pos){
		GraphNode nodo = new GraphNode();
		for(int i = 0; i< grafo.size();i++){
			if(grafo.get(i).getPunto().equals(pos)){
				nodo = ((ArrayList<GraphNode>) grafo).get(i);
				continue;
			}
		}
		return nodo;
	}
	
	public void addNodo(GraphNode n){
		grafo.add(n);
	}
	
	public boolean equals(Object obj)
	{
		if(obj!=null && obj instanceof GraphEnvironment)
			return this.posAct == ((GraphEnvironment) obj).posAct ? true : false;
		else return false;		
	}
	
	public GraphEnvironment clone()
	{
		GraphEnvironment clon = new GraphEnvironment();
		clon.destino=this.destino;
		clon.posAct=this.posAct;
		clon.grafo = this.grafo;
		return clon;
	}
	
	@Override
	public String toString()
	{
		return "Pos: "+posAct;
	}
}
