/**
 * 
 */
package com.ronrytest.nio.io;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

import com.ronrytest.base.character.SupplementaryCharacter;

/**
 * @author ronry
 * 
 */
public class DataStreamTest {

	public static void main(String[] args) throws Exception {
		System.out.println(SupplementaryCharacter.bytesToBinaryString("中"
				.getBytes("UTF-8")));

		ByteArrayOutputStream data = new ByteArrayOutputStream();
		FileOutputStream file = new FileOutputStream("/home/ronry/tt.log");
		DataOutputStream out = new DataOutputStream(file);

		out.writeUTF("中");
		// out.writeBoolean(true);
		out.flush();
		out.close();

		System.out.println(SupplementaryCharacter.bytesToBinaryString(data
				.toByteArray()));

		// DataInputStream in = new DataInputStream(new FileInputStream(
		// "/home/ronry/tt.log"));
		//
		// // System.out.println(in.readBoolean());
		// System.out.println(in.readInt());

	}
}
