package fml.tum.msh;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fml.tum.listeners.DSAListener;
import fml.tum.listeners.MetaioListener;
import fml.tum.model.Instruction;
import fml.tum.model.Warehouse;
import fml.tum.utils.PropertiesValues;
import fml.tum.view.ModelObserver;

public class Steuerhebel {

	
	public static final String CONFIG_FILE = "./config.properties";
	
	/**
	 * Protocol for MQTT
	 */
	private static final String MQTT_PROTOCOL = "tcp://";

	/**
	 * Creates the MQTT client to be used in communication
	 */
	private MqttClient client;

	
	public static void main(String[] args) {
		new Steuerhebel().start();
	}

	/**
	 * Creates the model, starts the listeners and the views
	 */
	public void start() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File(CONFIG_FILE)));
			Warehouse warehouse = new Warehouse(properties);
			warehouse.setInstructions(loadInstructions());
			startObservers(warehouse, properties);
			startMetaioListener(warehouse, properties);
			startDSAListener(warehouse, properties);
		} catch (MqttException | IOException e) {
			try {
				client.disconnect();
			} catch (MqttException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Loads the instructions from the file "./instructions.json"
	 * @return
	 */
	public static Instruction[] loadInstructions() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = new Gson();
		gson = gsonBuilder.create();
		try {
			String content = new String(Files.readAllBytes(Paths.get("./instructions.json")));
			return gson.fromJson(content, Instruction[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Starts and adds the observers of the model
	 * @param warehouse
	 * @param properties 
	 */
	private void startObservers(Warehouse warehouse, Properties properties) {
		ModelObserver view = new ModelObserver(warehouse, properties);
		warehouse.addObserver(view);
	}

	/**
	 * Starts the listener for the DSA system
	 * @param warehouse Model
	 * @param properties 
	 * @throws SocketException Exception thrown if there is a connection problem
	 * @throws IOException Exception thrown if there is a thread problem
	 */
	private void startDSAListener(Warehouse warehouse, Properties properties) throws SocketException,
			IOException {
		new Thread(new DSAListener(properties.getProperty(PropertiesValues.DSA_HOST), Integer.parseInt(properties.getProperty(PropertiesValues.DSA_PORT)), warehouse)).start();
	}

	/**
	 * Starts the listener for Metaio. Listens for the broker using the MQTT_PROTOCOL, MQTT_ADDRESS, MQTT_PORT.
	 * It subscribes to the topics defined in MetaioListener REGAL_TOPIC_NAME, PALETTE_TOPIC_NAME, AUFTRAG_TOPIC_NAME, SYMBOL_TOPIC_NAME
	 * @param warehouse Model Not null
	 * @param properties 
	 * @throws MqttException If there was a problem creating the client or subscribing to the topics.
	 */
	private void startMetaioListener(Warehouse warehouse, Properties properties) throws MqttException {
		String url = MQTT_PROTOCOL + properties.getProperty(PropertiesValues.MQTT_HOST) + ":" + properties.getProperty(PropertiesValues.MQTT_PORT);
		client = new MqttClient(url, MqttClient.generateClientId());
		client.connect();
		MetaioListener metaioListener = new MetaioListener(warehouse);

		client.subscribe(MetaioListener.REGAL_TOPIC_NAME);
		client.subscribe(MetaioListener.PALETTE_TOPIC_NAME);
		client.subscribe(MetaioListener.AUFTRAG_TOPIC_NAME);
		client.subscribe(MetaioListener.SYMBOL_TOPIC_NAME);

		client.setCallback(metaioListener);
	}

}
