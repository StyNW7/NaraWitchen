package utility;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Entity;
import entity.Organization;
import entity.User;
import foods.Food;

public class Authentication {
	
	Scanner scan = new Scanner(System.in);
	Printing print = new Printing();
	
	// Login Account
	
	public Entity login(ArrayList<Entity> userList) {
		
		System.out.println("\n== NaraWitchen Login ==\n");
		
		Entity activeUser;
		
		String name;
		String password;
		
		System.out.print("Name: ");
		name = scan.nextLine();
		
		System.out.print("Password: ");
		password = scan.nextLine();
		
		// Admin credentials
		
		if (name.equals("Admin") && password.equals("Admin123")) {
			activeUser = userList.get(0);
			return activeUser;
		}
		
		// User and Organization credentials
		
		for (Entity entity : userList) {
			if (entity.getName().equals(name) && entity.getPassword().equals(password)) {
				activeUser = entity;
				return activeUser;
			}
		}
		
		// Credentials Invalid
		
		System.out.println("There is no such credentials!\n");
		print.continueFunction();
		
		return null;
		
	}
	
	// Register Account
	
	public void register(ArrayList<Entity> userList) {
		
		System.out.println("\n== NaraWitchen Register ==\n");
		
		String name;
		String password;
		String accountType;
		String confirmation;
		int money;
		double discount = 0;
		
		do {
			
			boolean flag = true;
			
			System.out.print("Name [>= 5 characters]: ");
			name = scan.nextLine();
			
			for (Entity person : userList) {
				if (person.getName().equalsIgnoreCase(name)) {
					flag = false;
					break;
				}
			}
			
			if (flag && name.length() >= 5) break;
			
		}
		
		while (true);
		
		do {
			
			System.out.print("Password [>= 8 characters]: ");
			password = scan.nextLine();
			if (password.length() >= 8) break;
			
		}
		
		while (true);
		
		do {
			
			System.out.print("Account Type [User | Organization]: ");
			accountType = scan.nextLine();
			if (accountType.equalsIgnoreCase("user") || accountType.equalsIgnoreCase("organization")) break;
			
		}
		
		while (true);
		
		do {
			
			System.out.print("Money [>= 100000]: ");
			money = scan.nextInt();
			scan.nextLine();
			if (money >= 100000) break;
			
		}
		
		while (true);
		
		if (accountType.equalsIgnoreCase("organization")) {
			
			do {
				
				System.out.print("Discount [>= 5]: ");
				discount = scan.nextDouble();
				scan.nextLine();
				if (discount >= 5) break;
				
			}
			
			while (true);
			
		}
		
		do {
			
			System.out.print("Are you sure to register that account ['Yes' | 'No']: ");
			confirmation = scan.nextLine();
			if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("no")) break;
			
		}
		
		while (true);
		
		// Create account
		
		if (confirmation.equalsIgnoreCase("yes")) {
			
			if (accountType.equalsIgnoreCase("organization"))
				userList.add(new Organization(name, password, money, discount));
			
			else if (accountType.equalsIgnoreCase("user"))
				userList.add(new User(name, password, money));
			
			System.out.println("Register Succeed!\n");
			
		}
		
		else {
			System.out.println("Regsiter Cancelled!\n");
		}
		
		print.continueFunction();
		
	}

}
