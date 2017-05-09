package com.kino.database.DAO;

import java.util.List;
import java.util.Map;



public interface MovieDAO 
{
	void insertMovie (Movie film);
	
	void deleteMovie (String tytul);
	
	void findMovieByName (String tytul);
	
	void findMovieByType (String gatunek);
	
	List<Movie> getMovieListByName(String tytul);
	
	public Map<String, Integer> getStatMovie();
	
	public List getAllMovies();
	
}
