package main;

import java.util.ArrayList;
import java.util.Scanner;

import dao.DaoController;
import entity.Admin;
import entity.Entity;
import utility.Authentication;
import utility.Printing;

public class Main {

	Scanner scan = new Scanner(System.in);
	Printing print = new Printing();
	Authentication auth = new Authentication();
	DaoController daoController = new DaoController();
	
	static Admin admin = new Admin();
	
	ArrayList <Entity> userList = new ArrayList<>();
	Entity activeUser;
	
	// There is only 1 Responsibility for the Main Class which is the Main Menu
	
	public Main() {
		
		init();
		
		int action;
		
		do {
			
			print.PrintingMainMenu();
			System.out.print(">> ");
			action = scan.nextInt();
			scan.nextLine();
			
			if (action == 1) {
				activeUser = auth.login(userList);
				if (activeUser != null) daoController.Menu(admin, activeUser);
			}
			
			else if (action == 2) auth.register(userList);
			
			else if (action == 0) break;
			
			else {
				System.out.println("Input Invalid!");
				print.continueFunction();
			}
			
			// Reset user
			
			activeUser = null;
			
		}
		
		while (action != 0);
		
		System.out.println("\nThank you for using NaraWitchen :)");
		
	}
	
	// Function to init there is only 1 Admin
	
	public void init() {
		
		userList.add(admin);
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
