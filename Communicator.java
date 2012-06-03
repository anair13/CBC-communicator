package org.framework.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket server to let the robot print to a connected computer.
 */ 
public class Communicator {

	final int PORT = 4444;
	ServerSocket serverSocket = null;
	Socket clientSocket = null;
	PrintWriter out;
	BufferedReader in;
	
	public Communicator() {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + PORT);
		}
		
		Socket clientSocket = null;
		try {
			System.out.println("Waiting for socket server");
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.out.println("Client accepting failed.");
		}
		
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			System.out.println("Streams failed to instantiate.");
		}
		
		send("Communication initiated");
		System.out.println("Communicator loaded");
		
	}
	
	public void send(String s) {
		out.println(s);
	}
	
	public void close() {
		try {
			out.close();
			in.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Sockets failed to close.");
		}
	}
}