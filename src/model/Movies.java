package model;

public class Movies extends Entertainment {

	private String director;
	private String format;
	private String movieID;

	public Movies(int yearOfRelease, String title, String genre, double price, String director, String format) {
		super(yearOfRelease, title, genre, price, director);
		// TODO Auto-generated constructor stub

		this.director = director;
		this.format  =format;
	}
	public Movies(int yearOfRelease, String title, String genre, double price, String director, String format, String movieID) {
		super(yearOfRelease, title, genre, price, director);
		
		this.movieID = movieID;
		this.director = director;
		this.format  =format;
		
	}

	public String getMovieID() {
		return movieID;
	}
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

}
