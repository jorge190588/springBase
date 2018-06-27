package com.student.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.student.entities.Users;
import com.student.tools.RestResponse;
import com.student.ws.UserRestClient;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private UserRestClient userRestClient = new UserRestClient();
	
	@Override
	public Authentication authenticate(Authentication authentication)  throws AuthenticationException {
		String 	name 		= authentication.getName(),
				password 	= authentication.getCredentials().toString();
	    
	    userRestClient.findBy("name", name);
		RestResponse response = userRestClient.getResponse() ;
		List<Users> userlist = (List<Users>) response.get_data();
		if (userlist.size()==0) throw new BadCredentialsException("Usuario y clave incorrectos");
		Users user = new Users(userlist.get(0));
		
		if (user.getIsEnabled()==false)throw new BadCredentialsException("Usuario inactivo");
		
        if (name.equals(user.getName()) && BCrypt.checkpw(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
        } else {
        	throw new BadCredentialsException("Usuario y clave incorrectos");
        }

	 }

	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
