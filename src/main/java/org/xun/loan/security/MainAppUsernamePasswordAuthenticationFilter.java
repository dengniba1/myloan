package org.xun.loan.security;

import org.apache.log4j.Logger;
import org.xun.loan.dao.account.AccountRepository;
import org.xun.loan.entity.account.Account;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.xun.loan.util.ApplicationContextUtil;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;


public class MainAppUsernamePasswordAuthenticationFilter extends
     UsernamePasswordAuthenticationFilter {

	private static final Logger LOGGERS = Logger.getLogger(MainAppUsernamePasswordAuthenticationFilter.class);
	private AccountRepository accountRepository;
	public MainAppUsernamePasswordAuthenticationFilter(){
		accountRepository= ApplicationContextUtil.getBean(AccountRepository.class);
	}
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		try {
			return super.obtainPassword(request);
//			return RSAUtils.decodeJsValue(super.obtainPassword(request));
		} catch (Exception e) {
			LOGGERS.error("",e);
		}
		return null;
	}

	@Override
    protected String obtainUsername(HttpServletRequest request) {
		try {
			String username=URLDecoder.decode(super.obtainUsername(request), "UTF-8");
			if (StringUtils.isEmpty(username)) {
				throw new UsernameNotFoundException(username);
			}
			Account account = accountRepository.findOne(username);
			if(Objects.isNull(account)){//不存在此用户或者该用户账号被禁用
				throw new UsernameNotFoundException(username);
			}
			return username;
		} catch (UnsupportedEncodingException e) {
			LOGGERS.error("obtainUsername exception:", e);
			return null;
		}
    }

//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
//		return super.attemptAuthentication(request, response);
//	}

}
