package Assignment4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserManager {

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

	public void create(User user) {
		String sql = "insert into users (username,password,firstname,lastname,email,dateofbirth) values (?,?,?,?,?,?)";
		Connection connection = getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			statement.setDate(6, user.getDateOfBirth());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public List<User> readAllUsers() {
		List<User> users = new ArrayList<User>();
		String sql = "select * from user";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				String username = results.getString("username");
				String password = results.getString("password");
				String firstname = results.getString("firstname");
				String lastname = results.getString("lastname");
				Date dateofbirth = results.getDate("dateOfBirth");
				String email = results.getString("email");

				User user1 = new User(username, password, firstname, lastname,
						email, dateofbirth);
				users.add(user1);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return users;
	}

	public User readUser(String username) {

		User user = new User();
		String sql = "select * from user where username=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			if (results.next()) {

				String password = results.getString("password");
				String firstname = results.getString("firstname");
				String lastname = results.getString("lastname");
				Date dateofbirth = results.getDate("dateOfBirth");
				String email = results.getString("email");
				user = new User(username, password, firstname, lastname, email,
						dateofbirth);
			}
			results.close();
			results = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return user;

	}


	public void updateUser(String username, User user) {
		String sql = "update user set firstName=?,lastName=?,password=?,email=?,dateofbirth=? where username=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setDate(5, user.getDateOfBirth());
			statement.setString(6, username);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public void deleteUser(String username) {
		String sql = "delete from user where username=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

}
