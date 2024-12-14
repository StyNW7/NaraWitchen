package entity;

import java.util.ArrayList;

import foods.Order;

public class User extends Entity {
	
	// User has attribute money to buy catering
	
	private Integer money;
	ArrayList<Order> orderList;

	public User(String name, String password, Integer money) {
		super(name, password);
		this.money = money;
		this.orderList = new ArrayList<>();
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}
	
	// Liskov Substitution Principle
	
	@Override
	public void introduction() {
		System.out.printf("Hi, %s\n", this.getName());
		System.out.printf("Money: %d\n", this.getMoney());
	}

}
