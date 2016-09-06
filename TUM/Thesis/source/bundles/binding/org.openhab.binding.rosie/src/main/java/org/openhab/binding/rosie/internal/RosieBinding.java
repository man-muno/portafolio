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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openhab.binding.rosie.RosieBindingProvider;
import org.openhab.binding.rosie.blackboard.AbstractControl;
import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource;
import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource.Level;
import org.openhab.binding.rosie.blackboard.BasicControl;
import org.openhab.binding.rosie.blackboard.Blackboard;
import org.openhab.binding.rosie.blackboard.experts.L3.AbstractMLExpert;
import org.openhab.core.binding.AbstractActiveBinding;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implement this class if you are going create an actively polling service like
 * querying a Website/Device.
 * 
 * @author manuel.munoz
 * @since 1.8.0
 */
public class RosieBinding extends AbstractActiveBinding<RosieBindingProvider>
		implements ManagedService {

	private static final Logger logger = LoggerFactory
			.getLogger(RosieBinding.class);

	private static final String TRAINING_TIME = "trainingTime";
	
	private long trainingTime = AbstractMLExpert.TRAINING_TIME;
	
	private static final String EXPERT_PREFIX = "expert";

	/**
	 * The BundleContext. This is only valid when the bundle is ACTIVE. It is
	 * set in the activate() method and must not be accessed anymore once the
	 * deactivate() method was called or before activate() was called.
	 */
	private BundleContext bundleContext;

	/**
	 * Instance of the blackboard
	 */
	private Blackboard blackboard;

	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * the refresh interval which is used to poll values from the Rosie server
	 * (optional, defaults to 60000ms)
	 */
	private long refreshInterval = 5000;

	/**
	 * Controller of the experts from the blackboard
	 */
	private AbstractControl controller;

	public RosieBinding() {
		blackboard = Blackboard.getInstance();
		controller = new BasicControl(blackboard, eventPublisher);
		logger.info("RosieBinding bundle activated");
	}

	/**
	 * Called by the SCR to activate the component with its configuration read
	 * from CAS
	 * 
	 * @param bundleContext
	 *            BundleContext of the Bundle that defines this component
	 * @param configuration
	 *            Configuration properties for this component obtained from the
	 *            ConfigAdmin service
	 */
	public void activate(final BundleContext bundleContext,
			final Map<String, Object> configuration) {
		this.bundleContext = bundleContext;
		// the configuration is guaranteed not to be null, because the component
		// definition has the
		// configuration-policy set to require. If set to 'optional' then the
		// configuration may be null

		// to override the default refresh interval one has to add a
		// parameter to openhab.cfg like <bindingName>:refresh=<intervalInMs>
		String refreshIntervalString = (String) configuration.get("refresh");
		if (StringUtils.isNotBlank(refreshIntervalString)) {
			refreshInterval = Long.parseLong(refreshIntervalString);
		}

		// read further config parameters here ...
		// Read the experts names, instantiate them and add them to the blackboard
		//blackboard.setEventPlubisher(eventPublisher);
		controller.setEventPlubisher(eventPublisher);
		//blackboard.addKnowledgeSource(RulesOfEngagementExpert.class.getSimpleName(), new RulesOfEngagementExpert(blackboard, "RulesOfEngagementExpert"));
		//blackboard.addKnowledgeSource(KnownPresenceExpert.ITEM_NAME, new KnownPresenceExpert(blackboard, KnownPresenceExpert.ITEM_NAME));
		//blackboard.addKnowledgeSource(MotionExpertIndoors.ITEM_NAME, new MotionExpertIndoors(blackboard, MotionExpertIndoors.ITEM_NAME));
		//blackboard.addKnowledgeSource(MotionExpertOutdoors.ITEM_NAME, new MotionExpertOutdoors(blackboard, MotionExpertOutdoors.ITEM_NAME));
		//blackboard.addKnowledgeSource(EntryPointExpert.ITEM_NAME, new EntryPointExpert(blackboard, EntryPointExpert.ITEM_NAME));
		//blackboard.addKnowledgeSource(KNearestNeighborExpert.ITEM_NAME, new KNearestNeighborExpert(blackboard, EntryPointExpert.ITEM_NAME));

		
		
		Iterator<String> iterator = configuration.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			logger.debug("Starting to load Rosie config for key {}", key);
			if (key.startsWith(EXPERT_PREFIX)) {
				String value = (String) configuration.get(key);
				AbstractKnowledgeSource instantiatedClass = instantiate(value, AbstractKnowledgeSource.class);
				if (instantiatedClass instanceof AbstractMLExpert)
					((AbstractMLExpert) instantiatedClass).setTrainingTime(trainingTime);
				blackboard.addKnowledgeSource(instantiatedClass.getClass() .getSimpleName(), instantiatedClass);
				logger.debug("Starting to load Rosie config for expert {}",
						value);
			} else if(key.startsWith(TRAINING_TIME)) {
				String value = (String) configuration.get(key);
				trainingTime = Long.parseLong(value);
				System.out.println("trainingtime" + trainingTime);
			}
		}
		setProperlyConfigured(true);
	}

	/**
	 * Called by the SCR when the configuration of a binding has been changed
	 * through the ConfigAdmin service.
	 * 
	 * @param configuration
	 *            Updated configuration properties
	 */
	public void modified(final Map<String, Object> configuration) {
		// update the internal configuration accordingly
	}

	/**
	 * Called by the SCR to deactivate the component when either the
	 * configuration is removed or mandatory references are no longer satisfied
	 * or the component has simply been stopped.
	 * 
	 * @param reason
	 *            Reason code for the deactivation:<br>
	 *            <ul>
	 *            <li>0 – Unspecified
	 *            <li>1 – The component was disabled
	 *            <li>2 – A reference became unsatisfied
	 *            <li>3 – A configuration was changed
	 *            <li>4 – A configuration was deleted
	 *            <li>5 – The component was disposed
	 *            <li>6 – The bundle was stopped
	 *            </ul>
	 */
	public void deactivate(final int reason) {
		this.bundleContext = null;
		// deallocate resources here that are no longer needed and
		// should be reset when activating this binding again
		controller.teardown();
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	protected long getRefreshInterval() {
		return refreshInterval;
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	protected String getName() {
		return "Rosie Refresh Service";
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	protected void execute() {
		controller.loopExperts();
		logger.debug("looping experts!");
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	protected void internalReceiveCommand(String itemName, Command command) {
		Date date = new Date();
		blackboard.addTuple(itemName, Level.L1, command,dateFormat.format(date), false);
		controller.loopExperts();
		logger.debug("internalReceiveCommand({},{}) is called!", itemName,
				command);
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	protected void internalReceiveUpdate(String itemName, State newState) {
		Date date = new Date();
		blackboard.addTuple(itemName, Level.L1, newState,
				dateFormat.format(date), false);
		controller.loopExperts();
		logger.debug("internalReceiveUpdate({},{}) is called!", itemName,
				newState);
	}

	public <T> T instantiate(final String className, final Class<T> type) {
		try {
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(Blackboard.class);
			Object obj = constructor.newInstance(blackboard);
			return type.cast(obj);
		} catch (final InstantiationException e) {
			e.printStackTrace();
			logger.error("Error instatiating " + e.getMessage());
			throw new IllegalStateException(e);
		} catch (final IllegalAccessException e) {
			logger.error("Error instatiating " + e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(e);
		} catch (final ClassNotFoundException e) {
			logger.error("Error instatiating " + e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(e);
		} catch (NoSuchMethodException e) {
			logger.error("Error instatiating " + e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(e);
		} catch (SecurityException e) {
			logger.error("Error instatiating " + e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(e);
		} catch (IllegalArgumentException e) {
			logger.error("Error instatiating " + e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			logger.error("Error instatiating " + e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException {
		// TODO Auto-generated method stub
		logger.debug("RosieBinding update called ");
	}

}
