package org.xun.loan.entity.loan;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by liwenlong on 2017/5/21.
 */
@Entity
public class RequestInformation {
    @Id
    private String id;
    private int minAge;
    private int maxAge;
    private boolean isRequireID;//身份证
    private boolean isRequirePremisesPermit;//房产证
    private boolean isRequireQCCQZ;//汽车产权证
    private boolean isRequireMarriageCertificate;//结婚证
    private boolean isRequireDivorceCertificate;//离婚证
    private boolean isRequireProofOfIncome;//收入证明
    private boolean isRequireContinual6Month;//6个月打卡工资银行流水
    private boolean isRequirePolicy;//保单
    private boolean isRequirePreSaleContract;//预售合同
    private boolean isRequireTax;//税单
    private int minLoanDay;
    private int maxLoanDay;
    private boolean needFund;//公积金有无要求
    private boolean needSociaIlnsurance;//社保有无要求
    private String notes;//注意事项
    private String characteristic;//特点

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public boolean isRequireID() {
        return isRequireID;
    }

    public void setRequireID(boolean requireID) {
        isRequireID = requireID;
    }

    public boolean isRequirePremisesPermit() {
        return isRequirePremisesPermit;
    }

    public void setRequirePremisesPermit(boolean requirePremisesPermit) {
        isRequirePremisesPermit = requirePremisesPermit;
    }

    public boolean isRequireQCCQZ() {
        return isRequireQCCQZ;
    }

    public void setRequireQCCQZ(boolean requireQCCQZ) {
        isRequireQCCQZ = requireQCCQZ;
    }

    public boolean isRequireMarriageCertificate() {
        return isRequireMarriageCertificate;
    }

    public void setRequireMarriageCertificate(boolean requireMarriageCertificate) {
        isRequireMarriageCertificate = requireMarriageCertificate;
    }

    public boolean isRequireDivorceCertificate() {
        return isRequireDivorceCertificate;
    }

    public void setRequireDivorceCertificate(boolean requireDivorceCertificate) {
        isRequireDivorceCertificate = requireDivorceCertificate;
    }

    public boolean isRequireProofOfIncome() {
        return isRequireProofOfIncome;
    }

    public void setRequireProofOfIncome(boolean requireProofOfIncome) {
        isRequireProofOfIncome = requireProofOfIncome;
    }

    public boolean isRequireContinual6Month() {
        return isRequireContinual6Month;
    }

    public void setRequireContinual6Month(boolean requireContinual6Month) {
        isRequireContinual6Month = requireContinual6Month;
    }

    public boolean isRequirePolicy() {
        return isRequirePolicy;
    }

    public void setRequirePolicy(boolean requirePolicy) {
        isRequirePolicy = requirePolicy;
    }

    public boolean isRequirePreSaleContract() {
        return isRequirePreSaleContract;
    }

    public void setRequirePreSaleContract(boolean requirePreSaleContract) {
        isRequirePreSaleContract = requirePreSaleContract;
    }

    public boolean isRequireTax() {
        return isRequireTax;
    }

    public void setRequireTax(boolean requireTax) {
        isRequireTax = requireTax;
    }

    public int getMinLoanDay() {
        return minLoanDay;
    }

    public void setMinLoanDay(int minLoanDay) {
        this.minLoanDay = minLoanDay;
    }

    public int getMaxLoanDay() {
        return maxLoanDay;
    }

    public void setMaxLoanDay(int maxLoanDay) {
        this.maxLoanDay = maxLoanDay;
    }

    public boolean isNeedFund() {
        return needFund;
    }

    public void setNeedFund(boolean needFund) {
        this.needFund = needFund;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public boolean isNeedSociaIlnsurance() {
        return needSociaIlnsurance;
    }

    public void setNeedSociaIlnsurance(boolean needSociaIlnsurance) {
        this.needSociaIlnsurance = needSociaIlnsurance;
    }
}
