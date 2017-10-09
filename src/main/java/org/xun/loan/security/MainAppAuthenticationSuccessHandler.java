package org.xun.loan.security;

import org.apache.log4j.Logger;
import org.xun.loan.dao.account.AccountRepository;
import org.xun.loan.entity.account.Account;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.xun.loan.dao.account.UserOptionRepository;
import org.xun.loan.util.Constants;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class MainAppAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static Logger LOGGER = Logger.getLogger(MainAppAuthenticationSuccessHandler.class);

	private static final String PUBLICINFOLOGIN = "greenwich/user/login.json";

	private RestTemplate restTemp;

	private AccountRepository accountRepository;

	private UserOptionRepository userOptionRepository;

//	@Autowired
//	private MessageService messageService;

	public MainAppAuthenticationSuccessHandler(AccountRepository accountRepository,
                                               UserOptionRepository userOptionRepository) {
		super();
		this.accountRepository = accountRepository;
		this.userOptionRepository = userOptionRepository;
		this.restTemp = new RestTemplate();
		SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemp.getRequestFactory();
		rf.setConnectTimeout(1000);
		rf.setReadTimeout(1000);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		response.setCharacterEncoding("utf-8");
		LOGGER.info("user authentication success");
		Account account = accountRepository.findOne(((User) authentication.getPrincipal()).getUsername());
		// 验证校验序列码
		HttpSession session = request.getSession();
		String verCode = (String) session.getAttribute("verCode");
		String inVerCode = request.getParameter("verifyCode");
		if(!verCode.equalsIgnoreCase(inVerCode)){
			authentication.setAuthenticated(false);
			return;
		}
		session(session, account);
		response.sendRedirect("/product/input_product");
//		response.getWriter().write("success");
	}

	private void session(HttpSession session, Account account) {
		String currentSessionId = session.getId();
		LOGGER.info("currentSessionId:" + currentSessionId);
		session.setAttribute(Constants.SESSIONUSERS, account);
	}

}
