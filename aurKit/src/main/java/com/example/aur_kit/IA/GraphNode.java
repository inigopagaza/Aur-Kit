package com.example.aur_kit.IA;

import java.util.ArrayList;

import android.graphics.Point;

public class GraphNode extends Point{
	
	private String punto;
	private ArrayList<GraphNode> vertices = new ArrayList<>();
	private int piso;
	
	public GraphNode() {
		super();
	}

	public GraphNode(String punto, int piso, Point point) {
		super();
		this.punto = punto;
		this.vertices = new ArrayList<GraphNode>();
		this.piso = piso;
		this.x = point.x;
		this.y = point.y;
	}

	public void addVert(GraphNode n){
		vertices.add(n);
	}

	public String getPunto() {
		return punto;
	}

	public void setPunto(String punto) {
		this.punto = punto;
	}
	
	public ArrayList<GraphNode> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<GraphNode> vertices) {
		this.vertices = vertices;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj!=null && obj instanceof GraphNode)
		 {
			boolean b = (((GraphNode)obj).punto) == this.punto ? true:false;
			return b;
		 }
		 else
		 {
			 return false;
		 }
	}

	@Override
	public String toString() {
		return "Nodo [punto=" + punto + "]";
	}	
	
	
}
