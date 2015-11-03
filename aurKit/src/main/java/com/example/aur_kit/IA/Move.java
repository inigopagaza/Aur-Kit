package com.example.aur_kit.IA;

import com.example.aur_kit.IA.formulation.Operator;
import com.example.aur_kit.IA.formulation.State;

public class Move extends Operator{
	
	private GraphNode origen;
	private GraphNode destino;

	public Move(GraphNode origen, GraphNode destino) {
		super(String.valueOf(destino.getPunto()));
		this.origen = origen;
		this.destino = destino;
	}

	@Override
	protected State effect(State state) {
		GraphEnvironment ent = (GraphEnvironment)state.getInformacion();
		GraphEnvironment newEnt = ent.clone();
		newEnt.setPosAct(destino.getPunto());
		return new State(newEnt);
	}

	@Override
	protected boolean isApplicable(State state) {
		GraphEnvironment ent = (GraphEnvironment)state.getInformacion();
		String pos = ent.getPosAct();
		if(origen.getPunto().equals(pos))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
