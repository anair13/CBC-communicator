package org.winchester.demo.fundraiser.ObjectType;

import java.awt.*;


/**
 * Contains all information about an object located on the Map.
 * 
 * @author ashvin
 *
 */
public interface GameObject {

	public void setLocation(int x, int y);
	
	public void setSize(double x);
	
	public GameObject copy();

	public Point getLocation();
	
	public double getSize();
	
	public String toString();
	
	public void render(Graphics2D g);
}
