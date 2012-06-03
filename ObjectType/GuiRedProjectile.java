package org.winchester.demo.fundraiser.ObjectType;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class GuiRedProjectile extends Ellipse2D.Double implements GameObject{
	

	private static final long serialVersionUID = 1L;
	public static final double DEFAULT_RADIUS = 12;
	public double radius;
	public Color color;
	
	public GuiRedProjectile(double x, double y){
		super(x - DEFAULT_RADIUS, y - DEFAULT_RADIUS, DEFAULT_RADIUS*2, DEFAULT_RADIUS*2);
		radius = DEFAULT_RADIUS;
		this.color = Color.RED;
	}
	
	public GuiRedProjectile(double x, double y, Color color){
		super(x - DEFAULT_RADIUS, y - DEFAULT_RADIUS, DEFAULT_RADIUS*2, DEFAULT_RADIUS*2);
		radius = DEFAULT_RADIUS;
		this.color = color;
	}
	
	public GuiRedProjectile(double x, double y, double radius){
		super(x - radius, y - radius, radius * 2, radius * 2);
		this.radius = radius;
		this.color = Color.RED;
	}
	
	public GuiRedProjectile(double x, double y, double radius, Color color){
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
		return new Point((int)(getX() + radius), (int)(getY() + radius));
	}

	public double getSize() {
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
		return new GuiRedProjectile(getLocation().getX(), getLocation().getY(), radius, color);
	}
}
