package io.bittiger.ads.entity;

public class Campaign {

	private String id;
	private String name;
	private double budget;
	
	public Campaign() {
		this.id = "";
		this.name = "";
		this.budget = 0;
	}
	
	public Campaign(String id, String name, double budget) {
		super();
		this.id = id;
		this.name = name;
		this.budget = budget;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBudget() {
		return budget;
	}

	public void modifyBudget(double budget) {
		this.budget += budget;
	}
	
	@Override
	public String toString() {
		return "Campaign " + this.id + " : name : " + this.name + ", budget: " + this.budget;
	}
	
	
}
