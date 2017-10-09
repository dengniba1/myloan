package org.xun.loan.entity.account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by liwenlong on 2017/5/17.
 */
@Entity
public class Account{
    @Id
    private String phoneNumber;
    private String password;

    @OneToOne
    private CredentialsInformation credentialsInformation;
    @OneToOne
    private OfficerInformation officerInformation;

    public OfficerInformation getOfficerInformation() {
        return officerInformation;
    }

    public void setOfficerInformation(OfficerInformation officerInformation) {
        this.officerInformation = officerInformation;
    }

    public CredentialsInformation getCredentialsInformation() {
        return credentialsInformation;
    }

    public void setCredentialsInformation(CredentialsInformation credentialsInformation) {
        this.credentialsInformation = credentialsInformation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
