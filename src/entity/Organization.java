package entity;

import java.util.ArrayList;

import foods.Order;

public class Organization extends Entity {
	
	// Organization has attribute money and discount to buy catering
	
	private Integer money;
	private Double discount;
	ArrayList<Order> orderList;
	
	public Organization(String name, String password, Integer money, Double discount) {
		super(name, password);
		this.money = money;
		this.discount = discount;
		this.orderList = new ArrayList<>();
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
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
		System.out.printf("\nOrganization: %s\n", this.getName());
		System.out.printf("Money: %d\n", this.getMoney());
		System.out.printf("Discount: %.2f\n\n", this.getDiscount());
	}

}
