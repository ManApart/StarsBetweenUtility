package rak.utility;

import javafx.scene.paint.Color;

public class ColorConverter {
	
	public void logColorValues(java.awt.Color jwtColor){
		System.out.println("JWT: R:" + jwtColor.getRed() + ", G:" + jwtColor.getGreen() + ", B:" + jwtColor.getBlue());
		Color jfxColor = convertJWTToJFX(jwtColor);
		System.out.println("JFX: R:" + jfxColor.getRed() + ", G:" + jfxColor.getGreen() + ", B:" + jfxColor.getBlue());
		System.out.println("(" + jfxColor.getRed() + "," + jfxColor.getGreen() + "," + jfxColor.getBlue() + ",1)\n");
		
	}
	
	private Color convertJWTToJFX(java.awt.Color jwtColor){
		double r = jwtScaleToJFXScale(jwtColor.getRed());
		double g = jwtScaleToJFXScale(jwtColor.getGreen());
		double b = jwtScaleToJFXScale(jwtColor.getBlue());
		
		Color jfxColor = new Color(r,g,b,1);
		return jfxColor;
	}
	
	private double jwtScaleToJFXScale(int amount){
		return amount / 255d;
	}
	
	public String convertJFXToHex(Color color){
        return String.format( "#%02X%02X%02X",
            (int)(color.getRed() * 255),
            (int)(color.getGreen() * 255),
            (int)(color.getBlue() * 255));
    }
	

}
