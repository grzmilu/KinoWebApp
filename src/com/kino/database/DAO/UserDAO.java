package com.kino.database.DAO;

import java.util.List;
import java.util.Map;

public interface UserDAO 
{
	 void insertUser (User klient);
	 
	 void deleteUser (String login);
	 
	 List<User> getUserByLogin(String login);
	 
	 List<User> getAllUsers();
}
