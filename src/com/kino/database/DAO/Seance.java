package com.kino.database.DAO;

public class Seance {
	private String ID;
	private String startTime;
	private String roomNumber;
	private String title;
	private int priceID;

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public int getPriceID() {
		return priceID;
	}

	public void setPriceID(int priceID) {
		this.priceID = priceID;
	}

}
