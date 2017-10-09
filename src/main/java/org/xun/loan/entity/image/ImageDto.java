package org.xun.loan.entity.image;


import org.xun.loan.entity.account.Account;

import java.sql.Timestamp;

/**
 * Created by liwenlong on 2017/6/6.
 */
public class ImageDto {

    private short year;
    private short mount;
    private short day;
    private Account userDto;
    private String name;
    private String fileName;
    private String url;
    private String previewUrl;
    private Timestamp time;
    private short width;
    private short height;
    private String clientIp;
    private String clientAgent;
    private short clientType;
    private short status;
    private String ext3;
    private String ext4;

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public short getMount() {
        return mount;
    }

    public void setMount(short mount) {
        this.mount = mount;
    }

    public short getDay() {
        return day;
    }

    public void setDay(short day) {
        this.day = day;
    }

    public Account getUserDto() {
        return userDto;
    }

    public void setUserDto(Account userDto) {
        this.userDto = userDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public short getWidth() {
        return width;
    }

    public void setWidth(short width) {
        this.width = width;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientAgent() {
        return clientAgent;
    }

    public void setClientAgent(String clientAgent) {
        this.clientAgent = clientAgent;
    }

    public short getClientType() {
        return clientType;
    }

    public void setClientType(short clientType) {
        this.clientType = clientType;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }
}
