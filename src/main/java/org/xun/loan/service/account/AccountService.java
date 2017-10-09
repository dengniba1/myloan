package org.xun.loan.service.account;

import org.xun.loan.dao.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liwenlong on 2017/5/18.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

}
