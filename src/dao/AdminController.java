package dao;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Admin;
import entity.Entity;
import foods.Food;
import foods.Regular;
import foods.Special;
import utility.Printing;
import utility.Sorting;

public class AdminController {
	
	Scanner scan = new Scanner (System.in);
	Printing print = new Printing();
	Sorting sort = new Sorting();
	
	Admin activeAdmin;
	
	public void AdminMenu(Entity admin) {
		
		activeAdmin = (Admin) admin;
		
		int action;
		
		do {
			
			System.out.println("== NaraWitchen Admin Page ==\n");
			
			admin.introduction();
			printingFoodList();
			
			printingMenuList();
			
			action = scan.nextInt();
			scan.nextLine();
			
			if (action == 1) {
				addMenu();
			}
			
			else if (action == 2){
				closeOrder();
			}
			
			else if (action == 3){
				updateMenu();
			}
			
			else if (action == 4){
				searchFood();
			}
			
			else if (action == 5){
				sortFood();
			}
			
			else if (action == 0) break;
			
		}
		
		while (action != 0);
		
	}
	
	// CRUD: Create Menu
	
	public void addMenu() {
		
		System.out.println();
		
		String name;
		String foodType;
		String menuType;
		int price;
		
		boolean delivery = false;
		String size = null;
		
		do {
			
			boolean flag = true;
			
			System.out.print("Menu Name [>= 3 characters]: ");
			name = scan.nextLine();
			
			for (Food food : activeAdmin.getFoodList()) {
				if (food.getName().equalsIgnoreCase(name)) {
					flag = false;
					break;
				}
			}
			
			if (flag && name.length() >= 3) break;
			
		}
		
		while (true);
		
		do {
			
			System.out.print("Food Type [>= 5 characters]: ");
			foodType = scan.nextLine();
			if (foodType.length() >= 5) break;
			
		}
		
		while (true);
		
		do {
			
			System.out.print("Food Price: ");
			price = scan.nextInt();
			scan.nextLine();
			if (price >= 5000) break;
			
		}
		
		while (true);
		
		do {
			
			System.out.print("Menu Type ['Regular' | 'Special']: ");
			menuType = scan.nextLine();
			if (menuType.equalsIgnoreCase("Regular") || menuType.equalsIgnoreCase("Special")) break;
			
		}
		
		while (true);
		
		if (menuType.equalsIgnoreCase("Regular")) {
			do {
				
				System.out.print("Menu Deliverable? ['Yes' | 'No']: ");
				String confirmation = scan.nextLine();
				if (confirmation.equalsIgnoreCase("Yes")) delivery = true;
				else delivery = false;
				if (confirmation.equalsIgnoreCase("Yes") || confirmation.equalsIgnoreCase("No")) break;
				
			}
			
			while (true);
		}
		
		else if (menuType.equalsIgnoreCase("Special")) {
			do {
				
				System.out.print("Menu Size? ['Normal' | 'Jumbo']: ");
				size = scan.nextLine();
				if (size.equalsIgnoreCase("Normal") || size.equalsIgnoreCase("Jumbo")) break;
				
			}
			
			while (true);
		}
		
		String confirm;
		
		do {
			
			System.out.print("Confirm to Add Menu ['Yes' | 'No']: ");
			confirm = scan.nextLine();
			if (confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("No")) break;
			
		}
		
		while (true);
		
		if (confirm.equalsIgnoreCase("Yes")) {
			if (menuType.equalsIgnoreCase("Regular"))
				activeAdmin.getFoodList().add(new Regular(name, foodType, price, 0, delivery));
			else if (menuType.equalsIgnoreCase("Special"))
				activeAdmin.getFoodList().add(new Special(name, foodType, price, 0, size));
			System.out.println("Add Menu Success");
		}
		
		else {
			System.out.println("Menu Cancelled!");
		}
		
		print.continueFunction();
		
	}
	
	// CRUD: Delete Menu
	
	public void closeOrder() {
		
		if (!preventMenuNull()) return;
		
		System.out.println();
		
		System.out.println("Close order for the menu");
		
		int num = 1;
		for (Food food : activeAdmin.getFoodList()) {
			System.out.printf("%-3d %-10s: [Order: %3d]\n", num++, food.getName(), food.getOrderCount());
		}
		System.out.println();
		
		int index;
		
		do {
			
			System.out.printf("Choose Menu Number to delete [1 - %d]: ", activeAdmin.getFoodList().size());
			index = scan.nextInt();
			scan.nextLine();
			index--;
			if (index >= 0 && index < activeAdmin.getFoodList().size()) break;
			
		}
		
		while (true);
			
		String confirm;
			
		do {
			
			System.out.print("Confirm to Delete Menu ['Yes' | 'No']: ");
			confirm = scan.nextLine();
			if (confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("No")) break;
			
		}
		
		while (true);
		
		if (confirm.equalsIgnoreCase("Yes")) {
			activeAdmin.getFoodList().remove(index);
			System.out.println("Menu Deleted Successfully");
		}
		
		else {
			System.out.println("Menu Delete cancelled!");
		}
		
	}
	
