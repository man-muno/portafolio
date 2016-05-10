package de.visiom.carpc.services.navigation.handlers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.visiom.carpc.asb.messagebus.EventPublisher;
import de.visiom.carpc.asb.messagebus.events.ValueChangeEvent;
import de.visiom.carpc.asb.messagebus.handlers.ValueChangeEventHandler;
import de.visiom.carpc.asb.servicemodel.exceptions.NoSuchParameterException;
import de.visiom.carpc.asb.servicemodel.parameters.Parameter;
import de.visiom.carpc.asb.servicemodel.valueobjects.NumberValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.SetValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.StateValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.StringValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.SwitchValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.SwitchValueObject.Switch;
import de.visiom.carpc.asb.servicemodel.valueobjects.ValueObject;
import de.visiom.carpc.asb.serviceregistry.ServiceRegistry;
import de.visiom.carpc.asb.serviceregistry.exceptions.NoSuchServiceException;
import de.visiom.carpc.services.navigation.Destination;
import de.visiom.carpc.services.navigation.Navigation;
import de.visiom.carpc.services.navigation.ServiceConstants;
import de.visiom.carpc.services.navigation.exceptions.NavigationException;
import de.visiom.carpc.services.navigation.model.Point;
import de.visiom.carpc.services.navigation.publishers.TurnByTurnPublisher;
import de.visiom.carpc.services.navigation.util.HttpRequest;

