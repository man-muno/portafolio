/**
 * Copyright (c) 2010-2015, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.rosie.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang.StringUtils;
import org.openhab.binding.rosie.RosieBindingProvider;
import org.openhab.binding.rosie.blackboard.Blackboard;
import org.openhab.binding.rosie.blackboard.Location;
import org.openhab.binding.rosie.blackboard.SensorType;
import org.openhab.binding.rosie.blackboard.experts.L1.AbstractDataExpert;
import org.openhab.binding.rosie.blackboard.experts.L1.ItemExpertFactory;
import org.openhab.core.binding.BindingConfig;
import org.openhab.core.items.Item;
import org.openhab.model.item.binding.AbstractGenericBindingProvider;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is responsible for parsing the binding configuration.
 * 
 * @author manuel.munoz
 * @since 1.8.0
 */
public class RosieGenericBindingProvider extends AbstractGenericBindingProvider implements RosieBindingProvider {

	private static final Logger logger = LoggerFactory.getLogger(RosieGenericBindingProvider.class);
	
	private Blackboard blackboard;
	
	public RosieGenericBindingProvider() {
		super();
		blackboard = Blackboard.getInstance();
		logger.debug("RosieGenericBindingProvider executed");
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getBindingType() {
		return "rosie";
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public void validateItemType(Item item, String bindingConfig) throws BindingConfigParseException {
		//if (!(item instanceof SwitchItem || item instanceof DimmerItem)) {
		//	throw new BindingConfigParseException("item '" + item.getName()
		//			+ "' is of type '" + item.getClass().getSimpleName()
		//			+ "', only Switch- and DimmerItems are allowed - please check your *.items configuration");
		//}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processBindingConfiguration(String context, Item item, String bindingConfig) throws BindingConfigParseException {
		super.processBindingConfiguration(context, item, bindingConfig);
		logger.debug("Starting Rosie processConfiguration");
		RosieBindingConfig config = new RosieBindingConfig();
		//parse bindingconfig here ...
		config.parse(bindingConfig);
		final String itemName = item.getName();
		logger.debug("Starting to load Rosie config for item {} with configuration {}", itemName, bindingConfig);
		if (StringUtils.isEmpty(bindingConfig))
			throw new BindingConfigParseException("Missing Rosie binding configuration for item " + itemName);
		
		try {
			ItemExpertFactory factory = new ItemExpertFactory();
			AbstractDataExpert kSource = (AbstractDataExpert) factory.getExpert(config.getItemType() , config.sensorType, blackboard);
			kSource.setSensorType(SensorType.parseString(config.sensorType));
			kSource.setLocation(Location.parseLocation(config.location));
			blackboard.addKnowledgeSource(itemName, kSource.getSensorType(), kSource);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addBindingConfig(item, config);
	}
	
	
	/**
	 * This is a helper class holding binding specific configuration details
	 * 
	 * @author manuel.munoz
	 * @since 1.8.0
	 */
	class RosieBindingConfig implements BindingConfig {
		// put member fields here which holds the parsed values
		
		private String itemType;
		
		private String sensorType;
		
		private String location;

		public String getItemType(){
			return itemType;
		}
		
		public String getSensorType(){
			return sensorType;
		}
		
		public String getLocation(){
			return location;
		}
		
		public void parse(String bindingConfig) {
			String[] split = bindingConfig.split(",");
			//Openhab Sensor type
			itemType = split[0];
			//What that type represents
			sensorType = split[1];
			//Location either indoor or outdoor
			location = split[2]; 
		}
	}
	
	
	public <T> T instantiate(final String className, final Class<T> type, String itemName){
	    try{
	    	Class<?> clazz = Class.forName(className);
	    	Constructor<?> constructor = clazz.getConstructor(Blackboard.class, String.class);
	    	Object obj = constructor.newInstance(blackboard);
	        return type.cast(obj);
	    } catch(final InstantiationException e){
	    	logger.error("Error instatiating " + e.getMessage());
	        throw new IllegalStateException(e);
	    } catch(final IllegalAccessException e){
	    	logger.error("Error instatiating " + e.getMessage());
	        throw new IllegalStateException(e);
	    } catch(final ClassNotFoundException e){
	    	logger.error("Error instatiating " + e.getMessage());
	        throw new IllegalStateException(e);
	    } catch (NoSuchMethodException e) {
	    	logger.error("Error instatiating " + e.getMessage());
	    	throw new IllegalStateException(e);
		} catch (SecurityException e) {
			logger.error("Error instatiating " + e.getMessage());
	    	throw new IllegalStateException(e);
		} catch (IllegalArgumentException e) {
			logger.error("Error instatiating " + e.getMessage());
	    	throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			logger.error("Error instatiating " + e.getMessage());
	    	throw new IllegalStateException(e);
		}
	}
	
}
