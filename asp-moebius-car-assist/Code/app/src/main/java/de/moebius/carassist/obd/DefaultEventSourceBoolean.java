package de.moebius.carassist.obd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;

import com.github.eventsource.client.EventSource;
import com.github.eventsource.client.EventSourceClient;
import com.github.eventsource.client.EventSourceHandler;
import com.github.eventsource.client.MessageEvent;

/**
 * Created by david on 28.01.15.
 */
public class DefaultEventSourceBoolean extends EventSource {
    private final String USER_AGENT = "Mozilla/5.0";

    private WebOBDController controller;

    private String signalName;

    public DefaultEventSourceBoolean(final String signalName,
                                     final String baseURL, final WebOBDController controller)
            {

        super(new EventSourceClient(), 100, URI.create(baseURL + signalName
                + "/subscription"), new EventSourceHandler() {
            public void onConnect() {

            }

            public void onMessage(String event, MessageEvent message) {
                controller.newValue(signalName,
                        message.data.equals("ON") ? true : false);
            }

            public void onError(Throwable t) {
                System.err.println("Error: " + t);
            }
        });
        this.controller = controller;
        this.signalName = signalName;
        String url = baseURL + signalName;

        URL obj = null;
        try {
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            // add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            controller.newValue(signalName, response.toString().equals("ON") ? true : false);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
