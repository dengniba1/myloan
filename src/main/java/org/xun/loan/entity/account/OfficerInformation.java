package org.xun.loan.entity.account;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by liwenlong on 2017/5/21.
 */
@Entity
public class OfficerInformation {

    @Id
    @GenericGenerator(strategy="uuid", name = "id")
    private String id;
    private String name;
    private String photo;//照片
    private String company;//所属机构名称
    private double previousExperience;//从业经验年数
    private String companyAdress;
    private String region;
    private double loginCount;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPreviousExperience() {
        return previousExperience;
    }

    public void setPreviousExperience(double previousExperience) {
        this.previousExperience = previousExperience;
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(double loginCount) {
        this.loginCount = loginCount;
    }

}
