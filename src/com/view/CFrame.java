package com.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CFrame {
	
	// Colors
	public static Color clrTransparent = new Color(1.0f, 1.0f, 1.0f, 0.0f);
	public static Color clrDefault = new Color(50, 50, 50, 255);

	// Font
	public static Font fntDefault10 = new Font("Arial", Font.PLAIN, 10);
	public static Font fntDefault11 = new Font("Arial", Font.PLAIN, 11);
	public static Font fntDefault12 = new Font("Arial", Font.PLAIN, 12);
	public static Font fntDefault13 = new Font("Arial", Font.PLAIN, 13);

	public static Font fntDefault = fntDefault13;
	
	// Border
	public static Color clrBorderDefault = new Color(81, 119, 144, 255);
	public static Border brdrLine = new LineBorder(clrBorderDefault);
	

	// Scrollbar
	public static final int SCROLLBAR_HEIGHT_H = 10;
}
