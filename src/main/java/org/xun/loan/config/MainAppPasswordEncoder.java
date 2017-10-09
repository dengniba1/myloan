package org.xun.loan.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.xun.loan.util.MD5Util;

public class MainAppPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		return MD5Util.getMD5(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if(encodedPassword==null){
			return false;
		}
		return encodedPassword.equals(MD5Util.getMD5(rawPassword.toString()));
	}

}
