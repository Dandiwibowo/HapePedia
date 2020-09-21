package com.entity;

public class Customers {
    private int id;
    private String username;
	private String password;
	private String fullname;
	private String gender;
	private String birth;
    private String address;
    private java.sql.Timestamp createDate;
    private Daerah daerah;

    public Customers(
        String username,
        String password,
        String fullname,
        String gender,
        String birth,
        String address,
        java.sql.Timestamp createDate,
        Daerah daerah
    ){
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.createDate = createDate;
        this.daerah = daerah;
    }

    public Customers(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createDate;
    }

    public void setCreatedDate(java.sql.Timestamp createDate) {
        this.createDate = createDate;
    }

    public Daerah getPostalCode() {
        return daerah;
    }

    public void setPostalCode(Daerah daerah) {
        this.daerah = daerah;
    }

    public Daerah getDaerah() {
        return daerah;
    }

    public void setDaerah(Daerah daerah) {
        this.daerah = daerah;
    }
    
}
