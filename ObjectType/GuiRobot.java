package org.winchester.demo.fundraiser.ObjectType;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

public class GuiRobot extends Ellipse2D.Double {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final double DEFAULT_RADIUS = 10;
	public static final double DEFAULT_DIRECTION = 0;
	public static final double FRONT_ANGLE = 180;
	private Arc2D.Double front = null;
	private double r;
	private double direction;
	
	/**
	 * 
	 * @param x-coordinate
	 * @param y-coordinate 
	 * @param radius
	 * @param direction in radians
	 */
	public GuiRobot(double x, double y, double r, double direction) {
		super(x - r, y - r, 2 * r, 2 * r);
		this.r = r;
		this.direction = direction;
		double d = (direction*180/Math.PI)-FRONT_ANGLE/2;
		front = new Arc2D.Double(this.getBounds2D(), d%360, FRONT_ANGLE, Arc2D.PIE);
	}
	
	public GuiRobot(double x, double y, double r) {
		this(x, y, r, DEFAULT_DIRECTION);
	}
	
	public GuiRobot(double x, double y) {
		this(x, y, DEFAULT_RADIUS);
		this.r = DEFAULT_RADIUS;
	}
	
	public Point getLocation() {
		return new Point((int)(getX()+r), (int)(getY()+r));
	}
	
	public double getRadius(){
		return r;
	}
	
	public double getDirection(){
		return direction;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fill(this);
		g.setColor(Color.GREEN);
		g.fill(front);
	}

}
