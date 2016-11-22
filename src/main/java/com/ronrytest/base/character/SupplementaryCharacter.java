package com.ronrytest.base.character;

import java.io.UnsupportedEncodingException;

/**
 * <pre>
 * 探讨字符（汉字）在计算机内的表示形式:
 * 可以将字符对应的字节打印出来。
 * 因为char或者byte以及他们对应的包装类Byte和Character都没有提供toBinaryString()方法（可以直接输出对应数字的计算机表示(补码表示http://franksinger.iteye.com/blog/614540)）
 * 所以需要先将char或者byte转换成int,但是又不能改变二进制的形式，所以不能直接将char或者byte赋值给int
 * 例如byte b=xxx;int i=b; 或者char c=xxx;int i=x;这两种方式都会改变内存的字节表示。（第二种不会，因为高位补的是0），因为会执行补位：
 * 如果最初的数值类型是有符号的，那么就执行符号扩展(负数，高位补1，正数高位补0)；如果它是char，那么不管它将要被转成什么类型，都执行零扩展（都是高位补0）。
 * 
 * 为了解决这个问题可以让byte和0xff(这是一个int形的)先执行与运算，将高位置为0
 * </pre>
 * 
 * @author ronry
 * 
 */
public class SupplementaryCharacter {

	public static String bytesToBinaryString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toBinaryString(v);
			stringBuilder.append(hv);
			if (i < src.length - 1) {
				stringBuilder.append("-");
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {

		//char chinese = '中';
        char chinese = 'x';
		// 因为char是无符号数，所以Integer.toBinaryString(chinese)这样输出的就已经是其在内存中的二进制表示(char转int，高位补的是0)
		System.out.println(Integer.toBinaryString(chinese));
		System.out.println(Integer.toHexString(chinese));
		// 也可以直接用内存的unicode表示形式给char或者string赋值，制定一个中文字符
		char ss2 = '\u4e2d';
		String ss = "\u4e2d";
		System.out.println(ss);
		System.out.println(ss2);

		// 采用不同编码方式输出字符时，它占的字节是不一样的
		System.out.println(ss.getBytes("gbk").length);
		System.out.println(ss.getBytes("GBK")[0]);
		// 将byte直接转换成int会进行有符号方式的补位，因为-42是负数，所以高位被补1了
		System.out.println(Integer.toBinaryString(ss.getBytes("GBK")[0]));
		System.out.println(ss.getBytes("GBK")[1]);
		// 这个输出的才真正是“中”转换成gbk编码后的二进制形式，可以看到和原始内存中的形式是不一样的
		System.out.println(bytesToBinaryString(ss.getBytes("GBK")));

		System.out.println(ss.getBytes("utf-8").length);
		System.out.println(bytesToBinaryString(ss.getBytes("utf-8")));

		// 汉字(字符)在java(jvm)内部是用UTF-16表示的，也就是说任何一个字符都占两个字节
		// 但是将字符转换成字节的时候，则需要根据转换时制定的编码方式来确定每个字符的占用的字节
		// 不要把这两个概念搞混杂了，这两种方式转换后的字节序列也是不一样的

	}
}
