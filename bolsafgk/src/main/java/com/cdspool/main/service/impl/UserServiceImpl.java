package com.cdspool.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IUsuarioRepository;
import com.cdspool.main.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	IUsuarioRepository userRepository;
	
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
		
//		String token = Utils.generatePasswordResetToken(user.getId());
//		PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
//		passwordResetTokenEntity.setToken(token);
//		passwordResetTokenEntity.setUserDetails(user);
//		passwordResetTokenRepository.save(passwordResetTokenEntity);
//		
//		returnValue = new AmazonSES().sendPasswordResetRequest(
//				user.getEmail(),
//				token);
		return returnValue;
	}

}
