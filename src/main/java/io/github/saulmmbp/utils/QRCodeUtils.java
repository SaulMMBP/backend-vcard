package io.github.saulmmbp.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import io.github.saulmmbp.exceptions.ApplicationException;
import uk.org.okapibarcode.backend.QrCode;
import uk.org.okapibarcode.graphics.Color;
import uk.org.okapibarcode.output.Java2DRenderer;

@Component
public class QRCodeUtils {
	
	private static final int MODULE_WIDTH = 5;
	private static final int FONT_SIZE = 16;
	private static final String FONT_NAME = "Monospaced"; 

	public byte[] generateQRCode(String code) {
		QrCode qr = new QrCode();
		qr.setFontName(FONT_NAME);
		qr.setFontSize(FONT_SIZE);
		qr.setModuleWidth(MODULE_WIDTH);
		qr.setContent(code);
		
		int width = qr.getWidth();
		int height = qr.getHeight();
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		Graphics2D graphics2d = image.createGraphics();
	    Java2DRenderer renderer = new Java2DRenderer(graphics2d, 1, Color.WHITE, Color.BLACK);
	    renderer.render(qr);
	    graphics2d.dispose(); 
	    
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", byteArray);
		} catch (IOException e) {
			throw new ApplicationException("Error al generar código QR");
		}
		return byteArray.toByteArray();
	}
	
}
