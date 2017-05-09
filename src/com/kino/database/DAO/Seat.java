package com.kino.database.DAO;

public class Seat {
	private int ID;
	private int roomNumber;
	private int rowNumber;
	private int seatNumber;
	private boolean taken;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	public void setTaken(boolean taken){
		this.taken=taken;
	}
	
	public boolean isTaken(){
		return this.taken;
	}

}
