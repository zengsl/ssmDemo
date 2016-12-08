package com.ssm.pt.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @author zengsl
 *
 */
public class ImageCreator {
	
    private static String code;
    
	public static InputStream getImage(){
		byte[] arry = getByteArry();
		InputStream ips = new ByteArrayInputStream(arry);
		
		
		return ips;
	}
	
	public static byte[] getByteArry(){
		
		BufferedImage bfm = new BufferedImage(100, 40, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = bfm.getGraphics();
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.drawRect(20, 10, 100, 40);
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		String num = String.valueOf(r.nextInt(9999));
		Font f = new Font(null, Font.ITALIC, 26);
		g.setFont(f);
		g.drawString(num, 20, 30);
		code = num;   
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bfm, "jepg", bos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bos.toByteArray();
	}
	public static void generate(HttpServletResponse response) throws IOException{
		
		BufferedImage bfm = new BufferedImage(100, 40, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = bfm.getGraphics();
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.drawRect(20, 10, 100, 40);
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		String num = String.valueOf(r.nextInt(9999));
		Font f = new Font(null, Font.ITALIC, 26);
		g.setFont(f);
		g.drawString(num, 20, 30);
		code = num;   
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bfm, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		bos.flush();
	}
	
	public static String getCode() {
		return code;
	}
	public static void setCode(String code) {
		ImageCreator.code = code;
	}
	
	
	
}