public class NavigationHandler extends ValueChangeEventHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(NavigationHandler.class);

	private Navigation navigation = Navigation.INSTANCE;

	private ServiceRegistry serviceRegistry;

	private TurnByTurnPublisher turnByTurnPublisher;

	private EventPublisher eventPublisher;

	public void setEventPublisher(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void setTurnByTurnPublisher(TurnByTurnPublisher turnByTurnPublisher) {
		this.turnByTurnPublisher = turnByTurnPublisher;
	}

	public void start() {
		
		boolean isWifiStarted = false;
		while(isWifiStarted){
			HttpRequest request = HttpRequest.get("http://www.google.com");
			isWifiStarted = request.code() == 200;			
		}
		
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.IS_NAVIGATION_ACTIVE, false);

		// Add the Favorites and publish the parameters that need to be
		// available from the REST API
		// If no default values are to be set, please comment the code below
		addDefaultFavorites();
		// System.out.println("Added default favorites");
		publishDefaultFavorites();
		// System.out.println("published default favorites");
		publishDefaultNavigationValues();
		// System.out.println("published default navigation values");

		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.NAVIGATION_FAVORITES,
				navigation.getJSonFavorites());
	}

	private void publishDefaultFavorites() {

		// Publish attributes for favorite home
		Destination home = navigation
				.getFavoriteByKey(Navigation.FAVORITE_HOME);
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.HOME_ADDRESS, home.getAddress());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.HOME_LATITUDE, home.getLatitude());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.HOME_LONGITUDE, home.getLongitude());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.HOME_NAME, home.getName());

		// Publish attributes for favorite work
		Destination work = navigation
				.getFavoriteByKey(Navigation.FAVORITE_WORK);
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.WORK_ADDRESS, work.getAddress());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.WORK_LATITUDE, work.getLatitude());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.WORK_LONGITUDE, work.getLongitude());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.WORK_NAME, work.getName());

		// Publish attributes for favorite favorite1
		Destination favorite1 = navigation
				.getFavoriteByKey(Navigation.FAVORITE_1);
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_1_ADDRESS, favorite1.getAddress());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_1_LATITUDE, favorite1.getLatitude());
		turnByTurnPublisher
				.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
						ServiceConstants.FAVORITE_1_LONGITUDE,
						favorite1.getLongitude());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_1_NAME, favorite1.getName());

		// Publish attributes for favorite favorite2
		Destination favorite2 = navigation
				.getFavoriteByKey(Navigation.FAVORITE_2);
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_2_ADDRESS, favorite2.getAddress());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_2_LATITUDE, favorite2.getLatitude());
		turnByTurnPublisher
				.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
						ServiceConstants.FAVORITE_2_LONGITUDE,
						favorite2.getLongitude());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_2_NAME, favorite2.getName());

		// Publish attributes for favorite favorite3
		Destination favorite3 = navigation
				.getFavoriteByKey(Navigation.FAVORITE_3);
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_3_ADDRESS, favorite3.getAddress());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_3_LATITUDE, favorite3.getLatitude());
		turnByTurnPublisher
				.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
						ServiceConstants.FAVORITE_3_LONGITUDE,
						favorite3.getLongitude());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_3_NAME, favorite3.getName());

		// Publish attributes for favorite favorite4
		Destination favorite4 = navigation
				.getFavoriteByKey(Navigation.FAVORITE_4);
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_4_ADDRESS, favorite4.getAddress());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_4_LATITUDE, favorite4.getLatitude());
		turnByTurnPublisher
				.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
						ServiceConstants.FAVORITE_4_LONGITUDE,
						favorite4.getLongitude());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_4_NAME, favorite4.getName());

		// Publish attributes for favorite favorite5
		Destination favorite5 = navigation
				.getFavoriteByKey(Navigation.FAVORITE_5);
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_5_ADDRESS, favorite5.getAddress());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_5_LATITUDE, favorite5.getLatitude());
		turnByTurnPublisher
				.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
						ServiceConstants.FAVORITE_5_LONGITUDE,
						favorite5.getLongitude());
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.FAVORITE_5_NAME, favorite5.getName());

	}

	private void publishDefaultNavigationValues() {
		Destination destination = navigation
				.getFavoriteByKey(Navigation.FAVORITE_HOME);
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.NAVIGATION_DESTINATION_ADDRESS,
				destination.getAddress());
		turnByTurnPublisher.publishState(
				ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.NAVIGATION_DESTINATION,
				Navigation.FAVORITE_HOME);
		turnByTurnPublisher.publishState(
				ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.NAVIGATION_ROUTING_TYPE,
				ServiceConstants.ROUTING_TYPE_ENERGY_EFFICIENT);
		navigation.setDestination(destination);
	}

	private void addDefaultFavorites() {
		Destination home = new Destination(new Point(48.148657, 11.568569),
				"Arcisstraße 21, 80333 München", "Zuhause",
				Navigation.FAVORITE_HOME);
		home.setDestination(true);
		navigation.addFavorite(Navigation.FAVORITE_HOME, home);

		Destination work = new Destination(new Point(48.264138, 11.670789),
				"Boltzmannstraße 15, 85748 Garching", "Arbeit",
				Navigation.FAVORITE_WORK);
		navigation.addFavorite(Navigation.FAVORITE_WORK, work);

		Destination f1 = new Destination(new Point(48.218811, 11.624738),
				"Werner-Heisenberg-Allee 25, 80939 München", "Allianz Arena",
				Navigation.FAVORITE_1);
		navigation.addFavorite(Navigation.FAVORITE_1, f1);

		Destination f2 = new Destination(new Point(48.100622, 11.551370),
				"Tierparkstraße 30, 81543 München", "Tierpark Hellabrunn",
				Navigation.FAVORITE_2);
		navigation.addFavorite(Navigation.FAVORITE_2, f2);

		Destination f3 = new Destination(new Point(48.353673, 11.774958),
				"Nordallee 25, 85356 München", "Flughafen München",
				Navigation.FAVORITE_3);
		navigation.addFavorite(Navigation.FAVORITE_3, f3);

		Destination f4 = new Destination(new Point(48.134026, 11.550219),
				"Theresienwiese, 80339 München", "Theresienwiese",
				Navigation.FAVORITE_4);
		navigation.addFavorite(Navigation.FAVORITE_4, f4);

		Destination f5 = new Destination(
				new Point(48.144882, 11.586171),
				"Prinzregentenstraße 1, 80538 München",
				"P1", Navigation.FAVORITE_5);
		navigation.addFavorite(Navigation.FAVORITE_5, f5);
	}

	public void end() {

	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public void onValueChangeEvent(ValueChangeEvent valueChangeEvent) {
		Parameter parameter = valueChangeEvent.getParameter();
		String paramName = parameter.getName();

		// When a new parameter comes, update in on the navi
		if (paramName.equals(ServiceConstants.LATITUDE_PARAM_NAME))
			navigation.setCurrentLatitude(getDoubleValue(valueChangeEvent
					.getValue()));
		else if (paramName.equals(ServiceConstants.LONGITUDE_PARAM_NAME))
			navigation.setCurrentLongitude(getDoubleValue(valueChangeEvent
					.getValue()));
		else if (paramName.equals(ServiceConstants.BATTERY_STATUS))
			navigation.setCurrentBatteryStatus(getIntValue(valueChangeEvent
					.getValue()));
		else if ((paramName.equals(ServiceConstants.ROUTING_TYPE)))
			setRoutingType(valueChangeEvent);
		else if ((paramName.equals(ServiceConstants.NAVIGATION_DESTINATION)))
			setDestination(valueChangeEvent, parameter);
		else if ((paramName.equals(ServiceConstants.IS_NAVIGATION_ACTIVE)))
			setActiveNavigation(valueChangeEvent, parameter);
		else if (paramName.equals(ServiceConstants.CANCEL_ROUTING))
			cancelRouting(valueChangeEvent, parameter);
		else if (paramName.equals(ServiceConstants.NEW_WAYPOINT))
			addWaypoint(valueChangeEvent, parameter);
		else if (paramName.equals(ServiceConstants.NAVIGATION_FAVORITES))
			setFavorites(valueChangeEvent.getValue());
		// Setting of favorites - home
		else if (paramName.equals(ServiceConstants.HOME_ADDRESS))
			setFavoriteAddress(Navigation.FAVORITE_HOME,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.HOME_LATITUDE))
			setFavoriteLatitude(Navigation.FAVORITE_HOME,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.HOME_LONGITUDE))
			setFavoriteLongitude(Navigation.FAVORITE_HOME,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.HOME_NAME))
			setFavoriteName(Navigation.FAVORITE_HOME,
					valueChangeEvent.getValue());
		// Setting of favorites - work
		else if (paramName.equals(ServiceConstants.WORK_ADDRESS))
			setFavoriteAddress(Navigation.FAVORITE_WORK,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.WORK_LATITUDE))
			setFavoriteLatitude(Navigation.FAVORITE_WORK,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.WORK_LONGITUDE))
			setFavoriteLongitude(Navigation.FAVORITE_WORK,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.WORK_NAME))
			setFavoriteName(Navigation.FAVORITE_WORK,
					valueChangeEvent.getValue());
		// Setting of favorites - Favorite 1
		else if (paramName.equals(ServiceConstants.FAVORITE_1_ADDRESS))
			setFavoriteAddress(Navigation.FAVORITE_1,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_1_LATITUDE))
			setFavoriteLatitude(Navigation.FAVORITE_1,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_1_LONGITUDE))
			setFavoriteLongitude(Navigation.FAVORITE_1,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_1_NAME))
			setFavoriteName(Navigation.FAVORITE_1, valueChangeEvent.getValue());
		// Setting of favorites - Favorite 2
		else if (paramName.equals(ServiceConstants.FAVORITE_2_ADDRESS))
			setFavoriteAddress(Navigation.FAVORITE_2,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_2_LATITUDE))
			setFavoriteLatitude(Navigation.FAVORITE_2,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_2_LONGITUDE))
			setFavoriteLongitude(Navigation.FAVORITE_2,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_2_NAME))
			setFavoriteName(Navigation.FAVORITE_2, valueChangeEvent.getValue());
		// Setting of favorites - Favorite 3
		else if (paramName.equals(ServiceConstants.FAVORITE_3_ADDRESS))
			setFavoriteAddress(Navigation.FAVORITE_3,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_3_LATITUDE))
			setFavoriteLatitude(Navigation.FAVORITE_3,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_3_LONGITUDE))
			setFavoriteLongitude(Navigation.FAVORITE_3,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_3_NAME))
			setFavoriteName(Navigation.FAVORITE_3, valueChangeEvent.getValue());
		// Setting of favorites - Favorite 4
		else if (paramName.equals(ServiceConstants.FAVORITE_4_ADDRESS))
			setFavoriteAddress(Navigation.FAVORITE_4,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_4_LATITUDE))
			setFavoriteLatitude(Navigation.FAVORITE_4,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_4_LONGITUDE))
			setFavoriteLongitude(Navigation.FAVORITE_4,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_4_NAME))
			setFavoriteName(Navigation.FAVORITE_4, valueChangeEvent.getValue());
		// Setting of favorites - Favorite 5
		else if (paramName.equals(ServiceConstants.FAVORITE_5_ADDRESS))
			setFavoriteAddress(Navigation.FAVORITE_5,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_5_LATITUDE))
			setFavoriteLatitude(Navigation.FAVORITE_5,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_5_LONGITUDE))
			setFavoriteLongitude(Navigation.FAVORITE_5,
					valueChangeEvent.getValue());
		else if (paramName.equals(ServiceConstants.FAVORITE_5_NAME))
			setFavoriteName(Navigation.FAVORITE_5, valueChangeEvent.getValue());

		try {
			// It tries to complete the points of the existing route to
			// calculate a better turn by turn
			navigation.completeLegPoints();
		} catch (NavigationException e) {
			LOG.error(e.getMessage());
		}

	}

	private void setFavorites(ValueObject jsonFavorites) {
		navigation.replaceFavoritesFromJSON(getStringValue(jsonFavorites));
		Destination destination = navigation.getDestination();
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.NAVIGATION_DESTINATION_ADDRESS,
				destination.getAddress());
		turnByTurnPublisher.publishState(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.NAVIGATION_DESTINATION,
				destination.getId());
	}

	

	private void setFavoriteLongitude(String favorite, ValueObject longitude) {
		navigation.setFavoriteLongitud(favorite, getDoubleValue(longitude));
	}

	private void setFavoriteName(String favorite, ValueObject name) {
		navigation.setFavoriteName(favorite, getStringValue(name));
	}

	private void setFavoriteLatitude(String favorite, ValueObject latitude) {
		navigation.setFavoriteLatitude(favorite, getDoubleValue(latitude));
	}

	private void setFavoriteAddress(String favorite, ValueObject address) {
		navigation.setFavoriteAddress(favorite, getStringValue(address));
	}

	private void setActiveNavigation(ValueChangeEvent valueChangeEvent,
			Parameter parameter) {
		SwitchValueObject isNavigationActive = (SwitchValueObject) valueChangeEvent
				.getValue();
		// System.out.println("isNavigatingNow " +
		// navigation.isNavigationActive() + " received " +
		// isNavigationActive.getValue());
		if (isNavigationActive.getValue().equals(Switch.ON)) {
			// It has received the ON value. Now ask for the value on the
			// navigation
			if (!navigation.isNavigationActive()) {
				// If it has received the ON value and it is currently off, then
				// start navigating
				try {
					if (navigation.setActiveNavigation())
						turnByTurnPublisher.publish(
								ServiceConstants.NAVIGATION_SERVICE_NAME,
								parameter.getName(), true);
					else
						// If unsuccessful publish false
						turnByTurnPublisher.publish(
								ServiceConstants.NAVIGATION_SERVICE_NAME,
								parameter.getName(), false);
				} catch (NavigationException e) {
					LOG.error(e.getMessage());
					// If unsuccessful publish false
					turnByTurnPublisher.publish(
							ServiceConstants.NAVIGATION_SERVICE_NAME,
							parameter.getName(), false);
				}
			}
			// The values are the same, do nothing
		} else if (isNavigationActive.getValue().equals(Switch.OFF)) {
			// It has received the OFF value. Now ask for the value on the
			// navigation
			if (navigation.isNavigationActive()) {
				navigation.cancelTurnByTurn();
				if (!navigation.isNavigationActive()){
					turnByTurnPublisher.publish(
							ServiceConstants.NAVIGATION_SERVICE_NAME,
							parameter.getName(), false);
					turnByTurnPublisher.publishEmptyString(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_JSON_ROUTE);
				}
				else
					// If unsuccessful publish false
					turnByTurnPublisher.publish(
							ServiceConstants.NAVIGATION_SERVICE_NAME,
							parameter.getName(), true);
			}
			// The values are the same, do nothing
		}
	}

	private void setDestination(ValueChangeEvent valueChangeEvent,
			Parameter parameter) {
		Destination destination = navigation.getFavoriteByKey(valueChangeEvent
				.getValue().toString());
		navigation.setDestination(destination);
		turnByTurnPublisher.publish(ServiceConstants.NAVIGATION_SERVICE_NAME,
				ServiceConstants.NAVIGATION_DESTINATION_ADDRESS,
				destination.getAddress());
		

	}

	private void setRoutingType(ValueChangeEvent valueChangeEvent) {
		StateValueObject cancelRoute = (StateValueObject) valueChangeEvent
				.getValue();
		navigation.setRoutingType(cancelRoute.getValue());

	}

	private void cancelRouting(ValueChangeEvent valueChangeEvent,
			Parameter parameter) {
		SwitchValueObject cancelRoute = (SwitchValueObject) valueChangeEvent
				.getValue();
		if (cancelRoute.getValue().equals(Switch.ON)) {
			// It has received the OFF value. Now ask for the value on the
			// navigation
			boolean isNavigatingNow = navigation.isNavigationActive();
			if (isNavigatingNow) {
				navigation.cancelTurnByTurn();
				turnByTurnPublisher.publish(
						ServiceConstants.NAVIGATION_SERVICE_NAME,
						parameter.getName(), false);
				// Publish OFF value
			}
		}

	}

	private void addWaypoint(ValueChangeEvent valueChangeEvent,
			Parameter parameter) {
		// Read the waypoint values
		Map<Parameter, ValueObject> map = getSetValue(valueChangeEvent
				.getValue());
		try {
			double wayPointLatitude = getDoubleValue(map.get(serviceRegistry
					.getService(ServiceConstants.NAVIGATION_SERVICE_NAME)
					.getParameter(ServiceConstants.NEW_WAYPOINT_LATITUDE)));
			double wayPointLongitude = getDoubleValue(map.get(serviceRegistry
					.getService(ServiceConstants.NAVIGATION_SERVICE_NAME)
					.getParameter(ServiceConstants.NEW_WAYPOINT_LONGITUDE)));
			String wayPointType = getStringValue(map.get(serviceRegistry
					.getService(ServiceConstants.NAVIGATION_SERVICE_NAME)
					.getParameter(ServiceConstants.NEW_WAYPOINT_TYPE)));
			navigation.addWaypointToRoute(wayPointLatitude, wayPointLongitude,
					wayPointType);
		} catch (NoSuchParameterException e) {
			LOG.error(e.getMessage());
		} catch (NoSuchServiceException e) {
			LOG.error(e.getMessage());
		} catch (NavigationException e) {
			LOG.error(e.getMessage());
		}
	}

	private Map<Parameter, ValueObject> getSetValue(ValueObject valueObject) {
		SetValueObject setValueObject = (SetValueObject) valueObject;
		return setValueObject.getValue();
	}

	private String getStringValue(ValueObject valueObject) {
		return ((StringValueObject) valueObject).getValue();
	}

	private int getIntValue(ValueObject valueObject) {
		NumberValueObject numberValueObject = (NumberValueObject) valueObject;
		return numberValueObject.getValue().intValue();
	}

	private double getDoubleValue(ValueObject valueObject) {
		NumberValueObject numberValueObject = (NumberValueObject) valueObject;
		return numberValueObject.getValue().doubleValue();
	}

}
