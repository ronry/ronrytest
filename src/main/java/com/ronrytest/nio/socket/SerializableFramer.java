/**
 * 
 */
package com.ronrytest.nio.socket;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * 用java序列化作为分帧标记。如果所有的数据都是通过这个方式写的是没问题的，否则就不行
 * 
 * @author ronry
 * 
 */
public class SerializableFramer implements Framer {

	@Override
	public void sendAFrame(OutputStream out, Serializable msg) throws Exception {
		ObjectOutputStream objStream = new ObjectOutputStream(out);

		objStream.writeObject(msg);

		out.flush();

	}

	@Override
	public <T> T receiveAFrame(InputStream in, Class<T> msgClass)
			throws Exception {

		ObjectInputStream objIn = new ObjectInputStream(in);

		return msgClass.cast(objIn.readObject());

	}

}
