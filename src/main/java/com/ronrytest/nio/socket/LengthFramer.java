/**
 * 
 */
package com.ronrytest.nio.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author ronry
 * 
 */
public class LengthFramer implements Framer {

	@Override
	public void sendAFrame(OutputStream out, Serializable msg) throws Exception {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objStream = new ObjectOutputStream(byteOut);

		objStream.writeObject(msg);

		byte[] dataBytes = byteOut.toByteArray();

		byte[] lengthBytes = new byte[4];

		int length = dataBytes.length;
		for (int i = 0; i < 4; i++) {
			lengthBytes[i] = (byte) (length >> (i * 4));
		}

		out.write(dataBytes);
		out.flush();

		objStream.close();
	}

	@Override
	public <T> T receiveAFrame(InputStream in, Class<T> msgClass)
			throws Exception {

		byte[] lengthBytes = new byte[4];
		readEnough(in, lengthBytes);

		int length = 0;
		for (int i = 0; i < 4; i++) {
			length += (lengthBytes[i] & 0xff) << (i * 4);
		}

		byte[] dataBytes = new byte[length];
		readEnough(in, dataBytes);

		ObjectInputStream objIn = new ObjectInputStream(
				new ByteArrayInputStream(dataBytes));

		return null;
	}

	// read(byte b[], int off, int len)能确保一定塞满b吗？
	private void readEnough(InputStream in, byte[] dataBytes) throws Exception {

		int unreadLength = dataBytes.length;
		int alreadLength = 0;

		while (true) {
			alreadLength += in.read(dataBytes, alreadLength, unreadLength);
			if (alreadLength >= dataBytes.length) {
				break;
			} else {
				unreadLength = dataBytes.length - alreadLength;
			}
		}

	}
}
