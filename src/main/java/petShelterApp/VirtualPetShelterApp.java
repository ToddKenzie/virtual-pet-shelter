package petShelterApp;


import java.util.Scanner;

import petShelter.*;

public class VirtualPetShelterApp {

	public static VirtualPetShelter hhShelter = new VirtualPetShelter();
	public static VPSAppMenus appMenus = new VPSAppMenus();
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {

		shelterStartUp();
		System.out.println(appMenus.displayWelcomeMenu());
		Thread.sleep(4000);
		System.out.println(appMenus.displayStatusHelpMenu());
		Thread.sleep(4000);
		System.out.println(appMenus.displayShelterStatusMenu(hhShelter));
		Thread.sleep(3000);

		
		boolean continuePlaying = true;
		do {
			System.out.println(appMenus.displayOptionsMenu());
			String userInputOptionsMenu = input.nextLine();
			
			boolean willExecuteTickAfterMenuInput = true;
			
			if (userInputOptionsMenu.equalsIgnoreCase("F")) {
				
			} else if (userInputOptionsMenu.equalsIgnoreCase("W")) {
				
			} else if (userInputOptionsMenu.equalsIgnoreCase("P")) {
				
			} else if (userInputOptionsMenu.equalsIgnoreCase("V")) {
				
			} else if (userInputOptionsMenu.equalsIgnoreCase("C")) {
				
			} else if (userInputOptionsMenu.equalsIgnoreCase("A")) {
				
			} else if (userInputOptionsMenu.equalsIgnoreCase("B")) {
				willExecuteTickAfterMenuInput = false;
			} else if (userInputOptionsMenu.equalsIgnoreCase("H")) {
				willExecuteTickAfterMenuInput = false;
			} else if (userInputOptionsMenu.equalsIgnoreCase("Q")) {
				willExecuteTickAfterMenuInput = false;
				continuePlaying = false;
			} else {
				willExecuteTickAfterMenuInput = false;
				System.out.println("Unknown command.  Please review options and try again.\n");
				Thread.sleep(2000);
			}
			
			if (willExecuteTickAfterMenuInput) {
				System.out.println("Tick execute");
				Thread.sleep(2000);
				
				System.out.println(appMenus.displayShelterStatusMenu(hhShelter));
				Thread.sleep(3000);
			}

			
		} while (continuePlaying);

		System.out.println("Thanks for playing");
		
		input.close();
	}

	public static void shelterStartUp() {
		hhShelter.generateNewRandomPet();
		hhShelter.generateNewRandomPet();
		hhShelter.generateNewRandomPet();
	}

}
