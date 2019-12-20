package com.atguigu.crud.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crud.utils.QRCodeGeneratorUtil;

@Controller
public class QRImageController
{

	@RequestMapping("/qrimage")
	public ResponseEntity<byte[]> getQRImage()
	{
		//二维码内的信息
		String info = "https://blog.csdn.net/gisboygogogo/article/details/86036656";

		byte[] qrcode = null;
		try
		{
			qrcode = QRCodeGeneratorUtil.generateQRCodeImage(info);
		}
		catch (Exception e)
		{
			System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
		}

		//设置 headers 信息
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<byte[]>(qrcode, headers, HttpStatus.CREATED);
	}
}
