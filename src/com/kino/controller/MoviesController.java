package com.kino.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kino.database.DAO.Movie;
import com.kino.database.DAO.User;
import com.kino.database.connector.SqliteDAO;

@Controller
public class MoviesController {
	@Autowired
	
	private SqliteDAO sqliteDAO;
	
	private User getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();

		} else {
			userName = principal.toString();
		}
		List<User> user = sqliteDAO.getUserByLogin(userName);
		if (user.size() == 0) {
			return null;
		}
		return user.get(0);
	}
	
	@RequestMapping(value="/movie_detail", method = RequestMethod.GET)
	public String displayAll(@RequestParam("title") String title,Map<String, Object> model) {
		if (getPrincipal() != null) {
			model.put("user", getPrincipal());
		}		
		List<Movie> lista=sqliteDAO.getMovieListByName(title);
		if(!lista.isEmpty()){
			model.put("movie", lista.get(0));
			model.put("seances", sqliteDAO.getFutureSeancesForTitle(title));
		}
		
		return "/movie_detail";
	}
	
}
