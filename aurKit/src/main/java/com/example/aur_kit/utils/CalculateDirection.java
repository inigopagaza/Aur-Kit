package com.example.aur_kit.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.aur_kit.R;
import com.example.aur_kit.IA.GraphNode;

public class CalculateDirection {
	
	public static Drawable calculateDirectionQR(Context context, GraphNode now, GraphNode destination, double angle, boolean elev){
		
		Drawable dir = null;
		
		
		if (now.getPiso() < destination.getPiso())
		{
			//floor up
			int p = destination.getPiso() - now.getPiso();
			switch (p) {
			case 1:{
				if (!elev)
					dir = context.getResources().getDrawable(R.drawable.sube_1);
				else
					dir = context.getResources().getDrawable(R.drawable.sube_asc_1);
				break;
				}
			case 2:{
				if (!elev)
					dir = context.getResources().getDrawable(R.drawable.sube_2);
				else
					dir = context.getResources().getDrawable(R.drawable.sube_asc_2);
				break;
			}
			case 3:{
				if (!elev)
					dir = context.getResources().getDrawable(R.drawable.sube_3);
				else
					dir = context.getResources().getDrawable(R.drawable.sube_asc_3);
				break;
			}
			default:
				break;
			}
		}
		else if (now.getPiso() > destination.getPiso())
		{
			//floor down
			int p = now.getPiso() - destination.getPiso();
			switch (p) {
			case 1:{
				if (!elev)
					dir = context.getResources().getDrawable(R.drawable.baja_1);
				else
					dir = context.getResources().getDrawable(R.drawable.baja_asc_1);
				break;
				}
			case 2:{
				if (!elev)
					dir = context.getResources().getDrawable(R.drawable.baja_2);
				else
					dir = context.getResources().getDrawable(R.drawable.baja_asc_2);
				break;
			}
			case 3:{
				if (!elev)
					dir = context.getResources().getDrawable(R.drawable.baja_3);
				else
					dir = context.getResources().getDrawable(R.drawable.baja_asc_3);
				break;
			}
			default:
				break;
			}
		}
		else if (now.x<destination.x)
		{
			if ((angle>=345 &&360>=angle) || (0<=angle && angle<=45))
				//right
				dir = context.getResources().getDrawable(R.drawable.der);
			else if (angle>=135 && angle<=225)
				//left
				dir = context.getResources().getDrawable(R.drawable.izq);
			else if(225<angle && angle<345)
				//front
				dir = context.getResources().getDrawable(R.drawable.al);
			else 
				//back
				dir = context.getResources().getDrawable(R.drawable.atr);
		}
		else if (now.x>destination.x)
		{
			if ((angle>=345 &&360>=angle) || (0<=angle && angle<=45))
				//left
				dir = context.getResources().getDrawable(R.drawable.izq);
			else if (angle>=135 && angle<=225)
				//right
				dir = context.getResources().getDrawable(R.drawable.der);
			else if (45<angle && angle<135)
				//front
				dir = context.getResources().getDrawable(R.drawable.al);
			else 
				//back
				dir = context.getResources().getDrawable(R.drawable.atr);
		}
		else if (now.y<destination.y)
		{
			if(225<=angle && angle<345)
				//left
				dir = context.getResources().getDrawable(R.drawable.izq);
			else if(45<angle && angle<=135)
				//right
				dir = context.getResources().getDrawable(R.drawable.der);
			else if ((angle>=345 &&360>=angle) || (0<=angle && angle<=45))
				//front
				dir = context.getResources().getDrawable(R.drawable.al);
			else
				//back
				dir = context.getResources().getDrawable(R.drawable.atr);
		}
		else if (now.y>destination.y)
		{
			if(225<=angle && angle<=345)
				//right
				dir = context.getResources().getDrawable(R.drawable.der);
			else if(45<=angle && angle<=135)
				//left
				dir = context.getResources().getDrawable(R.drawable.izq);
			else if (135<angle && angle<225)
				//front
				dir = context.getResources().getDrawable(R.drawable.al);
			else
				//back
				dir = context.getResources().getDrawable(R.drawable.atr);
		}
		return dir;
	}
	
	public static Drawable calculateDirectionClass(Context context, String orient, double angle){
		
		Drawable dir = null;
		if (orient.equals("up"))
		{
			if(225<=angle && angle<=345)
				//left
				dir = context.getResources().getDrawable(R.drawable.lle_izq);
			else if(45<=angle && angle<=135)
				//right
				dir = context.getResources().getDrawable(R.drawable.lle_der);
			else if (135<angle && angle<225)
				//back
				dir = context.getResources().getDrawable(R.drawable.lle);
			else if ((angle>=345 &&360>=angle) || (0<=angle && angle<=45))
				//front
				dir = context.getResources().getDrawable(R.drawable.lle);
		}
		else if(orient.equals("down"))
		{
			if(225<=angle && angle<=345)
				//right
				dir = context.getResources().getDrawable(R.drawable.lle_der);
			else if(45<=angle && angle<=135)
				//left
				dir = context.getResources().getDrawable(R.drawable.lle_izq);
			else if (135<angle && angle<225)
				//front
				dir = context.getResources().getDrawable(R.drawable.lle);
			else if ((angle>=345 &&360>=angle) || (0<=angle && angle<=45))
				//back
				dir = context.getResources().getDrawable(R.drawable.lle);
		}
		else if(orient.equals("right"))
		{
			if(225<=angle && angle<=345)
				//front
				dir = context.getResources().getDrawable(R.drawable.lle);
			else if(45<=angle && angle<=135)
				//back
				dir = context.getResources().getDrawable(R.drawable.lle);
			else if (135<angle && angle<225)
				//left
				dir = context.getResources().getDrawable(R.drawable.lle_izq);
			else if ((angle>=345 &&360>=angle) || (0<=angle && angle<=45))
				//right
				dir = context.getResources().getDrawable(R.drawable.lle_der);
		}
		else if(orient.equals("left"))
		{
			if(225<=angle && angle<=345)
				//back
				dir = context.getResources().getDrawable(R.drawable.lle);
			else if(45<=angle && angle<=135)
				//front
				dir = context.getResources().getDrawable(R.drawable.lle);
			else if (135<angle && angle<225)
				//right
				dir = context.getResources().getDrawable(R.drawable.lle_der);
			else if ((angle>=345 &&360>=angle) || (0<=angle && angle<=45))
				//left
				dir = context.getResources().getDrawable(R.drawable.lle_izq);
		}
		return dir;	
	}
}
