package com.xier.sesame.attence.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xxu on 2017/4/14.
 */
public class OffLinePasswordDto implements Serializable {

    //Offline password id
    private Long offLinePasswordId;

    //创建时间
    private Date gmtCreate;

    //修改时间
    private Date gmtModify;

    //org id
    private Long orgId;

    //password
    private String password;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Long getOffLinePasswordId() {
        return offLinePasswordId;
    }

    public void setOffLinePasswordId(Long offLinePasswordId) {
        this.offLinePasswordId = offLinePasswordId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
