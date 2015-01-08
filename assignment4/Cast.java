package Assignment4;

public class Cast {

	
	public Cast() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cast(String characterName, String actor, String movie) {
		super();
		this.characterName = characterName;
		this.actor = actor;
		this.movie = movie;
	}

	private String characterName;
	private String actor;
	private String movie;
	
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	
}
