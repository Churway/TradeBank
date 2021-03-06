package com.churway.model;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author Churway
 * @create 2020/11/12
 * @since 1.0.0
 */

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    private Long id;
    private String account;
    private String password;
    private String nickname;
    private String realname;
    private String mobile;
    private String email;
    private String grpstr;
    private Date createtime;
    private Integer state;
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrpstr() {
        return grpstr;
    }

    public void setGrpstr(String grpstr) {
        this.grpstr = grpstr;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    //
}