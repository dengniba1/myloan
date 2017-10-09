package org.xun.loan.controller.account;

import org.xun.loan.dao.account.AccountRepository;
import org.xun.loan.entity.account.Account;
import org.xun.loan.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by liwenlong on 2017/6/19.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/getAccountPhoto/{phoneNo}")
    public String getAccountPhoto(@PathVariable String phoneNo, HttpSession session){
        Account user = (Account) session.getAttribute(Constants.SESSIONUSERS);
        Account account = accountRepository.findOne(user.getPhoneNumber());
        return account.getOfficerInformation().getPhoto();
    }
}
