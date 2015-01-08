package Assignment4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CommentManager {

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

	public void createComment(Comment newComment) {
		String sql = "insert into comment (comment,date,username,movieid) values (?,?,?,?)";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment.getComment());
			statement.setDate(2, newComment.getDate());
			statement.setString(3, newComment.getUser());
			statement.setString(4, newComment.getMovie());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public List<Comment> readAllComments() {
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				String comment = results.getString("comment");
				Date date = results.getDate("date");
				String username = results.getString("username");
				String movieid = results.getString("movieid");
				Comment comment1 = new Comment(comment, date, username, movieid);
				comments.add(comment1);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return comments;
	}

	public List<Comment> readAllCommentsForUsername(String username) {
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment where user=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				String comment = results.getString("comment");
				Date date = results.getDate("date");
				String movieid = results.getString("movieid");
				Comment comment1 = new Comment(comment, date, username, movieid);
				comments.add(comment1);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return comments;
	}

	public List<Comment> readAllCommentsForMovie(String movieId) {
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment where movie=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				String comment = results.getString("comment");
				Date date = results.getDate("date");
				String username = results.getString("username");
				Comment comment1 = new Comment(comment, date, username, movieId);
				comments.add(comment1);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return comments;
	}

	public Comment readCommentForId(String commentId) {
		Comment comment = null;
		String sql = "select * from comment where movie=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				String comments = results.getString("comment");
				Date date = results.getDate("date");
				String username = results.getString("username");
				String movieid = results.getString("movieid");
				comment = new Comment(comments, date, username, movieid);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return comment;

	}

	public void updateComment(String commentId, String newComment) {
		String sql = "update comment set comment=? where commentid=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment);
			statement.setString(2, commentId);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public void deleteComment(String commentId) {
		String sql = "delete from comment where commentid=?";
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

}
