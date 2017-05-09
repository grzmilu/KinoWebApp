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

import com.kino.database.DAO.PriceList;
import com.kino.database.DAO.Seance;
import com.kino.database.DAO.User;
import com.kino.database.connector.SqliteDAO;

@Controller
public class PriceListController {
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
	
	
	
	@RequestMapping(value="/price_list", method = RequestMethod.GET)
	public String displayAll(@RequestParam(value="index",required=false)String index,Map<String, Object> model) {
		if (getPrincipal() != null) {
			model.put("user", getPrincipal());
		}	
		List<Seance> seances = sqliteDAO.getFutureSeances();
		if (!seances.isEmpty()) {
			model.put("seances", seances);
		}
		List<PriceList> prices=sqliteDAO.getAllPriceLists();
		model.put("priceList", prices);
		int id=0;
		try{
			id=Integer.parseInt(index);
			model.put("prices", "<p><h3>"+prices.get(id).getName()+"</h3></p><p>"+prices.get(id).getDescription()+"</p><p>Normalna cena: "+prices.get(id).getNormalPrice()+" z³</p><p>Ni¿sza cena: "+prices.get(id).getLowerPrice()+" z³</p>");
		}catch(Exception ex){
			model.put("prices", "B³êdny argument");
		}	
		return "/price_list";
	}
	
}
