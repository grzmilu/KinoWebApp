package com.kino.controller;

import java.util.ArrayList;
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

import com.kino.database.DAO.Booking;
import com.kino.database.DAO.Movie;
import com.kino.database.DAO.Seance;
import com.kino.database.DAO.Seat;
import com.kino.database.DAO.User;
import com.kino.database.connector.SqliteDAO;

@Controller
public class SeanceController {
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

	@RequestMapping(value = "/seance_detail", method = RequestMethod.GET)
	public String displayDetailGET(@RequestParam(value = "id", required = false) String id, Map<String, Object> model) {

		if (id != null) {
			if (sqliteDAO.canBook(id)) {
				try {
					Seance seance = sqliteDAO.getSeanceByID(id).get(0);
					String[] time = seance.getStartTime().split(" ");
					model.put("date", time[0]);
					model.put("time", time[1]);
					model.put("movie", sqliteDAO.getMovieListByName(seance.getTitle()).get(0));
					model.put("seance", seance);
				} catch (Exception ex) {
					model.put("message", "B³¹d atrybutu");
				}
			} else {
				return "redirect:/";
			}
		}
		return "seance_detail";
	}

	@RequestMapping(value = "/seances", method = RequestMethod.GET)
	public String displaySeancesGET(@RequestParam(value = "date", required = false) String date,
			Map<String, Object> model) {
		if (getPrincipal() != null) {
			model.put("user", getPrincipal());
		}
		model.put("days", sqliteDAO.getWeekList());
		model.put("seances", sqliteDAO.getSeanceListForDate(date));
		List<Seance> seances = sqliteDAO.getFutureSeances();
		if (!seances.isEmpty()) {
			model.put("future_seances", seances);
		}

		return "seances";
	}
}
