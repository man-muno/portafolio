package fml.tum.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class DatagramSender implements Runnable {

	/**
	 * Address of the endpoint
	 */
	private String address;
	
	/**
	 * port of the endpoint
	 */
	private int port;
	
	/**
	 * Payload
	 */
	private ByteBuffer byteBuffer;

	/**
	 * Creates the object with the given parameters
	 * @param address Address of the endpoint
	 * @param port Listening port
	 * @param byteBuffer Payload
	 */
	public DatagramSender(String address, int port, ByteBuffer byteBuffer) {
		this.address = address;
		this.port = port;
		this.byteBuffer = byteBuffer;
	}

	/**
	 * Sends the payload message to the an end point on the given address and port.
	 */
	public void send() {
		DatagramSocket clientSocket = null;
		try {
			clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName(address);
			byte[] sendData = byteBuffer.array();

			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, port);
			clientSocket.send(sendPacket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			clientSocket.close();
		}
	}

	@Override
	public void run() {
		send();
	}

}
