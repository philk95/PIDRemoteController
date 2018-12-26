package de.kohl.philipp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.util.Scanner;

import lejos.hardware.BrickFinder;
import lejos.hardware.BrickInfo;

public class PCRemote {

	private final static int PORT = 1234;

	private Socket connection = null;

	public void connectToFirstBrick() throws UnknownHostException, IOException {
		BrickInfo[] bricks = BrickFinder.discover();

		if (bricks.length == 0) {
			throw new IllegalArgumentException("No brick found!");
		}

		this.connection = new Socket(bricks[0].getIPAddress(), PORT);
		System.out.println("Connected: " + this.connection);
		this.connection.setTcpNoDelay(true);
	}

	public void closeConnection() throws IOException {
		this.connection.close();
	}

	public OutputStream getOutputStream() throws IOException {
		if (this.connection == null) {
			throw new NullPointerException("Establish connection first!");
		}
		return this.connection.getOutputStream();
	}

	public void sendCommand(String command) throws IOException {
		OutputStream outputStream = getOutputStream();

		outputStream.write(command.getBytes());
		outputStream.flush();
	}

	private static void sendCommands(PCRemote remote) throws IOException {
		OutputStream outputStream = remote.getOutputStream();

		String command = null;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Command: ");
			command = scanner.nextLine();
			if ("exit".equals(command)) {
				break;
			}
			outputStream.write(command.getBytes());
			outputStream.flush();
		}

		scanner.close();
		outputStream.close();
	}

	public static void main(String[] args) throws NotBoundException, UnknownHostException, IOException {

		PCRemote remote = new PCRemote();
		remote.connectToFirstBrick();

		sendCommands(remote);

		remote.closeConnection();
	}
}
