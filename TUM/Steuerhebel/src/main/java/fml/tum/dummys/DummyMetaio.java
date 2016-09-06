package fml.tum.dummys;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class DummyMetaio {
	
	//StaplAR/Auftrag
	//StaplAR/Regal
	//StaplAR/Palette
	//StaplAR/Command

	MqttClient client;

	public static void main(String[] args) throws Exception {

		new DummyMetaio().start();
	}

	public void start() throws InterruptedException 
	{
		while (true) {
			sendRegal();
			//sendPalette();
			//sendAuftrag();
			//sendSymbol();
			// Pause for 4 seconds
			Thread.sleep(500);
		}
	}

	public void sendRegal() {
		try {
			
			ByteBuffer b = ByteBuffer.allocate(32);
			b.order(ByteOrder.LITTLE_ENDIAN);
			
			int regalAbsolut = 1;//getRandomInt(1, 5);
			b.putInt(0, regalAbsolut);
			int xTranslation = getRandomInt(1, 200);
			b.putInt(4, xTranslation);
			int yTranslation = getRandomInt(1, 200);
			b.putInt(8, yTranslation);
			int zTranslation = getRandomInt(1, 200);
			b.putInt(12, zTranslation);
			int xRotation= getRandomInt(1, 200);
			b.putInt(16, xRotation);
			int yRotation = getRandomInt(1, 200);
			b.putInt(20, yRotation);
			int zRotation= getRandomInt(1, 200);
			b.putInt(24, zRotation);
			int regalNumber = getRandomInt(1, 5);
			b.putInt(28, regalNumber);
			byte[] result = b.array();
			client = new MqttClient("tcp://localhost:1883", "TrackAR-Regal");
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload(result);
			client.publish("StaplAR/Regal", message);
			//client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				client.disconnect();
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendPalette() {
		try {
			
			ByteBuffer b = ByteBuffer.allocate(28);
			b.order(ByteOrder.LITTLE_ENDIAN);
			
			int regalAbsolut = 2;//getRandomInt(1, 5);
			b.putInt(0, regalAbsolut);
			int xTranslation = getRandomInt(1, 200);
			b.putInt(4, xTranslation);
			int yTranslation = getRandomInt(1, 200);
			b.putInt(8, yTranslation);
			int zTranslation = getRandomInt(1, 200);
			b.putInt(12, zTranslation);
			float xRotation= getRandomFloat(1, 200);
			b.putFloat(16, xRotation);
			float yRotation = getRandomFloat(1, 200);
			b.putFloat(20, yRotation);
			int regalNummer= getRandomInt(1, 5);
			b.putInt(24, regalNummer);
			
			byte[] result = b.array();
			client = new MqttClient("tcp://localhost:1883", "TrackAR-Palette");
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload(result);
			client.publish("StaplAR/Palette", message);
			//client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				client.disconnect();
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void sendSymbol() {
		try {
			
			ByteBuffer b = ByteBuffer.allocate(8);
			b.order(ByteOrder.LITTLE_ENDIAN);
			
			int symbol = 4;//;getRandomInt(1, 5);
			b.putInt(0, symbol);
			int symbolId = getRandomInt(1, 200);
			b.putInt(4, symbolId);
			
			
			byte[] result = b.array();
			client = new MqttClient("tcp://localhost:1883", "TrackAR-Symbol");
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload(result);
			client.publish("StaplAR/Symbol", message);
			//client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				client.disconnect();
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendAuftrag() {
		try {
			
			ByteBuffer b = ByteBuffer.allocate(28);
			b.order(ByteOrder.LITTLE_ENDIAN);
			
			int regalAbsolut = 3;//getRandomInt(1, 5);
			b.putInt(0, regalAbsolut);
			int xTranslation = getRandomInt(1, 200);
			b.putInt(4, xTranslation);
			int yTranslation = getRandomInt(1, 200);
			b.putInt(8, yTranslation);
			int zTranslation = getRandomInt(1, 200);
			b.putInt(12, zTranslation);	
			int xRotation= getRandomInt(1, 200);
			b.putInt(16, xRotation);
			int yRotation = getRandomInt(1, 200);
			b.putInt(20, yRotation);
			int zRotation= getRandomInt(1, 200);
			b.putInt(24, zRotation);
			
			byte[] result = b.array();
			client = new MqttClient("tcp://localhost:1883", "TrackAR-Regal");
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload(result);
			client.publish("StaplAR/Regal", message);
			//client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				client.disconnect();
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int getRandomInt(int min, int max) {
		Random rand = new Random();
		int random = rand.nextInt(max) + min;
		return random;
	}

	public float getRandomFloat(int min, int max) {
		Random rand = new Random();
		float random = max*rand.nextFloat() + min;
		return random;
	}
	
}
