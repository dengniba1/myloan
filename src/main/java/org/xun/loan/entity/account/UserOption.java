package org.xun.loan.entity.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

/**
 * Created by liwenlong on 2017/5/26.
 */
@Entity
public class UserOption {
    @Id
    @GeneratedValue
    private long id;
    private long phoneNumber;
    private Instant optTime;
    private String ipAdress;
    private String sessionId;
    private String optType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getOptTime() {
        return optTime;
    }

    public void setOptTime(Instant optTime) {
        this.optTime = optTime;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }
}
