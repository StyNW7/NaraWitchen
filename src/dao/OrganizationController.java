package dao;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Admin;
import entity.Entity;
import entity.Organization;
import entity.User;
import foods.Food;
import foods.Order;
import foods.Regular;
import foods.Special;
import interfaces.RegularMenu;
import interfaces.SpecialMenu;
import utility.Printing;

public class OrganizationController implements RegularMenu, SpecialMenu {
	
	Scanner scan = new Scanner(System.in);
	Printing print = new Printing();
	
	Admin admin;
	
	ArrayList<Food> menuList = new ArrayList<>();
	
	Organization activeUser;
	
	public void UserMenu(Admin activeAdmin, Entity user) {
		
		admin = activeAdmin;
		activeUser = (Organization) user;
		
		if (!init()) return;
		
		int action;
		
		do {
			
			activeUser.introduction();
			printRegularMenu();
			printSpecialMenu();
			
			System.out.println("\n=== Organization NaraWitchen ===\n");
			
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
		
		System.out.println("\n== Buy Food ==\n");
		
		printMenuList();
		
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
		
		if (food instanceof Regular) {
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
		}
		
		// Normal or Jumbo
		
		else if (food instanceof Special) {
			if (((Special) food).getSize().equals("Jumbo")) {
				
				totalCost += 5000*count;
				
			}
		}
		
		// Payment (because organization there is discount)
		
		int totalDiscount = (int) (totalCost * activeUser.getDiscount() / 100);
		totalCost = totalCost - totalDiscount;
		
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
				
				Order order = new Order(food.getName(), count, totalCost);
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
		
		System.out.println("Your Order List\n");
		
		int index = 1;
		
		for (Order order : activeUser.getOrderList()) {
			System.out.printf("%-3d %-15s [%-2d]: Rp. %-5s\n", index++, order.getFoodName(), order.getCount(), order.getFoodPrice());
		}
		
		print.continueFunction();
		return;
		
	}
	
	public void printMenuList() {
		
		System.out.println("== Regular & Special Menu ==");
		
		int index = 1;
		for (Food food : menuList) {
			if (food instanceof Regular) System.out.printf("%-3d %-10s: Rp. %-10d\n", index++, food.getName(), food.getPrice());
			else System.out.printf("%-3d %-10s [%-8s]: Rp. %-10d\n", index++, food.getName(), 
					((Special) food).getSize(), food.getPrice());
		}
		
		if (index == 1) {
			System.out.println("No Menu right now...\n");
		}
		
		System.out.println();
		
	}
	
	// Interface Segregation Principle

	@Override
	public void printRegularMenu() {
		
		System.out.println("== Regular Menu ==");
		
		int index = 1;
		for (Food food : menuList) {
			if (food instanceof Regular) System.out.printf("%-3d %-10s: Rp. %-10d\n", index++, food.getName(), food.getPrice());
		}
		
		if (index == 1) {
			System.out.println("No Regular Menu right now...\n");
		}
		
		System.out.println();
		
	}
	
	@Override
	public void printSpecialMenu() {
		
		System.out.println("== Special Menu ==");
		
		int index = 1;
		for (Food food : menuList) {
			if (food instanceof Special) System.out.printf("%-3d %-10s [%-8s]: Rp. %-10d\n", index++, food.getName(), 
					((Special) food).getSize(), food.getPrice());
		}
		
		if (index == 1) {
			System.out.println("No Special Menu right now...\n");
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
			menuList.add(food);
		}
		
		return true;
		
	}

}
