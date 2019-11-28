package com.cdspool.main.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.ClaveTemporal;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IClaveTeRepository;
import com.cdspool.main.repository.UserRepository;
import com.cdspool.main.service.UserService;
import com.cdspool.main.shared.AmazonSES;
import com.cdspool.main.shared.UserDto;
import com.cdspool.main.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

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
		passwordResetTokenEntity.setUsuario(user);
		passwordResetTokenRepository.save(passwordResetTokenEntity);

		returnValue = new AmazonSES().sendPasswordResetRequest(user.getEmail(), token);
		return returnValue;
	}

	@Override
	public UserDto createUser(UserDto user) {
//		if (userRepository.findByEmail(user.getEmail()) != null)
//			throw new UserServiceException("Record already exists");
//
//		for(int i=0;i<user.getAddresses().size();i++)
//		{
//			AddressDTO address = user.getAddresses().get(i);
//			address.setUserDetails(user);
//			address.setAddressId(utils.generateAddressId(30));
//			user.getAddresses().set(i, address);
//		}
//		  
//		//BeanUtils.copyProperties(user, userEntity);
//		ModelMapper modelMapper = new ModelMapper();
//		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
//
//		String publicUserId = utils.generateUserId(30);
//		userEntity.setUserId(publicUserId);
//		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));
//
//		UserEntity storedUserDetails = userRepository.save(userEntity);
// 
//		//BeanUtils.copyProperties(storedUserDetails, returnValue);
//		UserDto returnValue  = modelMapper.map(storedUserDetails, UserDto.class);
//		
//        // Send an email message to user to verify their email address
//		amazonSES.verifyEmail(returnValue);

		return null;
	}

	@Override
	public UserDto getUser(String email) {
		Usuario userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public UserDto getUserByCredencial(String credencial) {
		UserDto returnValue = new UserDto();
		Usuario userEntity = userRepository.findByEmail(credencial);

		if (userEntity == null)
			throw new UsernameNotFoundException("User with ID: " + credencial + " not found");

		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public boolean verifyEmailToken(String token) {
//		boolean returnValue = false;
//
//        // Find user by token
//        Usuario userEntity = userRepository.findUsuarioByEmailVerificationToken(token);
//
//        if (userEntity != null) {
//            boolean hastokenExpired = Utils.hasTokenExpired(token);
//            if (!hastokenExpired) {
//                userEntity.setEmailVerificationToken(null);
//                userRepository.save(userEntity);
//                returnValue = true;
//            }
//        }

		return false;
	}
}