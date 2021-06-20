package com.unla.app.controllers;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import com.unla.app.helpers.QRHelper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QRController {

	@GetMapping(value = "/api/qr/{codeText}/{width}/{height}")
	public ResponseEntity<String> generateQRCode(@PathVariable("codeText") String codeText,
			@PathVariable("width") Integer width, @PathVariable("height") Integer height, HttpServletRequest request)
			throws Exception {
		String domain;
		if (request.getServerName() == "127.0.0.1") {
			domain = "http://127.0.0.1:8080";
		} else {
			domain = "https://" + request.getServerName();
		}
		String url = domain + "/permisos/" + codeText + "?qr";

		String encodedFile = Base64.getEncoder().encodeToString(QRHelper.getQRCodeImage(url, width, height));
		return ResponseEntity.status(HttpStatus.OK).body(encodedFile);
	}
}
