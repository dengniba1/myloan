package org.xun.loan.controller.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.xun.loan.dao.account.AccountRepository;
import org.xun.loan.dao.loan.LoanRepository;
import org.xun.loan.entity.account.Account;
import org.xun.loan.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.xun.loan.dao.loan.RequestInformationRepository;
import org.xun.loan.entity.loan.Loan;
import org.xun.loan.entity.loan.RequestInformation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liwenlong on 2017/6/21.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RequestInformationRepository requestInformationRepository;

    @RequestMapping("/inputProduct")
    public void inputProduct(@RequestBody Loan data) throws JsonProcessingException {
        RequestInformation requestInformation = data.getRequestInformation();
        requestInformation.setId(UUIDGenerator.getUUID());
        requestInformation = requestInformationRepository.save(requestInformation);
        data.setRequestInformation(requestInformation);
        data.setId(UUIDGenerator.getUUID());
        Account account = accountRepository.findOne("15601840101");
        data.setAccount(account);
        loanRepository.save(data);
    }
}
