package com.cdspool.main.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IUsuarioRepository;
import com.cdspool.main.service.DocumentoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(value = "subir")
public class FileUploadController {

	@Autowired
	DocumentoController dc;

	@Autowired
	IUsuarioRepository iUsuarioRepository;

	@Autowired
	DocumentoService sDocu;

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Secured({ "ROLE_ADMIN", "ROLE_ALUMNO", "ROLE_EMPRESA" })
	public String uploadFile(@RequestParam("file") MultipartFile file, Principal principal,
			RedirectAttributes attributes) throws IOException {
		if (file == null || file.isEmpty()) {
			return "{'msg':'Por favor seleccione un archivo'}";
		}

		Usuario usua = iUsuarioRepository.findByEmail(principal.getName());
		
		StringBuilder builder = new StringBuilder();
		builder.append(System.getProperty("user.home"));
		builder.append(File.separator);
		builder.append("Desktop");
		builder.append(File.separator);
		builder.append("Proyecto_n3");
		builder.append(File.separator);
		builder.append("BolsaCDSS");
		builder.append(File.separator);
		builder.append("img");
		builder.append(File.separator);
		builder.append("img_"+usua.getId()+".png");

		File convertFile = new File(builder.toString());
		convertFile.createNewFile();

		try (FileOutputStream fout = new FileOutputStream(convertFile)) {

			fout.write(file.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "{'msg':'Archivo cargado correctamente'}";
	}
	@RequestMapping(value = "/curriculum", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadcv(@RequestParam("file") MultipartFile file, Principal principal,
			RedirectAttributes attributes) throws IOException {
		if (file == null || file.isEmpty()) {
			return "{'msg':'Por favor seleccione un archivo'}";
		}
		
		Usuario usua = iUsuarioRepository.findByEmail(principal.getName());
		
		StringBuilder builder = new StringBuilder();
		builder.append(System.getProperty("user.home"));
		builder.append(File.separator);
		builder.append("Desktop");
		builder.append(File.separator);
		builder.append("Proyecto_n3");
		builder.append(File.separator);
		builder.append("BolsaCDSS");
		builder.append(File.separator);
		builder.append("cv");
		builder.append(File.separator);
		builder.append("cv_"+usua.getId()+".pdf");
		
		File convertFile = new File(builder.toString());
		convertFile.createNewFile();
		
		try (FileOutputStream fout = new FileOutputStream(convertFile)) {
			
			fout.write(file.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "{'msg':'Archivo cargado correctamente'}";
	}

}
