package com.entity;

public class Admin {
	
	private int id;
	private String nama;
	private String username;
	private String password;
	private String address;
	private String position;
	private Daerah daerah;
	
	
	public Admin() {

	}

	public Admin(String nama, String username, String password, String address, String position, Daerah daerah){
		
		this.nama = nama;
		this.username = username;
		this.password = password;
		this.address = address;
		this.position = position;
		this.daerah = daerah;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Daerah getDaerah() {
		return daerah;
	}
	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}
	
	
}
