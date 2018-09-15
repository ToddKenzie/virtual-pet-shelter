package petShelterApp;

import java.util.Collection;
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
				hhShelter.feedAllPets();
				System.out.println("You feed all of the animals.  The sounds of animals munching fill the shelter.\n");
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("W")) {
				hhShelter.giveWaterToAllPets();
				System.out.println("You fill all of the water bowls.  The animals become content for a while.\n");
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("P")) {
				System.out.println("Please state which pet you would like to play with\nPet names are Case-Sensitive\n");
				Collection<VirtualPet> allPets = hhShelter.getAllPets();
				for (VirtualPet virtualPet : allPets) {
					System.out.println(virtualPet.getName() + ": " + virtualPet.getDescription());
				}
				String petNameToPlayWith = verifyPetNameIsValid();
				hhShelter.playWith(petNameToPlayWith);
				VirtualPet petPlayedWith = hhShelter.retrievePetInfo(petNameToPlayWith);
				
				if (petPlayedWith instanceof VirtualDog) {
					boolean areThereOtherDogs = false;
					for (VirtualPet virtualPet : allPets) {
						if (virtualPet instanceof VirtualDog && !virtualPet.getName().equals(petNameToPlayWith)) {
							areThereOtherDogs = true;
						}
					}
					System.out.println(petNameToPlayWith +" runs around from the attention, chasing balls.");
					if (areThereOtherDogs) {
						System.out.println("While " + petNameToPlayWith + " is happy, other dogs in the shelter seem to be dejected.\n");
					}
				} else if (petPlayedWith instanceof VirtualBird) {
					boolean isThereAtLeastACat = false;
					for (VirtualPet virtualPet : allPets) {
						if (virtualPet instanceof VirtualCat) {
							isThereAtLeastACat = true;
						}
					}
					System.out.println(petNameToPlayWith + " tweets loudly from the attention." );
					if (isThereAtLeastACat) {
						System.out.println("The cats begin to pace about their cages.\n");
					}
				} else {
					System.out.println(petNameToPlayWith + " purrs from the scritches.\n");
				}
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("V")) {
				System.out.println("Who needs the attention of the vet?\nWe can only get one treated\n");
				String petToGetVetTreatment = verifyPetNameIsValid();
				hhShelter.callVetFor(petToGetVetTreatment);
				System.out.println(petToGetVetTreatment + " seems to be doing much better.");
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("C")) {
				hhShelter.cleanShelter();
				System.out.println(
						"Cleaning the shelter is probably a good idea.  You don't want to let the waste pile up.\n");
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("A")) {
				System.out.println("Which pet will you try to get adopted today?\nPet names are Case-Sensitive\n");
				String petNameToAdopt = verifyPetNameIsValid();

				boolean wasPetAdopted = hhShelter.adopt(petNameToAdopt);
				if (wasPetAdopted) {
					System.out.println("Congratulations! " + petNameToAdopt + " was successfully adopted!");
				} else {
					System.out.println(
							"Unfortunately, it doesn't seem like anyone wanted to adopt " + petNameToAdopt + " today.");
				}

			} else if (userInputOptionsMenu.equalsIgnoreCase("B")) {
				willExecuteTickAfterMenuInput = false;
				System.out.println("What type of pet do you want to bring in?  Dog, Cat, or Bird?");
				String petType = verifyPetTypeIsValid();

				System.out.println("What should the pet name be?");
				String petName = verifyPetNameDoesNotAlreadyExist();

				System.out.println("Please enter a short description for the new pet");
				String petDescription = input.nextLine();

				System.out.println("Would you like to enter starting atrributes for your new pet (Y/N)?");
				boolean userChoiceToEnterAtrributes = verifyUserChoiceToEnterAttributes();
				
				if (userChoiceToEnterAtrributes) {
					
				} else {
					hhShelter.takeInNewPet(petType, petName, petDescription);
					System.out.println(petName + " has been brought into the shelter.\n");
				}
				Thread.sleep(1000);
				System.out.println(appMenus.displayShelterStatusMenu(hhShelter));
				Thread.sleep(1000);

			} else if (userInputOptionsMenu.equalsIgnoreCase("H")) {
				willExecuteTickAfterMenuInput = false;
				System.out.println(appMenus.displayStatusHelpMenu());
				Thread.sleep(1000);
			} else if (userInputOptionsMenu.equalsIgnoreCase("Q")) {
				willExecuteTickAfterMenuInput = false;
				continuePlaying = false;
			} else {
				willExecuteTickAfterMenuInput = false;
				System.out.println("Unknown command.  Please review options and try again.\n");
				Thread.sleep(2000);
			}

			if (willExecuteTickAfterMenuInput) {
				String resultsOfTick = hhShelter.tick();
				System.out.println(resultsOfTick);
				Thread.sleep(2000);

				System.out.println(appMenus.displayShelterStatusMenu(hhShelter));
				Thread.sleep(1000);
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

	public static String verifyPetNameIsValid() {
		String userEnteredPetName;
		boolean petNameInvalid;

		do {
			userEnteredPetName = input.nextLine();
			petNameInvalid = !hhShelter.checkIfPetNameExists(userEnteredPetName);
			if (petNameInvalid) {
				System.out.println("Pet Name does not exist - double-check spelling and capitalization.");
			}
		} while (petNameInvalid);

		return userEnteredPetName;
	}

	public static String verifyPetNameDoesNotAlreadyExist() {
		String userEnteredPetName;
		boolean petNameInvalid;

		do {
			userEnteredPetName = input.nextLine();
			petNameInvalid = hhShelter.checkIfPetNameExists(userEnteredPetName);
			if (petNameInvalid) {
				System.out.println("Pet Name already exists.  Please choose another.");
			}
		} while (petNameInvalid);

		return userEnteredPetName;
	}

	public static String verifyPetTypeIsValid() {
		String userEnteredPetType;
		boolean isPetTypeInvalid;
		do {
			userEnteredPetType = input.nextLine();
			isPetTypeInvalid = !(userEnteredPetType.equalsIgnoreCase("dog")
					|| userEnteredPetType.equalsIgnoreCase("cat") || userEnteredPetType.equalsIgnoreCase("bird"));
			if (isPetTypeInvalid) {
				System.out.println("Please choose dog, cat, or bird.  We really can't take care of other pets here.");
			}
		} while (isPetTypeInvalid);

		return userEnteredPetType;
	}

	public static boolean verifyUserChoiceToEnterAttributes() {
		String userChoiceToEnterAtrributes;
		boolean isChoiceInvalid = true;
		do {
			userChoiceToEnterAtrributes = input.nextLine();
			if (!(userChoiceToEnterAtrributes.equalsIgnoreCase("y")
					|| userChoiceToEnterAtrributes.equalsIgnoreCase("n"))) {
				System.out.println("Invalid choice.  Press \'Y\' or \'N\'\n");
			} else {
				isChoiceInvalid = false;
			}
		} while (isChoiceInvalid);
		if (userChoiceToEnterAtrributes.equalsIgnoreCase("y")) {
			return true;
		} else {
			return false;
		}
	}
}
