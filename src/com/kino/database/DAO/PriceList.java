package com.kino.database.DAO;

public class PriceList {
	private int id;
	private String name;
	private String description;
	private double normalPrice;
	private double lowerPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(double normalPrice) {
		this.normalPrice = normalPrice;
	}
	
	public double getLowerPrice() {
		return lowerPrice;
	}

	public void setLowerPrice(double lowerPrice) {
		this.lowerPrice = lowerPrice;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
