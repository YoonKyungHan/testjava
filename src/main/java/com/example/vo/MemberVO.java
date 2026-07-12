package com.example.vo;

import java.util.Date;

/**
 * VO - 프로젝트 스타일
 * private 필드만 두고 getter/setter 로 접근
 */
public class MemberVO {

    private String po;           // 고유키 (pp2026-001 형태, 서버에서 채번)
    private String member_id;
    private String member_name;
    private String email;
    private String phone;
    private String age;
    private String gender;
    private String addr;
    private String dept_cd;
    private String use_yn;
    private Date   join_dt;

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDept_cd() {
        return dept_cd;
    }

    public void setDept_cd(String dept_cd) {
        this.dept_cd = dept_cd;
    }

    public String getUse_yn() {
        return use_yn;
    }

    public void setUse_yn(String use_yn) {
        this.use_yn = use_yn;
    }

    public Date getJoin_dt() {
        return join_dt;
    }

    public void setJoin_dt(Date join_dt) {
        this.join_dt = join_dt;
    }
}
