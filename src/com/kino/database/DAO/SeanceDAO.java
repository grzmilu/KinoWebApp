package com.kino.database.DAO;

import java.util.List;
import java.util.Map;

public interface SeanceDAO 
{
	void insertSeance (Seance seans);
	
	void deleteSeance (String czas);
	
	boolean canBook(String seanceID);
	
	List<Seance> getSeanceByTitle(String tytul);
	 
	List<Seance> getSeanceByID(String ID);
	
	List<Seance> getAllSeances();
	
	List<Seance> getFutureSeances();
	
	List<Seance> getSeanceListForDate(String date);
}
