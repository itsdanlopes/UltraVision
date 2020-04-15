package model;

public class LiveConcert extends Entertainment {

	protected String band;
	protected String format;

	public LiveConcert(int yearOfRelease, String title, String genre, double price, String band, String format) {
		super(yearOfRelease, title, genre, price, band);
		// TODO Auto-generated constructor stub

		this.band = band;
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

}
