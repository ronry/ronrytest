package com.ronrytest.spdy;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.spdy.api.DataInfo;
import org.eclipse.jetty.spdy.api.ReplyInfo;
import org.eclipse.jetty.spdy.api.Stream;
import org.eclipse.jetty.spdy.api.StreamFrameListener;
import org.eclipse.jetty.spdy.api.StringDataInfo;
import org.eclipse.jetty.spdy.api.SynInfo;
import org.eclipse.jetty.spdy.api.server.ServerSessionFrameListener;
import org.eclipse.jetty.spdy.server.SPDYServerConnector;

public class TestJettyServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		ServerSessionFrameListener frameListener = new ServerSessionFrameListener.Adapter() {

			public StreamFrameListener onSyn(final Stream stream,
					SynInfo synInfo) {

				// Send a reply to this message
				try {
					stream.reply(new ReplyInfo(false));
				} catch (Exception e) {
					e.printStackTrace();
				}

				// and start a timer that sends a request to this stream every 5
				// seconds
				ScheduledExecutorService executor = Executors
						.newSingleThreadScheduledExecutor();
				Runnable periodicTask = new Runnable() {
					private int i = 0;

					public void run() {
						// send a request and don't close the stream
						try {
							stream.data(new StringDataInfo(
									"Data from the server " + i++, false));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				executor.scheduleAtFixedRate(periodicTask, 0, 1,
						TimeUnit.SECONDS);

				// Next create an adapter to further handle the client input
				// from specific stream.
				return new StreamFrameListener.Adapter() {

					/**
					 * We're only interested in the data, not the headers in
					 * this example
					 */
					public void onData(Stream stream, DataInfo dataInfo) {
						String clientData = dataInfo.asString("UTF-8", true);
						System.out
								.println("Received the following client data: "
										+ clientData);
					}
				};
			}
		};

		// Wire up and start the connector
		org.eclipse.jetty.server.Server server = new Server();

		SPDYServerConnector connector = new SPDYServerConnector(server,
				frameListener);
		connector.setPort(8181);

		server.addConnector(connector);
		server.start();

		server.join();

		System.out.println("started!!!");

	}

}
