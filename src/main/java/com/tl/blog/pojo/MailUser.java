package com.tl.blog.pojo;

import java.util.Date;

/**
 * @author tl
 */
public class MailUser {
    private String mail;
    private Long num;
    private Date visitTime;

    public MailUser() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
}
