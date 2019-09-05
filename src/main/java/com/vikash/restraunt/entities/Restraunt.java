package com.vikash.restraunt.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Restraunts")
public class Restraunt {
	
	@Id
	private int id;
	private String city;
	private int ranking;
	private double rating;
	private int noOfReviews;
	private String name;
	private List<String> cuisineStyle;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getNoOfReviews() {
		return noOfReviews;
	}

	public void setNoOfReviews(int noOfReviews) {
		this.noOfReviews = noOfReviews;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCuisineStyle() {
		return cuisineStyle;
	}

	public void setCuisineStyle(List<String> cuisineStyle) {
		this.cuisineStyle = cuisineStyle;
	}

	@Override
	public String toString() {
		return "Restraunt [id=" + id + ", city=" + city + ", ranking=" + ranking + ", rating=" + rating
				+ ", noOfReviews=" + noOfReviews + ", name=" + name + ", cuisineStyle=" + cuisineStyle + "]";
	}

}
