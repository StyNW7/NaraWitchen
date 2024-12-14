package entity;

import java.util.ArrayList;

import foods.Food;

public class Admin extends Entity {
	
	ArrayList<Food> foodList;
	
	// Only 1 single admin

	public Admin() {
		super("Admin", "Admin123");
		this.foodList = new ArrayList<>();
	}
	
	// Liskov Substitution Principle
	
	@Override
	public void introduction() {
		System.out.printf("Hi, %s\n", this.getName());
	}

	public ArrayList<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
	}
	
}
