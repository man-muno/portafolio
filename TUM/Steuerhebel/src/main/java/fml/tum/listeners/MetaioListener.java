package fml.tum.listeners;

import java.nio.ByteOrder;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import fml.tum.model.Warehouse;
import fml.tum.utils.Utils;

public class MetaioListener implements MqttCallback{

	public static final String AUFTRAG_TOPIC_NAME = "StaplAR/Auftrag";
	public static final String REGAL_TOPIC_NAME = "StaplAR/Regal";
	public static final String PALETTE_TOPIC_NAME = "StaplAR/Palette";
	public static final String SYMBOL_TOPIC_NAME = "StaplAR/Symbol";
	
	private Warehouse warehouse;
	
	public MetaioListener(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@Override
	public void connectionLost(Throwable cause) {
		cause.printStackTrace();
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		if(topic.equals(REGAL_TOPIC_NAME))
			readRegalMessage(message);
		else if(topic.equals(PALETTE_TOPIC_NAME))
			readPalettMessage(message);
		else if(topic.equals(SYMBOL_TOPIC_NAME))
			readSymbolMessage(message);
	}

	private void readSymbolMessage(MqttMessage message) {
		byte[] payload = message.getPayload();
		//Here is where the definition of the protocol is read
		int symbolPos = 0;
		int symbol= Utils.getInt(payload, symbolPos, 4, ByteOrder.LITTLE_ENDIAN);
		
		warehouse.setCommand(symbol);
	}

	private void readPalettMessage(MqttMessage message) {
		byte[] payload = message.getPayload();
		//Here is where the definition of the protocol is read
		int posTransX = 0;
		int posTransY = 4;
		int posTransZ = 8;
		int posRotX = 12;		
		int posRotY = 16;
		
		
		int transX= Utils.getInt(payload, posTransX, 4, ByteOrder.LITTLE_ENDIAN);
		int transY = Utils.getInt(payload, posTransY, 4, ByteOrder.LITTLE_ENDIAN);
		int transZ = Utils.getInt(payload, posTransZ, 4, ByteOrder.LITTLE_ENDIAN);
		float rotX = Utils.getFloat(payload, posRotX, 4, ByteOrder.LITTLE_ENDIAN);
		float rotY = Utils.getFloat(payload, posRotY, 4, ByteOrder.LITTLE_ENDIAN);
		
		warehouse.setCurrentPalletCoordinates(transX,transY,transZ,rotX,rotY);
		
	}

	private void readRegalMessage(MqttMessage message) {
		byte[] payload = message.getPayload();
		//Here is where the definition of the protocol is read
		int posTransX = 0;
		int posTransY = 4;
		int posTransZ = 8;
		//int posRotX = 12;		
		//int posRotY = 16;
		int posRegalNummer = 20;

		int transX= Utils.getInt(payload, posTransX, 4, ByteOrder.LITTLE_ENDIAN);
		int transY = Utils.getInt(payload, posTransY, 4, ByteOrder.LITTLE_ENDIAN);
		int transZ = Utils.getInt(payload, posTransZ, 4, ByteOrder.LITTLE_ENDIAN);
		//float rotX = getFloat(payload, posRotX, 4, ByteOrder.LITTLE_ENDIAN);
		//float rotY = getFloat(payload, posRotY, 4, ByteOrder.LITTLE_ENDIAN);
		//The rotation information comes from another system
		int regalNummer = Utils.getInt(payload, posRegalNummer, 4, ByteOrder.LITTLE_ENDIAN);
		warehouse.setCurrentShelfNumber(regalNummer);
		warehouse.setForkliftCoordinates(transX,transY,transZ);
		warehouse.setForkliftMastHeight(transY);
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		try {
			System.out.println(token.getMessage());
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
