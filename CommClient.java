package org.winchester.demo.fundraiser;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.winchester.demo.fundraiser.ObjectType.GuiBlock;
import org.winchester.demo.fundraiser.ObjectType.GuiPingPong;

public class CommClient {
	
	final String HOST; 
	final int PORT = 4444;
	Socket echoSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	
	public CommClient(String ipIdentifier) {
		HOST = "192.168.1." + ipIdentifier;
		
		try {
			echoSocket = new Socket(HOST, PORT);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + HOST);
			System.exit(0);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: " + HOST);
			System.exit(0);
		}
	}
	
	public String getNextLine() {
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "No input";
	}
	
	public void send(String s) {
		out.println(s);
	}
	
	public void close() {
		out.close();
		try {
			in.close();
			echoSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {

		CommClient client;
		
		if (args.length > 0) {
			client = new CommClient(args[0]);
		}
		else {
			client = new CommClient("13");
		}
		String robotInput;
		
		GUIBoard board = new GUIBoard();
		board.setVisible(true);
		board.repaint();
		
		while ((robotInput = client.getNextLine()) != null) {
			
			System.out.println("received: " + robotInput);
			
			if (robotInput.startsWith("Robot moved to ")) {
				double x = 2 * Double.parseDouble(robotInput.substring(robotInput.indexOf("(") + 1, robotInput.indexOf(",")));
				double y = 2 * Double.parseDouble(robotInput.substring(robotInput.indexOf(",") + 1, robotInput.indexOf(")")));
				double direction = Double.parseDouble(robotInput.substring(robotInput.indexOf("[") + 1, robotInput.indexOf("]")));
				board.updateRobot(new Point2D.Double(x,y), direction);
				System.out.println("robot position updated");
			}
			
			if(robotInput.startsWith("Hit wall at ")){
				double x = 2 * Double.parseDouble(robotInput.substring(robotInput.indexOf("(") + 1, robotInput.indexOf(",")));
				double y = 2 * Double.parseDouble(robotInput.substring(robotInput.indexOf(",") + 1, robotInput.indexOf(")")));
				board.drawObject(new GuiBlock(x, y));
				System.out.println("added block");
			}
			
			if (robotInput.startsWith("Green pompom at ")) {
				double x = 2 * Double.parseDouble(robotInput.substring(robotInput.indexOf("(") + 1, robotInput.indexOf(",")));
				double y = 2 * Double.parseDouble(robotInput.substring(robotInput.indexOf(",") + 1, robotInput.indexOf(")")));
				//rDemo.updateRobot(new Point2D.Double(x,y));
				board.drawObject(new GuiPingPong(x,y, Color.GREEN));
				System.out.println("added green pompom");
			}
			if (robotInput.startsWith("Pink pompom at ")) {
				double x = 2 * Double.parseDouble(robotInput.substring(robotInput.indexOf("(") + 1, robotInput.indexOf(",")));
				double y = 2 * Double.parseDouble(robotInput.substring(robotInput.indexOf(",") + 1, robotInput.indexOf(")")));
				//rDemo.updateRobot(new Point2D.Double(x,y));
				board.drawObject(new GuiPingPong(x,y, Color.PINK));
				System.out.println("added pink pompom");
			}
			if (robotInput.startsWith("Got pompom")){
				board.resetObjects();
			}
			
		}
		
		System.out.println("Robot probably disconnected");
		
		System.exit(0);
		
	}
}