	// CRUD: Update Menu
	
	public void updateMenu() {
		
		if (!preventMenuNull()) return;
		
		System.out.println();
		
		System.out.println("Update Menu Information");
		
		printingFoodList();
		
		int index;
		
		do {
			
			System.out.printf("Choose Menu Number to update [1 - %d]: ", activeAdmin.getFoodList().size());
			index = scan.nextInt();
			scan.nextLine();
			index--;
			if (index >= 0 && index < activeAdmin.getFoodList().size()) break;
			
		}
		
		while (true);
		
		int newPrice;
		
		do {
			
			System.out.printf("Choose new price for that menu: ");
			newPrice = scan.nextInt();
			scan.nextLine();
			if (newPrice >= 5000 && newPrice != activeAdmin.getFoodList().get(index).getPrice()) break;
			
		}
		
		while (true);
			
		String confirm;
			
		do {
			
			System.out.print("Confirm to Update Menu ['Yes' | 'No']: ");
			confirm = scan.nextLine();
			if (confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("No")) break;
			
		}
		
		while (true);
		
		if (confirm.equalsIgnoreCase("Yes")) {
			activeAdmin.getFoodList().get(index).setPrice(newPrice);;
			System.out.println("Menu Updated Successfully\n");
		}
		
		else {
			System.out.println("Menu update cancelled!\n");
		}
		
	}
	
	// CRUD: Read Food List
	
	public void printingFoodList() {
		
		if (activeAdmin.getFoodList().size() == 0) {
			System.out.println("No menu right now...");
			return;
		}
		
		int index = 1;
		for (Food food : activeAdmin.getFoodList()) {
			System.out.printf("%-3d %-10s: Rp. %-10d\n", index++, food.getName(), food.getPrice());
		}
		System.out.println();
		
	}
	
	// Searching
	
	public void searchFood() {
		
		if (!preventMenuNull()) return;
		
		System.out.println();
		
		System.out.println("Search Menu Detail");
		
		printingFoodList();
		
		int index;
		
		do {
			
			System.out.printf("Choose Menu to search [1 - %d]: ", activeAdmin.getFoodList().size());
			index = scan.nextInt();
			scan.nextLine();
			index--;
			if (index >= 0 && index < activeAdmin.getFoodList().size()) break;
			
		}
		
		while (true);
		
		// Printing detail information
		
		Food food = activeAdmin.getFoodList().get(index);
		
		String menuType = (food instanceof Regular) ? "Regular" : "Special";
		
		System.out.printf("\nMenu Type: %s\n", menuType);
		System.out.printf("Food Name: %s\n", food.getName());
		System.out.printf("Food Type: %s\n", food.getType());
		System.out.printf("Food Price: %d\n", food.getPrice());
		System.out.printf("Food Order Count: %d\n\n", food.getOrderCount());
		
		print.continueFunction();
		
	}
	
	// Sorting
	
	public void sortFood() {
		
		if (!preventMenuNull()) return;
		
		int action;
		
		do {
			
			printingFoodList();
			
			System.out.println("Sort Menu by Price");
			
			System.out.println("1. Sort Ascending");
			System.out.println("2. Sort Descending");
			System.out.println("0. Back");
			System.out.print(">> ");
			action = scan.nextInt();
			scan.nextLine();
			
			if (action == 1) sort.sortAscendingly(activeAdmin.getFoodList());
			else if (action == 2) sort.sortDescendingly(activeAdmin.getFoodList());
			
		}
		
		while (action != 0);
		
	}
	
	public boolean preventMenuNull() {
		
		if (activeAdmin.getFoodList().size() == 0) {
			System.out.println("No menu right now...");
			print.continueFunction();
			return false;
		}
		
		else return true;
		
	}
	
	public void printingMenuList() {
		
		System.out.println("\n=== Admin NaraWitchen ===\n");
		
		System.out.println("1. Add Product to Menu");
		System.out.println("2. Close Order");
		System.out.println("3. Update Menu");
		System.out.println("4. Search Menu");
		System.out.println("5. Sort Menu");
		System.out.println("0. Logout");
		
		System.out.print(">> ");
		
	}
	
	public ArrayList<Food> getFoodList(){
		return activeAdmin.getFoodList();
	}

}
