package com.kino.database.connector;

import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.kino.database.DAO.Booking;
import com.kino.database.DAO.BookingDAO;
import com.kino.database.DAO.Movie;
import com.kino.database.DAO.MovieDAO;
import com.kino.database.DAO.PriceList;
import com.kino.database.DAO.PriceListDAO;
import com.kino.database.DAO.Seance;
import com.kino.database.DAO.SeanceDAO;
import com.kino.database.DAO.Seat;
import com.kino.database.DAO.SeatDAO;
import com.kino.database.DAO.User;
import com.kino.database.DAO.UserDAO;

@Component("sqliteDAO")
public class SqliteDAO implements UserDAO, MovieDAO, SeanceDAO,PriceListDAO,SeatDAO,BookingDAO {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(datasource);
	}

	public void insertMovie(Movie film) {
		String sql = "insert into Movie (title,genre,releaseYear,description,direction,scenario,pegi,duration,poster,trailer) VALUES (:title, :genre, :releaseYear, :description, :direction, :scenario, :pegi, :duration, :poster,:trailer)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("title", film.getTitle());
		params.addValue("genre", film.getGenre());
		params.addValue("releaseYear", film.getReleaseYear());
		params.addValue("description", film.getDescription());
		params.addValue("direction", film.getDirection());
		params.addValue("scenario", film.getScenario());
		params.addValue("pegi", film.getPegi());
		params.addValue("duration", film.getDuration());
		params.addValue("poster", film.getPoster());
		params.addValue("trailer", film.getTrailer());
		jdbcTemplate.update(sql, params);
	}
	
	public void insertSeats(Seat seat) {
		String sql = "insert into Seats (ID,roomNumber,rowNumber,seatNumber) VALUES (:ID,:roomNumber,:rowNumber,:seatNumber)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("ID", seat.getID());
		params.addValue("roomNumber", seat.getRoomNumber());
		params.addValue("rowNumber", seat.getRowNumber());
		params.addValue("seatNumber", seat.getSeatNumber());
		jdbcTemplate.update(sql, params);
	}
	
	public void updateMovieByTitle(String title,Movie film){
		String sql = "update Movie SET genre=?,releaseYear=?,description=?,direction=?,scenario=?,pegi=?,duration=?,poster=?,trailer=? where title=?";
		jdbcTemplate.getJdbcOperations().update(sql, new Object[]{film.getGenre(),film.getReleaseYear(),film.getDescription(),film.getDirection(),film.getScenario(),film.getPegi(),film.getDuration(),film.getPoster(),film.getTrailer(),title});
	}
	
	public void insertOrReplaceMovie(Movie film){
		if(getMovieListByName(film.getTitle()).isEmpty()){
			insertMovie(film);
		}else{
			updateMovieByTitle(film.getTitle(), film);
		}
	}
	
	

	// public void insert(Movie film)
	// {
	//
	// String sql = "insert into Movie (tytul,gatunek,rok,opis) VALUES
	// (?,?,?,?)";
	// jdbcTemplate.update(sql, new
	// Object[]{film.getTitle(),film.getGatunek(),film.getRok(),film.getOpis()});
	// }

	public void deleteMovie(String title) {
		String sql = "delete from Movie where title=?";
		jdbcTemplate.getJdbcOperations().update(sql, title);
	}

	public void insertUser(User user) {
		String sql = "insert into User (login,phone,email, password, name, surname,authorities) VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.getJdbcOperations().update(sql, new Object[] { user.getLogin(), user.getPhone(), user.getEmail(),
				user.getPassword(), user.getName(), user.getSurname(),user.getAuthorities() });

	}

	public void deleteUser(String login) {
		String sql = "delete from User where login=?";
		int result = jdbcTemplate.getJdbcOperations().update(sql, login);
	}
	
	public void insertOrReplaceUser(User user){
		if(getUserByLogin(user.getLogin()).isEmpty()){
			insertUser(user);
		}else{
			updateUserByLogin(user.getLogin(), user);
		}
	}
	
	public void updateUserByLogin(String login,User user){
		String sql = "update User SET password=?,phone=?,email=?,name=?,surname=?,authorities=? where login=?";
		jdbcTemplate.getJdbcOperations().update(sql, new Object[]{user.getPassword(),user.getPhone(),user.getEmail(),user.getName(),user.getSurname(),user.getAuthorities(),login});
	}

	public void insertSeance(Seance seance) {
		String sql = "insert into Seance (start_time,roomNumber,title,priceID) VALUES (?,?,?,?)";
		jdbcTemplate.getJdbcOperations().update(sql,
				new Object[] {seance.getStartTime(),seance.getRoomNumber(), seance.getTitle(),seance.getPriceID()});

	}

	public void deleteSeance(String id) {
		String sql = "delete from Seance where id=?";
		int result = jdbcTemplate.getJdbcOperations().update(sql, id);
	}
	
	public void updateSeanceByID(String ID,Seance seans){
		String sql = "update Seance SET start_time=?,roomNumber=?,title=?,priceID=? where ID=?";
		jdbcTemplate.getJdbcOperations().update(sql, new Object[]{seans.getStartTime(),seans.getRoomNumber(),seans.getTitle(),seans.getPriceID(),ID});
	}
	
	public void insertOrReplaceSeance(Seance seans){
		if(getSeanceByID(seans.getID()).isEmpty()){
			insertSeance(seans);
		}else{
			updateSeanceByID(seans.getID(), seans);
		}
	}

	public void findMovieByName(String title) {
		String sql = "select * from Movie where title=? ";
		List<Map<String, Object>> list = jdbcTemplate.getJdbcOperations().queryForList(sql, title);
		for (Object li : list) {
			System.out.println(li.toString());
		}

	}

	public void findMovieByType(String genre) {
		String sql = "select * from Movie where genre=? ";
		List<Map<String, Object>> list = jdbcTemplate.getJdbcOperations().queryForList(sql, genre);
		for (Object li : list) {
			System.out.println(li.toString());
		}

	}

	private static final class MovieRowMapper implements RowMapper<Movie> {

		public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
			Movie movie = new Movie();
			movie.setGenre(rs.getString("genre"));
			movie.setTitle(rs.getString("title"));
			movie.setReleaseYear(rs.getString("releaseYear"));
			movie.setDescription(rs.getString("description"));
			movie.setDirection(rs.getString("direction"));
			movie.setScenario(rs.getString("scenario"));
			movie.setPegi(rs.getString("pegi"));
			movie.setDuration(rs.getString("duration"));
			movie.setPoster(rs.getString("poster"));
			movie.setTrailer(rs.getString("trailer"));
			return movie;
		}

	}

	private static final class SeanceRowMapper implements RowMapper<Seance> {

		public Seance mapRow(ResultSet rs, int rowNum) throws SQLException {
			Seance seance = new Seance();
			seance.setID(rs.getString("id"));
			seance.setStartTime(rs.getString("start_time"));
			seance.setRoomNumber(rs.getString("roomNumber"));
			seance.setTitle(rs.getString("title"));
			seance.setPriceID(rs.getInt("priceID"));
			return seance;
		}
	}
	
	private static final class StringRowMapper implements RowMapper<String>{
		public String mapRow(ResultSet rs,int rowNum) throws SQLException{
			return rs.getString(1);
		}
	}

	private static final class UserRowMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setEmail(rs.getString("email"));
			user.setLogin(rs.getString("login"));
			user.setPhone(rs.getString("phone"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setSurname(rs.getString("surname"));
			user.setAuthorities(rs.getString("authorities"));		
			return user;
		}

	}
	
	private static final class PriceListRowMapper implements RowMapper<PriceList>{

		public PriceList mapRow(ResultSet rs, int rowNum) throws SQLException {
			PriceList pl=new PriceList();
			pl.setId(rs.getInt("id"));
			pl.setName(rs.getString("name"));
			pl.setDescription(rs.getString("description"));
			pl.setLowerPrice(rs.getFloat("lowerPrice"));
			pl.setNormalPrice(rs.getFloat("normalPrice"));
			return pl;
		}
		
	}
	
	private static final class BookingRowMapper implements RowMapper<Booking>{

		public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
			Booking bk=new Booking();
			bk.setCode(rs.getString("code"));
			bk.setID(rs.getInt("id"));
			bk.setLogin(rs.getString("login"));
			bk.setSeanceID(rs.getInt("seanceID"));
			bk.setSeatID(rs.getInt("seatID"));
			bk.setLowerPrice(rs.getBoolean("lowerPrice"));
			bk.setHasPaid(rs.getBoolean("hasPaid"));
			return bk;
		}
		
	}
	
	private static final class SeatRowMapper implements RowMapper<Seat>{

		public Seat mapRow(ResultSet rs, int rowNum) throws SQLException {
			Seat s=new Seat();
			s.setID(rs.getInt("ID"));
			s.setRoomNumber(rs.getInt("roomNumber"));
			s.setRowNumber(rs.getInt("rowNumber"));
			s.setSeatNumber(rs.getInt("seatNumber"));
			try{
				s.setTaken(rs.getBoolean("isTaken"));
			}catch(SQLException ex){
				s.setTaken(false);
			}
			return s;
		}
		
	}

	public Map<String, Integer> getStatMovie() {
		String sql = "select title, count(*) as count from Movie group by title";

		return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {

			public Map<String, Integer> extractData(ResultSet rs) throws SQLException {
				Map<String, Integer> map = new TreeMap<String, Integer>();
				while (rs.next()) {
					String title = rs.getString("title");
					int count = rs.getInt("count");
					map.put(title, count);
					System.out.println("Tytul filmu:" + title);
				}
				return map;
			};

		});

	}

	public List<Movie> getMovieListByName(String title) {
		String sql = "select * from Movie where upper(title) like :title";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("title", "%" + title + "%");
		List<Movie> frm = jdbcTemplate.query(sql, params, new MovieRowMapper());

		for (Movie li : frm) {
			System.out.println("Tytul: " + li.getTitle());
		}
		return jdbcTemplate.query(sql, params, new MovieRowMapper());
	}

	public List<Movie> getAllMovies() {
		String sql = "select * from Movie order by title";
		return jdbcTemplate.query(sql, new MovieRowMapper());
	}
	
	

	public List<Seance> getSeanceByTitle(String title) {
		String sql = "select * from Seance where upper(title) like :title";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("title", "%" + title.toUpperCase() + "%");
		List<Seance> frm = jdbcTemplate.query(sql, params, new SeanceRowMapper());

		for (Seance seans : frm) {
			System.out.println(
					"Czas seansu " + seans.getTitle() + " w salie " + seans.getRoomNumber());
		}
		return jdbcTemplate.query(sql, params, new SeanceRowMapper());
	}
	
	public List<Seance> getSeanceByID(String ID) {
		String sql="select * from Seance where ID=:ID";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("ID", ID);
		return jdbcTemplate.query(sql, params, new SeanceRowMapper());
	}

	public List<Seance> getAllSeances() {
		String sql = "select * from Seance ";
		return jdbcTemplate.query(sql, new SeanceRowMapper());
	}

	public List<User> getUserByLogin(String login) {

		String sql = "select * from User where upper(login) like :login";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("login", login.toUpperCase());
		List<User> frm = jdbcTemplate.query(sql, params, new UserRowMapper());

		for (User user : frm) {
			System.out.println("Klient: " + user.getLogin() + " ma email: " + user.getEmail()
					+ " a numer telefoniczny " + user.getPhone());
		}
		return jdbcTemplate.query(sql, params, new UserRowMapper());
	}

	public List<User> getAllUsers() {

		String sql = "select * from User ";

		List<User> frm = jdbcTemplate.query(sql, new UserRowMapper());

		for (User klient : frm) {
			System.out.println("Klient: " + klient.getLogin() + " ma email: " + klient.getEmail()
					+ " a numer telefoniczny " + klient.getPhone());
		}
		return jdbcTemplate.query(sql, new UserRowMapper());
	}

	public List<Seance> getFutureSeances() {
		String sql = "select * from Seance where start_time>(datetime(CURRENT_TIMESTAMP, 'localtime','+1 hour')) order by start_time asc LIMIT 5";
		return jdbcTemplate.query(sql, new SeanceRowMapper());
	}
	
	public List<Seance> getFutureSeancesForTitle(String title) {
		String sql = "select * from Seance where start_time>(datetime(CURRENT_TIMESTAMP, 'localtime','+1 hour')) and title=:title order by start_time asc LIMIT 10";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("title", title);
		return jdbcTemplate.query(sql,params, new SeanceRowMapper());
	}

	@Override
	public List<Seat> getSeatListForRoomNumber(int roomNumber) {
		String sql = "select * from Seats where roomNumber=:roomNumber order by roomNumber asc,rowNumber asc,seatNumber asc";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("roomNumber", roomNumber);
		return jdbcTemplate.query(sql,params, new SeatRowMapper());
	}

	@Override
	public List<Booking> getBookingForSeanceID(int seanceID) {
		String sql = "select * from Booking where seanceID=:seanceID";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("seanceID", seanceID);
		return jdbcTemplate.query(sql,params, new BookingRowMapper());
	}

	@Override
	public List<Seat> getSeatListForSeance(int seanceID) {
		String sql = "select *,exists(select * from Booking as b where b.seatID=s.ID and b.seanceID=:seanceID) as \"isTaken\" from Seats as s where s.roomNumber=(select roomNumber from Seance where id=:seanceID) order by s.rowNumber asc,s.seatNumber asc";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("seanceID", seanceID);
		return jdbcTemplate.query(sql,params, new SeatRowMapper());
	}

	@Override
	public boolean canBook(int[] idArray, int seanceID) {
		String sql="select exists(select id from Booking where seanceID=:seanceID and seatID in (";
		for(int i=0;i<idArray.length;i++){
			if(i==idArray.length-1){
				sql=sql.concat(idArray[i]+"))");
			}else{
				sql=sql.concat(idArray[i]+", ");
			}
		}
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("seanceID", seanceID);
		return jdbcTemplate.queryForInt(sql, params)==0;
	}

	@Override
	public List<Seat> getSeatListForIDArray(int[] idArray) {
		String sql="select * from Seats where id in (";
		for(int i=0;i<idArray.length;i++){
			if(i==idArray.length-1){
				sql=sql.concat(idArray[i]+")");
			}else{
				sql=sql.concat(idArray[i]+", ");
			}
		}
		return jdbcTemplate.query(sql,new SeatRowMapper());
	}

	@Override
	public void insertBooking(Booking booking,String code) {
		String sql="insert into Booking (login,seanceID,seatID,code,lowerPrice,hasPaid) values (:login,:seanceID,:seatID,:code,:lowerPrice,:hasPaid)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("login", booking.getLogin());
		params.addValue("seanceID", booking.getSeanceID());
		params.addValue("seatID", booking.getSeatID());
		params.addValue("code", code);
		params.addValue("lowerPrice", booking.getLowerPrice());
		params.addValue("hasPaid", booking.getHasPaid());
		jdbcTemplate.update(sql, params);
	}

	
	@Override
	public boolean canBookList(List<Booking> bookingList) {
		String sql="select exists(select id from Booking where seanceID=:seanceID and seatID in (";
		for(int i=0;i<bookingList.size();i++){
			if(i==bookingList.size()-1){
				sql=sql.concat(bookingList.get(i).getSeatID()+"))");
			}else{
				sql=sql.concat(bookingList.get(i).getSeatID()+", ");
			}
		}
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("seanceID", bookingList.get(0).getSeanceID());
		return jdbcTemplate.queryForInt(sql, params)==0;
	}

	@Override
	public List<PriceList> getAllPriceLists() {
		String sql = "select * from Prices";
		return jdbcTemplate.query(sql,new PriceListRowMapper());
	}
	
	public List<String> getWeekList(){
		String sql="select date('now') union all select date('now','+1 day') union all select date('now','+2 day') union all select date('now','+3 day') union all select date('now','+4 day') union all select date('now','+5 day')";
		return jdbcTemplate.query(sql,new StringRowMapper());
	}

	@Override
	public List<Seance> getSeanceListForDate(String date) {
		String sql="select * from Seance where date(start_time)=\""+date+"\" and start_time>datetime(CURRENT_TIMESTAMP, 'localtime','+1 hour') order by start_time asc";
		return jdbcTemplate.query(sql,new SeanceRowMapper());
	}

	@Override
	public List<Booking> getBookingForUsername(String username) {
		String sql="select * from Booking where login='"+username+"'";
		return jdbcTemplate.query(sql,new BookingRowMapper());
	}

	@Override
	public List<Booking> getBookingForCode(String code) {
		String sql="select * from Booking where code='"+code+"'";
		return jdbcTemplate.query(sql,new BookingRowMapper());
	}

	@Override
	public List<String> getCodesListForUser(String username) {
		String sql="select distinct code from Booking where login='"+username+"'";
		return jdbcTemplate.query(sql,new StringRowMapper());
	}

	@Override
	public Seat getSeatForId(int id) {
		String sql="select * from Seats where id='"+id+"'";
		return jdbcTemplate.query(sql,new SeatRowMapper()).get(0);
	}

	@Override
	public void cancelBookingForCode(String code) {
		String sql = "delete from Booking where code='"+code+"'";
		jdbcTemplate.getJdbcOperations().update(sql);
	}

	@Override
	public boolean canBook(String seanceID) {
		String sql="select start_time>datetime(CURRENT_TIMESTAMP, 'localtime','+1 hour') from Seance where id=:seanceID";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("seanceID", seanceID);
		return jdbcTemplate.queryForInt(sql,params)==1;
	}
	
	
	
}
