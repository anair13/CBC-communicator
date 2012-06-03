package org.winchester.demo.fundraiser.ObjectType;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class GuiLuggageContainer extends Ellipse2D.Double implements GameObject{

	private static final long serialVersionUID = 1L;
	public static final double DEFAULT_RADIUS = 15;
	public static final Color DEFAULT_COLOR = Color.PINK;
	public double radius;
	public Color color;
	
	public GuiLuggageContainer(double x, double y){
		this(x, y, DEFAULT_RADIUS);
	}
	
	public GuiLuggageContainer(double x, double y, Color color){
		this(x, y, DEFAULT_RADIUS, color);
	}
	
	public GuiLuggageContainer(double x, double y, double radius){
		this(x, y, radius, DEFAULT_COLOR);
	}
	
	public GuiLuggageContainer(double x, double y, double radius, Color color){
		super(x - radius, y - radius, radius * 2, radius * 2);
		this.radius = radius;
		this.color = color;
	}
	
	public void setLocation(int x, int y) {
		setFrame(x - radius, y - radius, radius * 2, radius * 2);
	}
	
	public void setSize(double x) {
		radius = x;
		setFrame(getX(), getY(), radius * 2, radius * 2);
	}


	public Point getLocation() {
		return new Point((int)(getX()+radius), (int)(getY()+radius));
	}
	
	public double getSize(){
		return radius;
	}
	
	public String toString(){
		return "x = " + getX() + " y = "+ getY() + " radius = " + radius;
	}
	
	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fill(this);
	}
	
	public GameObject copy() {
		return new GuiLuggageContainer(getLocation().getX(), getLocation().getY(), radius, color);
	}
}
