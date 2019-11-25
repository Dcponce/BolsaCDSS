package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Credencial;
import com.cdspool.main.model.OperationStatusModel;
import com.cdspool.main.model.RequestOperationStatus;
import com.cdspool.main.model.TipoUsuario;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.model.request.PasswordResetRequestModel;
import com.cdspool.main.service.UserService;
import com.cdspool.main.service.UsuarioService;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService uService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/lista")
	public List<Usuario> lista(){
		return uService.findAll();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		uService.delete(id);
	}
	
	@PostMapping()
	public void save(@RequestBody Usuario usu) {
		uService.save(usu);
	}
	
	@PutMapping
	public void update(@RequestBody Usuario usu) {
		uService.save(usu);
	}
	
	@GetMapping("api/listaTipo")
	public List<TipoUsuario> listaTipo(){
		return uService.findAllTipo();
	}
	
	@GetMapping("api/cred")
	public List<Credencial> findAllCred(){
		return uService.findAllCred();
	}

	// http://localhost:8080/usuarios/passwordReset
	@PostMapping(path = "",
		produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	
	public OperationStatusModel requestReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) {
		OperationStatusModel returnValue = new OperationStatusModel();
		
		boolean operationResult = userService.requestPasswordReset(passwordResetRequestModel.getEmail());
		
		returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		
		if (operationResult) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}
		return returnValue;
	}
}
