package com.cdspool.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.ClaveTemporal;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IClaveTeRepository;
import com.cdspool.main.repository.IUsuarioRepository;
import com.cdspool.main.service.UserService;
import com.cdspool.main.shared.AmazonSES;
import com.cdspool.main.shared.Utils;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	IUsuarioRepository userRepository;
	
	@Autowired
	IClaveTeRepository passwordResetTokenRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean requestPasswordReset(String email) {
		boolean returnValue = false;
		
		Usuario user = userRepository.findByEmail(email);
		
		if (user == null) {
			return returnValue;
		}
		 
		String token = new Utils().generatePasswordResetToken(user.getId_credencial().getCodigo());
		ClaveTemporal passwordResetTokenEntity = new ClaveTemporal();
		passwordResetTokenEntity.setClavet(token);
		passwordResetTokenEntity.setUsuario(user);;
		passwordResetTokenRepository.save(passwordResetTokenEntity);
		
		returnValue = new AmazonSES().sendPasswordResetRequest(
				user.getEmail(),
				token);
		return returnValue;
	}
}