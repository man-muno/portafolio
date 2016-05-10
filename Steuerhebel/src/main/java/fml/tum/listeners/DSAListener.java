package fml.tum.listeners;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.nio.ByteOrder;

import fml.tum.model.Warehouse;
import fml.tum.utils.Utils;

public class DSAListener implements Runnable{
	
	/**
	 * Port where the listener expects the connections
	 */
	private int port;
	
	/**
	 * Model
	 */
	private Warehouse warehouse;

	private String host;
	
	/**
	 * Starts the attributes
	 * @param host 
	 * @param port Port to listen to the service. The service must be running there. 
	 * @param warehouse Not null
	 */
	public DSAListener(String host, int port, Warehouse warehouse)
	{
		this.warehouse = warehouse;
		this.port = port;
		this.host = host;
	}

	/**
	 * Functions as a server listening always listening for packets on the specified server
	 * @throws SocketException If there are problems with the connection
	 * @throws IOException If there are problems with  the
	 */
	private void listen(){
		DatagramSocket serverSocket = null;
		try {
			serverSocket = new DatagramSocket(port, InetAddress.getLocalHost());
			byte[] receiveData = new byte[30];             
			//packet type			0
			//vehicle id 			1
			//timestamp				2
			//translation vector	6
			//rotation vector		18
			//fork height			24
			//mastilt angle			26
			//forkset1 occupied		28
			//forkset2 occupied		29
			
			while(true)
			{
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
			    byte[] message =  receivePacket.getData();
	
			   /** try {
					int height = Utils.getShort(message, 24,2,ByteOrder.BIG_ENDIAN) + 32768;
					warehouse.setForkliftMastHeight(height);
				} catch (Exception e) {
					e.printStackTrace();
				}**/
			    try {
					float uFloat= Utils.getShort(message, 26,2,ByteOrder.BIG_ENDIAN); 
					warehouse.setForkliftMastAngle(uFloat);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(serverSocket!=null)
				serverSocket.close();
		}
		
		
	}

	@Override
	public void run() 
	{
		listen();
	}
	
	
	

}
