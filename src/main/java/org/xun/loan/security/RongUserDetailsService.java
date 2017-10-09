package org.xun.loan.security;

import org.xun.loan.dao.account.AccountRepository;
import org.xun.loan.entity.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class RongUserDetailsService implements UserDetailsService {

    private String userRole = "ROLE_USER";

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = accountRepository.findOne(username);
        return new User(account.getPhoneNumber(), account.getPassword(), Arrays.asList(new SimpleGrantedAuthority(userRole)));
    }


}
