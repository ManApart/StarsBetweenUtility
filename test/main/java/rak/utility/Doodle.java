package rak.utility;

import java.awt.Color;
import java.util.ArrayList;

public class Doodle {

	public static void main(String[] args){
//		logColors();
		
	}

	public static void logColors() {
		ArrayList<Color> colors = new ArrayList<>();
		colors.add(new Color(0, 50, 150,1));
		colors.add(new Color(0,100,200,1));
		colors.add(new Color(20,100,50,1));
		colors.add(new Color(20,100,0,1));
		colors.add(new Color(50,150,0,1));
		colors.add(new Color(50,200,0,1));
		
		ColorConverter converter = new ColorConverter();
		
		
		for (Color color : colors){
			converter.logColorValues(color);
		}
	}
}
