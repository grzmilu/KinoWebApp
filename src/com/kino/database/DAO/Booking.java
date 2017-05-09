package com.kino.database.DAO;

public class Booking {
	private int ID;
	private String login;
	private int seanceID;
	private int seatID;
	private String code;
	boolean lowerPrice;
	boolean hasPaid;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	

	public int getSeanceID() {
		return seanceID;
	}

	public void setSeanceID(int seanceID) {
		this.seanceID = seanceID;
	}
	
	public int getSeatID() {
		return seatID;
	}

	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setLowerPrice(boolean lowerPrice){
		this.lowerPrice=lowerPrice;
	}
	
	public boolean getLowerPrice(){
		return this.lowerPrice;
	}
	
	public void setHasPaid(boolean hasPaid){
		this.hasPaid=hasPaid;
	}
	
	public boolean getHasPaid(){
		return this.hasPaid;
	}

}
