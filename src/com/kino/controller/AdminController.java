package com.kino.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kino.database.DAO.Movie;
import com.kino.database.DAO.Seance;
import com.kino.database.DAO.User;
import com.kino.database.connector.SqliteDAO;

@Controller
public class AdminController {
	@Autowired
	private SqliteDAO sqliteDAO;


	@RequestMapping("/admin/admin_panel")
	public String viewAdminPanel(Map<String, Object> model) {
		return "admin/admin_panel";
	}

	@RequestMapping(value = "/admin/admin_movies", method = RequestMethod.GET)
	public String viewAdminMovies(Map<String, Object> model) {

		model.put("movies", sqliteDAO.getAllMovies());
		return "admin/admin_movies";
	}

	@RequestMapping(value = "/admin/admin_movies", method = RequestMethod.POST)
	public String adminMovieAction(@RequestParam String action, @RequestParam String title,
			@ModelAttribute("movieForm") Movie movie, Model model) {
		String result = "redirect:/admin/admin_movies.html";
		if (action.equals("Edytuj")) {
			result = "/admin/admin_movie_edit";
			model.addAttribute("movieForm", sqliteDAO.getMovieListByName(title).get(0));
			model.addAttribute("title", title);
			model.addAttribute("message",
					"Tytu³ jest identyfikatorem - zmiana spowoduje dodanie nowego filmu do bazy danych, lub zmianê istniej¹cego!");
		} else if (action.equals("Skasuj")) {
			sqliteDAO.deleteMovie(title);
		} else if (action.equals("Zapisz zmiany")) {
			sqliteDAO.insertOrReplaceMovie(movie);
		} else if (action.equals("Dodaj nowy")) {
			result = "/admin/admin_movie_edit";
			Movie newMovie = new Movie();
			model.addAttribute("movieForm", newMovie);
			model.addAttribute("title", "Nowy film");
		}
		return result;
	}

	// ADMIN SEANCES

	@RequestMapping(value = "/admin/admin_seances", method = RequestMethod.GET)
	public String viewAdminSeances(Map<String, Object> model) {
		model.put("seances", sqliteDAO.getAllSeances());
		return "admin/admin_seances";
	}

	@RequestMapping(value = "/admin/admin_seances", method = RequestMethod.POST)
	public String adminSeanceAction(@RequestParam String action, @RequestParam String ID,
			@ModelAttribute("seanceForm") Seance seance, Model model) {
		String result = "redirect:/admin/admin_seances.html";
		if (action.equals("Edytuj")) {
			result = "/admin/admin_seance_edit";
			model.addAttribute("movies", sqliteDAO.getAllMovies());
			model.addAttribute("prices", sqliteDAO.getAllPriceLists());
			model.addAttribute("seanceForm", sqliteDAO.getSeanceByID(ID).get(0));
			model.addAttribute("ID", ID);
			model.addAttribute("message",
					"ID jest identyfikatorem - zmiana spowoduje dodanie nowego seansu do bazy danych, lub zmianê istniej¹cego!");
		} else if (action.equals("Skasuj")) {
			sqliteDAO.deleteSeance(ID);
		} else if (action.equals("Zapisz zmiany")) {
			sqliteDAO.insertOrReplaceSeance(seance);
		} else if (action.equals("Dodaj nowy")) {
			model.addAttribute("movies", sqliteDAO.getAllMovies());
			model.addAttribute("prices", sqliteDAO.getAllPriceLists());
			result = "/admin/admin_seance_edit";
			Seance newSeance = new Seance();
			model.addAttribute("seanceForm", newSeance);
			model.addAttribute("ID", "Nowy seans");
		}
		return result;
	}

	// ADMIN_USERS

	@RequestMapping(value = "/admin/admin_users", method = RequestMethod.GET)
	public String viewAdminUsers(Map<String, Object> model) {
		model.put("users", sqliteDAO.getAllUsers());
		return "admin/admin_users";
	}

	@RequestMapping(value = "/admin/admin_users", method = RequestMethod.POST)
	public String adminUserAction(@RequestParam String action, @RequestParam String login,
			@ModelAttribute("userForm") User user, Model model) {
		String result = "redirect:/admin/admin_users.html";
		if (action.equals("Edytuj")) {
			result = "/admin/admin_user_edit";
			User newUser=sqliteDAO.getUserByLogin(login).get(0);
			newUser.setPassword("");
			model.addAttribute("userForm", newUser);
			model.addAttribute("login", login);
			model.addAttribute("message",
					"Login jest identyfikatorem - zmiana spowoduje dodanie nowego u¿ytkownika do bazy danych, lub zmianê istniej¹cego!");
		} else if (action.equals("Skasuj")) {
			sqliteDAO.deleteUser(login);
		} else if (action.equals("Zapisz zmiany")) {
			try{
				user.setPassword(sqliteDAO.getUserByLogin(user.getLogin()).get(0).getPassword());
			}catch(IndexOutOfBoundsException ex){
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(user.getPassword());			
				user.setPassword(hashedPassword);
			}
			sqliteDAO.insertOrReplaceUser(user);
		} else if (action.equals("Zmieñ has³o")) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(user.getPassword());			
			user.setPassword(hashedPassword);
			sqliteDAO.insertOrReplaceUser(user);
		} else if (action.equals("Dodaj nowy")) {
			result = "/admin/admin_user_edit";
			User newUser = new User();
			model.addAttribute("userForm", newUser);
			model.addAttribute("login", "Nowy u¿ytkownik");
		}
		return result;
	}

}
