package com.cdspool.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdspool.main.model.Documento;
import com.cdspool.main.model.TipoDocumento;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IDocumentoRepository;
import com.cdspool.main.repository.ITipoDocRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class DocumentoService {
	
	@Autowired
	IDocumentoRepository iDoc;
	
	@Autowired
	ITipoDocRepository iTipo;
	
	@Autowired
	IUsuarioRepository iUser;
	
	
	public Documento findById(Integer id) {
		return iDoc.findById(id).get();
	}
	
	public void delete(Integer id) {
		iDoc.deleteById(id);
	}
	
	public void save (Documento doc) {
		iDoc.save(doc);
	}
	
	public List<TipoDocumento> findAllDoc(){
		
		List<TipoDocumento> list = (List<TipoDocumento>) iTipo.findAll();
		
		return list;
	}
	
	public TipoDocumento findByIdTipo(Integer id) {
		return iTipo.findById(id).get();
	}
	
	public Documento findByUsuario(Integer id) {
		Usuario user = iUser.findById(id).get();
		return iDoc.findByUsuario(user);
	}
	
}
