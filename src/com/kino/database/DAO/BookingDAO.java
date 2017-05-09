package com.kino.database.DAO;

import java.awt.Point;
import java.util.List;

public interface BookingDAO 
{
	List<Booking> getBookingForSeanceID(int seanceID);
	boolean canBook(int[] idArray,int seanceID);
	boolean canBookList(List<Booking> bookingList);
	void insertBooking(Booking booking,String code);
	List<Booking> getBookingForUsername(String username);
	List<Booking> getBookingForCode(String code);
	List<String> getCodesListForUser(String code);
	void cancelBookingForCode(String code);
}
