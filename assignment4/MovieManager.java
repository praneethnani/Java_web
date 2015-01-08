package Assignment4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MovieManager {

	public static Connection getConnection() {
		InitialContext ic;
		try {
			ic = new InitialContext();
			DataSource ds = (DataSource) ic
					.lookup("java:comp/env/jdbc/assignment4");
			Connection c = ds.getConnection();
			return c;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createMovie(Movie newMovie) {
		String sql = "insert into movie (id,title,posterimage,releaseDate) values (?,?,?,?)";
		Connection connection = getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newMovie.getId());
			statement.setString(2, newMovie.getTitle());
			statement.setString(3, newMovie.getPosterImage());
			statement.setDate(4, newMovie.getReleaseDate());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public List<Movie> readAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "select * from movie";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				String id = results.getString("id");
				String title = results.getString("title");
				String posterimage = results.getString("posterimage");
				Date releasedate = results.getDate("releasedate");

				Movie movie = new Movie(id, title, posterimage, releasedate);
				movies.add(movie);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return movies;
	}

	public Movie readMovie(String movieId) {

		Movie movie = new Movie();
		String sql = "select * from movie where id=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			if (results.next()) {

				String id = results.getString("id");
				String title = results.getString("title");
				String posterimage = results.getString("posterimage");
				Date releasedate = results.getDate("releasedate");
				movie = new Movie(id, title, posterimage, releasedate);
			}
			results.close();
			results = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return movie;

	}

	public void updateMovie(String movieId, Movie movie) {
		String sql = "update movie set title=?,posterimage=?,releasedate=? where id=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			statement.setDate(3, movie.getReleaseDate());
			statement.setString(4, movieId);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public void deleteMovie(String movieId) {
		String sql = "delete from movie where id=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

}
