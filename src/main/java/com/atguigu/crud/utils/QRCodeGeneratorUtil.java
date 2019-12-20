package com.atguigu.crud.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGeneratorUtil
{

	/**
	 * 将url转为二维码字节数组
	 * @author Peng.Hu
	 * @date 2019年12月4日
	 * @param url
	 * @return 字节数组
	 * @throws Exception
	 *
	 */
	public static byte[] generateQRCodeImage(String url) throws Exception
	{
		int width = 360;
		int height = 360;
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);
		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		byte[] pngData = pngOutputStream.toByteArray();
		return pngData;
	}

	/**
	 * 将url转为二维码图片,并保存在本地
	 * @author Peng.Hu
	 * @date 2019年12月4日
	 * @param url
	 * @param width
	 * @param height
	 * @throws Exception
	 *
	 */
	private static void generateQRCodeImage(String url, int width, int height) throws Exception
	{
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);
		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		byte[] data = pngOutputStream.toByteArray();
		OutputStream oute = new FileOutputStream(new File(
		        "D:\\eclipse-workspace\\4.6\\learning\\ssm-crud\\src\\main\\webapp\\static\\images\\MyQRCode.png"));
		oute.write(data);
		oute.flush();
		oute.close();
		System.out.println("success");
	}
	
	public static void main(String[] args)
	{
		try
		{
			generateQRCodeImage("https://blog.csdn.net/gisboygogogo/article/details/86036656", 360, 360);
		}
		catch (Exception e)
		{
			System.out.println("Could not generate QR Code, Exception :: " + e.getMessage());
		}

	}
}
