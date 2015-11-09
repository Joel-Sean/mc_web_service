package mc.webservice.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {

	public static byte[] createTextImage(int width, int height, String text) throws IOException {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = image.createGraphics();
		Color c = new Color(0.6662f, 0.4569f, 0.3232f);
		GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.white,
				true);
		graphics2D.setPaint(gp);
		Font font = new Font("Verdana", Font.CENTER_BASELINE, 20);
		graphics2D.setFont(font);
		graphics2D.drawString(text, 2, 20);
		graphics2D.dispose();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
		ImageIO.write(image, "jpeg", outputStream);
		byte[] result = outputStream.toByteArray();
		outputStream.close();
		return result;
	}

}
