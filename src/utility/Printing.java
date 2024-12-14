package utility;

import java.util.Scanner;

public class Printing {
	
	Scanner scan = new Scanner (System.in);
	
	// Utility to printing menu and continue funtion
	
	public void PrintingMainMenu() {
		
		System.out.println("== NaraWitchen ==\n");
		System.out.println("1. Login");
		System.out.println("2. Regsiter");
		System.out.println("0. Exit\n");
		
	}
	
	public void continueFunction() {
		System.out.print("Press enter to continue...");
		scan.nextLine();
		System.out.println();
	}

}
