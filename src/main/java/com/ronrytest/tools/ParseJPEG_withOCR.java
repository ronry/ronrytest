package com.ronrytest.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ParseJPEG_withOCR {
	public static String getRecogniseStr(File imageFile) {
		String s = "";
		try {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getTileWidth();
			int height = image.getTileHeight();
			image = image.getSubimage(1, 1, width - 2, height - 2);
			// s = new OCR().recognizeEverything(image);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(" 图片识别失败！ ");
		}
		return s;
	}

	public static void main(String[] args) {
		String code = getRecogniseStr(new File("/home/ronry/ticketVerify.png"));
		if (code != null && code.length() > 0) {
			code = code.toLowerCase();
			code = code.replaceAll("q", "9");
			code = code.replaceAll("o", "0");
			code = code.replaceAll("l", "1");
		}
		System.out.println(code);
	}
}
