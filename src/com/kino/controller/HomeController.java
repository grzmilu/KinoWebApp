package com.kino.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.kino.database.DAO.Seance;
import com.kino.database.DAO.User;
import com.kino.database.connector.SqliteDAO;

@Controller
public class HomeController {
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		if (getPrincipal() != null) {
			model.addAttribute("user", getPrincipal());
		}

		List<Seance> seances = sqliteDAO.getFutureSeances();
		if (!seances.isEmpty()) {
			model.put("seances", seances);
			model.put("latest", sqliteDAO.getMovieListByName(seances.get(0).getTitle()).get(0));
		}

		return "index";
	}

	@RequestMapping("/welcome")
	public String displayAll(Map<String, Object> model) {
		if (getPrincipal() != null) {
			model.put("user", getPrincipal());
		}

		model.put("seances", sqliteDAO.getFutureSeances());
		model.put("movies", sqliteDAO.getAllMovies());
		String message = "Treœæ newsa ze Springa";
		model.put("message", message);
		return "welcome";
	}

}
