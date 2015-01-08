package Assignment4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActorManager {

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

	public void createActor(Actor newActor) {
		String sql = "insert into actor (id,firstname,lastname,dateofbirth) values (?,?,?,?,?,?)";
		Connection connection = getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newActor.getId());
			statement.setString(2, newActor.getFirstName());
			statement.setString(3, newActor.getLastName());
			statement.setDate(4, newActor.getDateOfBirth());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public List<Actor> readAllActors() {
		List<Actor> users = new ArrayList<Actor>();
		String sql = "select * from actor";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				String id = results.getString("id");
				String firstname = results.getString("firstname");
				String lastname = results.getString("lastname");
				Date dateofbirth = results.getDate("dateOfBirth");
				Actor actor = new Actor(id, firstname, lastname, dateofbirth);
				users.add(actor);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return users;
	}

	public Actor readActor(String actorId) {
		Actor actor = new Actor();
		String sql = "select * from actor where id=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				String id = results.getString("id");
				String firstname = results.getString("firstname");
				String lastname = results.getString("lastname");
				Date dateofbirth = results.getDate("dateOfBirth");
				actor = new Actor(id, firstname, lastname, dateofbirth);
			}
			results.close();
			results = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return actor;

	}

	public void updateActor(String actorId, Actor actor) {
		String sql = "update actor set id=?,firstname=?,lastname=?,dateOfBirth=? where id=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, actor.getId());
			statement.setString(2, actor.getFirstName());
			statement.setString(3, actor.getLastName());
			statement.setDate(4, actor.getDateOfBirth());
			statement.setString(5, actorId);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public void deleteActor(String actorId) {
		String sql = "delete from actor where id=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

}
