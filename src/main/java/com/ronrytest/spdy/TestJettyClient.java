package com.ronrytest.spdy;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.spdy.api.DataInfo;
import org.eclipse.jetty.spdy.api.SPDY;
import org.eclipse.jetty.spdy.api.Session;
import org.eclipse.jetty.spdy.api.Stream;
import org.eclipse.jetty.spdy.api.StreamFrameListener;
import org.eclipse.jetty.spdy.api.StringDataInfo;
import org.eclipse.jetty.spdy.api.SynInfo;
import org.eclipse.jetty.spdy.client.SPDYClient;
import org.eclipse.jetty.util.Fields;

public class TestJettyClient {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// this listener receives data from the server. It then prints out the
		// data
		StreamFrameListener streamListener = new StreamFrameListener.Adapter() {

			public void onData(Stream stream, DataInfo dataInfo) {
				// Data received from server
				String content = dataInfo.asString("UTF-8", true);
				System.out.println("SPDY content: " + content);
			}
		};

		// Start a SPDYClient factory shared among all SPDYClient instances
		SPDYClient.Factory clientFactory = new SPDYClient.Factory();
		clientFactory.start();

		// Create one SPDYClient instance
		SPDYClient client = clientFactory.newSPDYClient(SPDY.V2);

		// Obtain a Session instance to send data to the server that listens on
		// port 8181
		Session session = client.connect(
				new InetSocketAddress("localhost", 8181), null).get(500,
				TimeUnit.SECONDS);

		// Sends SYN_STREAM and DATA to the server
		Fields fields = new Fields();
		final Stream stream = session.syn(new SynInfo(fields, false),
				streamListener);

		// start a timer that sends a request to this stream every second
		ScheduledExecutorService executor = Executors
				.newSingleThreadScheduledExecutor();
		Runnable periodicTask = new Runnable() {
			private int i = 0;

			public void run() {
				// send a request, don't close the stream
				try {
					stream.data(new StringDataInfo("Data from the client "
							+ i++, false));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		executor.scheduleAtFixedRate(periodicTask, 0, 1, TimeUnit.SECONDS);

	}

}
