/**
 * 
 */
package com.ronrytest.nio.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author ronry
 * 
 */
public interface Framer {

	public void sendAFrame(OutputStream out, Serializable msg) throws Exception;

	public <T> T receiveAFrame(InputStream in, Class<T> msgClass)
			throws Exception;
}
