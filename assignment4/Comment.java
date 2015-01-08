package Assignment4;

import java.sql.Date;

public class Comment {
	private String comment;
	private Date date;

	private String user;
	private String movie;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String comment, Date date, String user,String movie) {
		super();
		this.comment = comment;
		this.date = date;
		this.user = user;
		this.movie=movie;
	}

	public void setComment(String comment){
		this.comment=comment;
	}
	public String getComment(){
		return this.comment;
	}
	public void setDate(Date date){
		this.date=date;
	}
	public Date getDate(){
		return this.date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

}
