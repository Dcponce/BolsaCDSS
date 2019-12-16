package com.cdspool.main.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdspool.main.model.Documento;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IUsuarioRepository;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "subir")
public class FileUploadController {

	@Autowired
	DocumentoController dc;

	@Autowired
	IUsuarioRepository iUsuarioRepository;

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, Principal principal,
			RedirectAttributes attributes) throws IOException {
		if (file == null || file.isEmpty()) {
			//attributes.addFlashAttribute("message", "Por favor seleccione un archivo");
			return "{'msg':'Por favor seleccione un archivo'}";
		}

		StringBuilder builder = new StringBuilder();
		builder.append(System.getProperty("user.home"));
		builder.append(File.separator);
		builder.append("Desktop");
		builder.append(File.separator);
		builder.append("Bolsa CDS");
		builder.append(File.separator);
		builder.append("cv");
		builder.append(File.separator);
		builder.append(file.getOriginalFilename());
		System.out.println("Esto trae: "+file.getOriginalFilename());
		byte[] fileBytes = file.getBytes();
		Path path = Paths.get(builder.toString());
		Files.write(path, fileBytes);

		
		//attributes.addFlashAttribute("message", "Archivo cargado correctamente");

		Documento d = new Documento();

		Usuario usua = iUsuarioRepository.findByEmail(principal.getName());

		d.setRuta(file.getOriginalFilename());
		d.setId_tipoDoc(dc.findById(1));
		d.setId_usuario(usua);
		dc.add(d);
		return "{'msg':'Archivo cargado correctamente'}";
	}

}
