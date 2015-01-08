package Assignment4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CastManager {

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

	void createCast(Cast newCast) {
		String sql = "insert into cast (charactername,actor,movie) values (?,?,?)";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setString(2, newCast.getActor());
			statement.setString(3, newCast.getMovie());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public List<Cast> readAllCast() {
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "select * from cast";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				String charactername = results.getString("charactername");
				String actor = results.getString("actor");
				String movie = results.getString("movie");

				Cast cast = new Cast(charactername, actor, movie);
				casts.add(cast);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return casts;
	}

	public List<Cast> readAllCastForActor(String actorId) {
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "select * from cast where actor=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				String charactername = results.getString("charactername");
				String actor = results.getString("actor");
				String movie = results.getString("movie");

				Cast cast = new Cast(charactername, actor, movie);
				casts.add(cast);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return casts;
	}

	public List<Cast> readAllCastForMovie(String movieId) {
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "select * from cast where movie=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				String charactername = results.getString("charactername");
				String actor = results.getString("actor");
				String movie = results.getString("movie");
				Cast cast = new Cast(charactername, actor, movie);
				casts.add(cast);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return casts;
	}

	public Cast readCastForId(String castId) {
		Cast cast = null;
		String sql = "select * from cast where castid=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, castId);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				String charactername = results.getString("charactername");
				String actor = results.getString("actor");
				String movie = results.getString("movie");
				cast = new Cast(charactername, actor, movie);

			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return cast;
	}

	public void updateCast(String castId, Cast newCast) {
		String sql = "update cast set charactername=?,actor=?,movie=? where castid=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setString(2, newCast.getActor());
			statement.setString(3, newCast.getMovie());
			statement.setString(4, castId);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public void deleteCast(String castId) {
		String sql = "delete from cast where castid=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, castId);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

}
