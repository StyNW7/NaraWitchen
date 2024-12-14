package dao;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Admin;
import entity.Entity;
import entity.User;
import foods.Food;
import foods.Order;
import foods.Regular;
import interfaces.RegularMenu;
import utility.Printing;

public class UserController implements RegularMenu {
	
	Scanner scan = new Scanner(System.in);
	Printing print = new Printing();
	
	Admin admin;
	
	ArrayList<Food> menuList = new ArrayList<>();
	
	User activeUser;
	
	public void UserMenu(Admin activeAdmin, Entity user) {
		
		admin = activeAdmin;
		activeUser = (User) user;
		
		if (!init()) return;
		
		int action;
		
		do {
			
			activeUser.introduction();
			printRegularMenu();
			
			System.out.println("\n=== User NaraWitchen ===\n");
			
			System.out.println("1. Buy Food");
			System.out.println("2. See Order");
			System.out.println("0. Logout");
			
			System.out.print(">> ");
			action = scan.nextInt();
			scan.nextLine();
			
			if (action == 1) buyFood();
			else if (action == 2) seeOrder();
			else if (action == 0) break;
			
		}
		
		while (true);
		
	}
	
	public void buyFood() {
		
		if (!preventMenuNull()) return;
		
		System.out.println("Buy Food");
		
		printRegularMenu();
		
		int index;
		
		do {
			
			System.out.printf("Choose Menu to buy [1 - %d]: ", menuList.size());
			index = scan.nextInt();
			scan.nextLine();
			index--;
			if (index >= 0 && index < menuList.size()) break;
			
		}
		
		while (true);
		
		Food food = menuList.get(index);
		int totalCost = food.getPrice();
		int count = 0;
		
		// Number
		
		do {
			
			System.out.print("How many food [>= 1]: ");
			count = scan.nextInt();
			scan.nextLine();
			
			if (count >= 1) {
				totalCost = totalCost * count;
				break;
			}
			
		}
		
		while (true);
		
		// Delivery or not
		
		if (((Regular) food).getDelivery()) {
			String confirm;
			
			do {
				
				System.out.print("You want to take delivery? ['Yes' | 'No']: ");
				confirm = scan.nextLine();
				if (confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("No")) break;
				
			}
			
			while (true);
			
			// Delivery Price = 5000
			
			if (confirm.equalsIgnoreCase("Yes")) totalCost += 5000;
			
		}
		
		// Payment
		
		if (totalCost <= activeUser.getMoney()) {
			
			String confirm;
			
			do {
				
				System.out.print("Confirm to Buy Food ['Yes' | 'No']: ");
				confirm = scan.nextLine();
				if (confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("No")) break;
				
			}
			
			while (true);
			
			if (confirm.equalsIgnoreCase("Yes")) {
				
				System.out.println("Food Bought Successfully");
				
				activeUser.setMoney(activeUser.getMoney()-totalCost);
				
				Order order = new Order(food.getName(), count, food.getPrice());
				activeUser.getOrderList().add(order);
				
				for (Food food2 : admin.getFoodList()) {
					if (food2.getName().equalsIgnoreCase(food.getName())) food2.setOrderCount(food2.getOrderCount()+count);
				}
				
			}
			
			else {
				System.out.println("Food Order cancelled!");
			}
			
		}
		
		else {
			
			System.out.println("Sorry your money is not enough to buy the food :(");
			
		}
		
		print.continueFunction();
		
	}
	
	public void seeOrder() {
		
		if (activeUser.getOrderList().size() == 0) {
			System.out.println("You haven't order anything yet...");
			print.continueFunction();
			return;
		}
		
		System.out.println("\nYour Order List\n");
		
		int index = 1;
		
		for (Order order : activeUser.getOrderList()) {
			System.out.printf("%-3d %-15s [%-2d]: Rp. %-5s\n", index++, order.getFoodName(), order.getCount(), order.getFoodPrice()*order.getCount());
		}
		
		System.out.println();
		
		print.continueFunction();
		return;
		
	}
	
	// Interface Segregation Principle

	@Override
	public void printRegularMenu() {
		
		if (menuList.size() == 0) {
			System.out.println("No menu right now...");
		}
		
		int index = 1;
		for (Food food : menuList) {
			if (food instanceof Regular) System.out.printf("%-3d %-10s: Rp. %-10d\n", index++, food.getName(), food.getPrice());
		}
		
		System.out.println();
		
	}
	
	public boolean preventMenuNull() {
		
		if (menuList.size() == 0) {
			System.out.println("No menu right now...");
			print.continueFunction();
			return false;
		}
		
		else return true;
		
	}
	
	public boolean init() {
		
		if (admin.getFoodList().size() == 0) {
			System.out.println("Sorry there is literally nothing in NaraWitchen right now...");
			print.continueFunction();
//			return false;
		}
		
		menuList = new ArrayList<>();
		
		for (Food food : admin.getFoodList()) {
			if (food instanceof Regular) menuList.add(food);
		}
		
		return true;
		
	}

}
