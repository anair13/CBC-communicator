package org.winchester.demo.fundraiser.ObjectType;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class GuiBlock extends Rectangle2D.Double implements GameObject{
	
	private static final long serialVersionUID = 1L;
	public static final double DEFAULT_SIDELENGTH = 20; 
	public static final Color DEFAULT_COLOR = Color.RED;
	public double sideLength;
	public Color color;
	
	public GuiBlock(double x, double y){
		this(x, y, DEFAULT_SIDELENGTH);
	}
	
	public GuiBlock(double x, double y, Color color){
		this(x, y, DEFAULT_SIDELENGTH, color);
	}
	
	public GuiBlock(double x, double y, double sideLength){
		this(x, y, sideLength, DEFAULT_COLOR);
	}
	
	public GuiBlock(double x, double y, double sideLength, Color color){
		super(x - sideLength/2, y - sideLength/2, sideLength, sideLength);
		this.sideLength = sideLength;
		this.color = color;
	}
	
	public void setLocation(int x, int y) {
		setRect(x - sideLength/2, y - sideLength/2, sideLength, sideLength);
	}
	
	public void setSize(double x) {
		x = sideLength;
		setRect(getX(),getY(), sideLength, sideLength);	
	}


	public Point getLocation() {
		return new Point((int)(getX()+sideLength), (int)(getY()+sideLength));
	}
	
	public double getSize() {
		return sideLength;
	}
	
	public String toString(){
		return "x = " + getX() + " y = "+ getY() + " sideLength = " + sideLength;
	}

	public void render(Graphics2D g) {
		g.setColor(color);
		g.fill(this);
	}

	public GameObject copy() {
		return new GuiBlock(getLocation().getX(),getLocation().getY(), sideLength, color);
	}
}
