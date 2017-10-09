package org.xun.loan.entity.account;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by liwenlong on 2017/5/18.
 */
@Entity
public class CredentialsInformation {
    @Id
    private String id;
    private String idFront;//身份证正面
    private String idBack;//身份证反面
    private String workCard;//工作证
    private String businessCard;//名片
    private String incumbencyCertificate;//在职3个月证明

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFront() {
        return idFront;
    }

    public void setIdFront(String idFront) {
        this.idFront = idFront;
    }

    public String getIdBack() {
        return idBack;
    }

    public void setIdBack(String idBack) {
        this.idBack = idBack;
    }

    public String getWorkCard() {
        return workCard;
    }

    public void setWorkCard(String workCard) {
        this.workCard = workCard;
    }

    public String getBusinessCard() {
        return businessCard;
    }

    public void setBusinessCard(String businessCard) {
        this.businessCard = businessCard;
    }

    public String getIncumbencyCertificate() {
        return incumbencyCertificate;
    }

    public void setIncumbencyCertificate(String incumbencyCertificate) {
        this.incumbencyCertificate = incumbencyCertificate;
    }
}
