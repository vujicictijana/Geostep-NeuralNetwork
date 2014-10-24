package main;

import java.io.Serializable;

public class Clue implements Serializable {

	private static final long serialVersionUID = 3490519070573454457L;
	String lat;
	String lng;
	String radius;


	public Clue() {
		super();
	}

	public Clue(String lat, String lng, String radius) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.radius = radius;

	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}
}
