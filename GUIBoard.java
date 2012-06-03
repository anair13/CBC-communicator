package org.winchester.demo.fundraiser;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.winchester.demo.fundraiser.ObjectType.*;

public class GUIBoard extends JFrame {

	List<GameObject> shapes = new ArrayList<GameObject>();

	/**
	 * No warning!
	 */
	private static final long serialVersionUID = 5566912268748174978L;
	public GuiRobot robot;
	int side = 600;
	private final int robotRadius = 10;
	public Rectangle2D background;

	private Image offscreen;
	private Graphics2D buffer;
	
	public static final int DEFAULT_SIDE = 300; // area from (-75,-75) to (75,75)
	public static int horizontalShift = DEFAULT_SIDE / 2;
	public static int verticalShift = DEFAULT_SIDE / 2;
	private double scale = 1;

	public GUIBoard() {
		super();
		setSize(new Dimension(side, side));
		robot = new GuiRobot(0, 0);
		background = new Rectangle2D.Double(0, 0, this.getWidth(),
				this.getHeight());
		
		this.getRootPane().addComponentListener(new ComponentAdapter() {
	        public void componentResized(ComponentEvent e) {
	        	setScale();
	        }
	    });
	}
	
	public void setScale() {
		if (this.getWidth() < this.getHeight()) {
			side = this.getWidth();
		} else {
			side = this.getHeight();
		}
		scale = (double)side / DEFAULT_SIDE;
	}

	public void paint(Graphics g) {
		offscreen = createImage(side,side);
		buffer = (Graphics2D) offscreen.getGraphics();
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (GameObject o : shapes) {
			o.render(buffer);
		}

		robot.render(buffer);
		
		System.out.println(buffer);
		
		g2.drawImage(offscreen, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}
	
	public void cleanupObjects(double x, double y){
		double difference = robot.getRadius();
		double distance = difference+1;
		for(int i = 0; i<shapes.size();){
			distance = shapes.get(i).getLocation().distance(new Point2D.Double(x, y));
			if(distance<difference){
				shapes.remove(i);
			} else {
				i++;
			}
		}
	}
	
	public synchronized void resetObjects(){
		shapes.clear();
		repaint();
	}
	
	public synchronized void updateRobot(Point2D p, double direction) {
		robot = new GuiRobot(
				scale * (p.getX() + horizontalShift), 
				side - scale * (p.getY() + verticalShift),
				scale * robotRadius, direction);
		//cleanupObjects(p.getX(), p.getY());
		repaint();
	}

	public synchronized void updateRobot(Point2D p) {
		updateRobot(p, robot.getDirection());
	}
	
	public synchronized void drawObject(GameObject g) {
		g.setLocation((int) (scale * (g.getLocation().getX() + horizontalShift)),
				(int) (side - scale *(g.getLocation().getY() + verticalShift)));
		//cleanupObjects(g.getLocation().getX(), g.getLocation().getY());
		shapes.add(g);
		repaint();
	}
	
	public static void main(String[] args) {
		GUIBoard board = new GUIBoard();
		board.setVisible(true);
		board.repaint();
		int x = -50; int y = -50; double d = 0;
		board.updateRobot(new Point2D.Double(x, y), d);
		while (true) {
			x++;
			if (x > 50) {
				x = -50;
			}
			y++;
			if (y > 50) {
				y = -50;
			}
			d+=Math.PI/50;
			board.updateRobot(new Point2D.Double(x, y), d);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}