package com.cdspool.main.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MultipartException.class)
	public void handleMultipart(MultipartException e, RedirectAttributes attributes) {
//		attributes.addFlashAttribute("message", e.getCause().getMessage());
		//attributes.addFlashAttribute("message", "Error al cargar el archivo");
		
		//return "{'msg':'Error al cargar el archivo'}";
	}
}
