package org.xun.loan.entity.loan;


import org.xun.loan.entity.account.Account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by liwenlong on 2017/5/18.
 */
@Entity
public class Loan {
    @Id
    private String id;
    private String name;
    @ManyToOne
    private Account account;
    private double minRate;
    private double maxRate;
    private double loanLimit;
    @OneToOne
    private RequestInformation requestInformation;

    public RequestInformation getRequestInformation() {
        return requestInformation;
    }

    public void setRequestInformation(RequestInformation requestInformation) {
        this.requestInformation = requestInformation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getMinRate() {
        return minRate;
    }

    public void setMinRate(double minRate) {
        this.minRate = minRate;
    }

    public double getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(double maxRate) {
        this.maxRate = maxRate;
    }

    public double getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(double loanLimit) {
        this.loanLimit = loanLimit;
    }
}
